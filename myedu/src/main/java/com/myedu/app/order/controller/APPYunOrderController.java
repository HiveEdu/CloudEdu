package com.myedu.app.order.controller;

import cn.hutool.core.util.StrUtil;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysGrade;
import com.myedu.project.dataBasic.service.ISysGradeService;
import com.myedu.project.order.domain.YunOrder;
import com.myedu.project.order.domain.vo.YunOrderVo;
import com.myedu.project.order.enums.OrderStatus;
import com.myedu.project.order.enums.StoreStuStatus;
import com.myedu.project.order.service.IYunOrderService;
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStudentService;
import com.myedu.project.store.domain.YunStoreStu;
import com.myedu.project.store.service.IYunStoreStuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.util.List;

/**
 * 订单Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-22
 */
@Api("订单")
@RestController
@RequestMapping("/app/order/order")
public class APPYunOrderController extends BaseController
{
    @Autowired
    private IYunOrderService yunOrderService;
    @Autowired
    private IYunStudentService yunStudentService;
    @Autowired
    private ISysGradeService sysGradeService;
    @Autowired
    private IYunStoreStuService yunStoreStuService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询订单列表
     */
    @AutoIdempotent
    @ApiOperation("查询订单列表")
    @ApiImplicitParam(name = "yunOrder", value = "查询订单列表",
            dataType = "YunOrderVo")
    @GetMapping("/list")
    public TableDataInfo list(YunOrderVo yunOrder)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        yunOrder.setCreateById(loginUser.getUser().getUserId());
        List<YunOrderVo> list = yunOrderService.selectYunOrderList(yunOrder);
        return getDataTable(list);
    }


    /**
     * 获取订单详细信息
     */
    @AutoIdempotent
    @ApiOperation("获取订单详细信息")
    @ApiImplicitParam(name = "id", value = "获取订单详细信息",
            dataType = "Long")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        YunStudentVo yunStudent=new YunStudentVo();
        yunStudent.setCreateById(SecurityUtils.getUserId());
        SysGrade sysGrade=new SysGrade();
        ajax.put("students", yunStudentService.selectYunStudentList(yunStudent));
        ajax.put("sysGrades", sysGradeService.selectSysGradeList(sysGrade));
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG,yunOrderService.selectYunOrderById(id));
        }
        return ajax;
    }

    /**
     * 新增订单
     */
    @AutoIdempotent
    @ApiOperation("新增订单")
    @ApiImplicitParam(name = "yunOrder", value = "新增订单",
            dataType = "YunOrderVo")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunOrder yunOrder)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunOrder.setCreateById(loginUser.getUser().getUserId());
        yunOrder.setCreateBy(loginUser.getUser().getNickName());
        yunOrder.setCreateTime(DateUtils.getNowDate());
        if(yunOrder.getStoreId()!=null){
            //报名给这个门店添加学生
            YunStoreStu yunStoreStu=new YunStoreStu();
            yunStoreStu.setStoreId(yunOrder.getStoreId());
            yunStoreStu.setStuId(yunOrder.getStudentId());
            yunStoreStu.setCreateById(SecurityUtils.getUserId());
            yunStoreStu.setCreateBy(SecurityUtils.getUsername());
            yunStoreStu.setStatus(StoreStuStatus.SIGNUP.getCode());
            yunStoreStuService.insertYunStoreStu(yunStoreStu);
        }
        return toAjax(yunOrderService.insertYunOrder(yunOrder));
    }

    /**
     * 修改订单
     */
    @AutoIdempotent
    @ApiOperation("修改订单")
    @ApiImplicitParam(name = "yunOrder", value = "修改订单",
            dataType = "YunOrderVo")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunOrder yunOrder)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunOrder.setUpdateTime(DateUtils.getNowDate());
        yunOrder.setUpdateBy(loginUser.getUser().getNickName());
        return toAjax(yunOrderService.updateYunOrder(yunOrder));
    }

    /**
     * 删除订单
     */
    @AutoIdempotent
    @ApiOperation("删除订单")
    @ApiImplicitParam(name = "ids", value = "删除订单",
            dataType = "Long[]")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunOrderService.deleteYunOrderByIds(ids));
    }

    @AutoIdempotent
    @ApiOperation("支付宝PC网页支付")
    @ApiImplicitParam(name = "yunOrder", value = "支付宝PC网页支付",
            dataType = "YunOrderVo")
    @Log(title = "支付宝PC网页支付")
    @PostMapping(value = "/toPayAsPC")
    public AjaxResult toPayAsPc(@RequestBody YunOrderVo yunOrder) throws Exception{
        AjaxResult ajax = AjaxResult.success();
        String payUrl = yunOrderService.toPayAsPc(yunOrder);
        ajax.put("url",payUrl);
        return ajax;
    }

    @AutoIdempotent
    @ApiOperation("支付宝手机支付")
    @ApiImplicitParam(name = "yunOrder", value = "支付宝手机支付",
            dataType = "YunOrderVo")
    @Log(title = "支付宝手机支付")
    @PostMapping(value = "/toPayAsWeb")
    public AjaxResult toPayAsWeb(@RequestBody YunOrderVo yunOrder) throws Exception{
        AjaxResult ajax = AjaxResult.success();
        String payUrl = yunOrderService.toPayAsWeb(yunOrder);
        ajax.put("url",payUrl);
        return ajax;
    }

    /**
     * @Description :同步通知
     * @Author : 梁少鹏
     * @Date : 2020/2/12 11:35
     */
    @AutoIdempotent
    @ApiOperation("同步通知")
    @GetMapping(value = "/getReturnUrlInfo")
    public String alipayReturnUrlInfo(HttpServletRequest request) {
        String result=yunOrderService.synchronous(request);
        if(result.equals("success")){
            return "<html>\n" +
                    "<head>\n" +
                    "<script type=\"text/javascript\"> function load() { window.close(); } </script>\n" +
                    "</head>\n" +
                    "<body onload=\"" +
                    "load()\"> </body>\n" +
                    "</html>";
        }else{
            result="支付失败";
        }
        return result;
    }
    /**
     * @Description :异步通知
     * @Author : 梁少鹏
     * @Date : 2020/2/12 11:35
     */
    @AutoIdempotent
    @ApiOperation("异步通知")
    @PostMapping(value = "/getNotifyUrlInfo")
    public void alipayNotifyUrlInfo(HttpServletRequest request, HttpServletResponse response){
        yunOrderService.notify(request,response);
    }

    @AutoIdempotent
    @ApiOperation(value = "订单核销")
    @PutMapping(value = "/check")
    // @PreAuthorize("@el.check('admin','STOREORDER_ALL','STOREORDER_EDIT')")
    public AjaxResult check(@Validated @RequestBody YunOrder resources) {
        if (StrUtil.isBlank(resources.getVerifyCode())) {
            return AjaxResult.error("核销码不能为空");
        }
        if (StrUtil.isBlank(resources.getStatus())) {
            return AjaxResult.error("订单状态码不能为空");
        }
        YunOrderVo storeOrderDTO = yunOrderService.selectYunOrderById(resources.getId());
        if (!resources.getVerifyCode().equals(storeOrderDTO.getVerifyCode())) {
            //throw new BadRequestException("核销码不对");
            return AjaxResult.error("核销码不对");
        }
        if (OrderStatus.TOBEPAID.getCode().equals(resources.getStatus())) {
            //throw new BadRequestException("订单未支付");
            return AjaxResult.error("订单未支付");
        }
        if (Integer.valueOf(resources.getStatus())>0) {
            // throw new BadRequestException("订单已核销");
            return AjaxResult.error("订单已核销");
        }

//        if (storeOrderDTO.getCombinationId() > 0 && storeOrderDTO.getPinkId() > 0) {
//            StorePinkDTO storePinkDTO = storePinkService.findById(storeOrderDTO.getPinkId());
//            if (!OrderInfoEnum.PINK_STATUS_2.getValue().equals(storePinkDTO.getStatus())) {
//                throw new BadRequestException("拼团订单暂未成功无法核销");
//            }
//        }

//        订单核销
        resources.setStatus(OrderStatus.COMPLETED.getCode());
        return toAjax(yunOrderService.updateYunOrder(resources));
    }

    @AutoIdempotent
    @ApiOperation(value = "修改订单备注")
    @PostMapping(value = "/storeOrder/remark")
    public AjaxResult editOrderRemark(@RequestBody YunOrder resources) {
        if (StrUtil.isBlank(resources.getRemark())) {
            return AjaxResult.error("请输入备注");
        }
        return toAjax(yunOrderService.updateYunOrder(resources));
    }

    /**
     * Sign签名生成
     *
     * @param content 内容
     * @param keyValue Appkey
     * @param charset 编码方式
     * @return DataSign签名
     */
    private String encrypt(String content, String keyValue, String charset) {
        if (keyValue != null) {
            content = content + keyValue;
        }
        byte[] src;
        try {
            src = MD5(content, charset).getBytes(charset);
            return Base64Utils.encodeToString(src);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * MD5加密
     *
     * @param str 内容
     * @param charset 编码方式
     * @throws Exception
     */
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }
}
