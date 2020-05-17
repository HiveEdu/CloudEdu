package com.myedu.framework.config;

import cn.hutool.json.JSONUtil;
import com.myedu.common.constant.HttpStatus;
import com.myedu.common.exception.CustomException;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/5/5
 * Time: 8:51
 * Description:
 */
@Component
public class AutoIdempotentInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    /**
     * 预处理
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //被ApiIdempotment标记的扫描
        AutoIdempotent methodAnnotation = method.getAnnotation(AutoIdempotent.class);
        if (methodAnnotation != null) {
            try {
               Boolean re= tokenService.checkToken(request);
                return  re;
//               if(re){
//                   return  true;
//                   // 幂等性校验, 校验通过则放行, 校验失败则抛出异常, 并通过统一异常处理返回友好提示
//               }else{
//                   throw new CustomException("获取用户账户异常", HttpStatus.TOKENFAIL);
//               }

            }catch (Exception ex){
                AjaxResult failedResult = AjaxResult.error(101, ex.getMessage());
                writeReturnJson(response,JSONUtil.toJsonStr(failedResult));
                throw ex;
            }
        }
        //必须返回true,否则会被拦截一切请求
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 返回的json值
     * @param response
     * @param json
     * @throws Exception
     */
    private void writeReturnJson(HttpServletResponse response, String json) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
