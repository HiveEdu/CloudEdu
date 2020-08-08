package com.myedu.project.order.controller;

import cn.hutool.core.util.StrUtil;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
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
import com.myedu.project.store.domain.vo.YunStoreStuVo;
import com.myedu.project.store.service.IYunStoreStuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RestController
@RequestMapping("/order/order")
public class YunOrderController extends BaseController
{
    @Autowired
    private IYunOrderService yunOrderService;
    @Autowired
    private IYunStudentService yunStudentService;
    @Autowired
    private ISysGradeService sysGradeService;
    @Autowired
    private IYunStoreStuService yunStoreStuService;
    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunOrderVo yunOrder)
    {
        startPage();
        List<YunOrderVo> list = yunOrderService.selectYunOrderList(yunOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunOrderVo yunOrder)
    {
        List<YunOrderVo> list = yunOrderService.selectYunOrderList(yunOrder);
        ExcelUtil<YunOrderVo> util = new ExcelUtil<YunOrderVo>(YunOrderVo.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:query')")
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
    @PreAuthorize("@ss.hasPermi('order:order:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunOrder yunOrder)
    {
        yunOrder.setCreateById(SecurityUtils.getUserId());
        yunOrder.setCreateBy(SecurityUtils.getUseNickName());
        yunOrder.setCreateTime(DateUtils.getNowDate());
        if(yunOrder.getStoreId()!=null){
            //报名给这个门店添加学生
            YunStoreStuVo yunStoreStu1=new YunStoreStuVo();
            yunStoreStu1.setStoreId(yunOrder.getStoreId());
            yunStoreStu1.setStuId(yunOrder.getStudentId());
            if(yunStoreStuService.selectYunStoreStuList(yunStoreStu1).size()<1){
                YunStoreStu yunStoreStu=new YunStoreStu();
                yunStoreStu.setStoreId(yunOrder.getStoreId());
                yunStoreStu.setStuId(yunOrder.getStudentId());
                yunStoreStu.setCreateById(SecurityUtils.getUserId());
                yunStoreStu.setCreateBy(SecurityUtils.getUsername());
                yunStoreStu.setStatus(StoreStuStatus.SIGNUP.getCode());
                yunStoreStuService.insertYunStoreStu(yunStoreStu);
            }
        }
        return toAjax(yunOrderService.insertYunOrder(yunOrder));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('order:order:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunOrder yunOrder)
    {
        yunOrder.setUpdateTime(DateUtils.getNowDate());
        return toAjax(yunOrderService.updateYunOrder(yunOrder));
    }
    /*
     * @Description :退款
     * @Author : 梁少鹏
     * @Date : 2020/5/4 7:42
     */
    @PreAuthorize("@ss.hasPermi('order:order:rebund')")
    @GetMapping(value = { "rebund/", "rebund/{id}" })
    public AjaxResult rebund(@PathVariable(value = "id", required = false) Long id) {
        return yunOrderService.rebund(id);
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('order:order:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunOrderService.deleteYunOrderByIds(ids));
    }


    @PreAuthorize("@ss.hasPermi('order:order:toPayAsPC')")
    @Log(title = "支付宝PC网页支付")
    @PostMapping(value = "/toPayAsPC")
    public AjaxResult toPayAsPc(@RequestBody YunOrderVo yunOrder) throws Exception{
        AjaxResult ajax = AjaxResult.success();
        yunOrder.setPayWay("1");//支付方式支付宝支付
        yunOrder.setStatus(OrderStatus.HAVETOPAY.getCode());//已支付状态
        yunOrderService.updateYunOrder(yunOrder);
//        String payUrl = yunOrderService.toPayAsPc(yunOrder);
//        ajax.put("url",payUrl);
        return ajax;
    }

    @PreAuthorize("@ss.hasPermi('order:order:toPayAsWeb')")
    @Log(title = "支付宝手机支付")
    @PostMapping(value = "/toPayAsWeb")
    public AjaxResult toPayAsWeb(@RequestBody YunOrderVo yunOrder) throws Exception{
        // 修改订单状态为支付成功，已付款; 同时新增支付流水
        AjaxResult ajax = AjaxResult.success();
        yunOrder.setPayWay("1");//支付方式支付宝支付
        yunOrder.setStatus(OrderStatus.HAVETOPAY.getCode());//已支付状态
        yunOrderService.updateYunOrder(yunOrder);
//        AjaxResult ajax = AjaxResult.success();
//        String payUrl = yunOrderService.toPayAsWeb(yunOrder);
//        ajax.put("url",payUrl);
        return ajax;
    }

    /*
     * @Description :同步通知
     * @Author : 梁少鹏
     * @Date : 2020/2/12 11:35
     */
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
    /*
     * @Description :异步通知
     * @Author : 梁少鹏
     * @Date : 2020/2/12 11:35
     */
    @PostMapping(value = "/getNotifyUrlInfo")
    public void alipayNotifyUrlInfo(HttpServletRequest request, HttpServletResponse response){
        yunOrderService.notify(request,response);
    }


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
