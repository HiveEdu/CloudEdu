package com.myedu.app.store;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.store.domain.YunStoreCouponIssue;
import com.myedu.project.store.domain.YunStoreCouponReceive;
import com.myedu.project.store.domain.vo.YunStoreCouponIssueVo;
import com.myedu.project.store.enums.CouponIssueStatus;
import com.myedu.project.store.enums.receiveStatus;
import com.myedu.project.store.service.IYunStoreCouponIssueService;
import com.myedu.project.store.service.IYunStoreCouponReceiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 店铺优惠券发布Controller
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@Api("店铺优惠券发布")
@RestController
@RequestMapping("/app/store/publishCoupon")
public class APPYunStoreCouponIssueController extends BaseController
{
    @Autowired
    private IYunStoreCouponIssueService yunStoreCouponIssueService;

    @Autowired
    private IYunStoreCouponReceiveService yunStoreCouponReceiveService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询店铺优惠券发布列表
     */
    @ApiOperation("查询店铺优惠券发布列表")
    @ApiImplicitParam(name = "yunStoreCouponIssue", value = "查询店铺优惠券发布列表",
            dataType = "YunStoreCouponIssueVo")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreCouponIssueVo yunStoreCouponIssue)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
        startPage();
        List<YunStoreCouponIssueVo> list = yunStoreCouponIssueService.selectYunStoreCouponIssueList(yunStoreCouponIssue);
        return getDataTable(list);
        }else{
            return getDataTableLose(null);
        }
    }

    /**
     * 导出店铺优惠券发布列表
     */
    @ApiOperation("导出店铺优惠券发布列表")
    @ApiImplicitParam(name = "yunStoreCouponIssue", value = "导出店铺优惠券发布列表",
            dataType = "YunStoreCouponIssueVo")
    @Log(title = "店铺优惠券发布", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreCouponIssueVo yunStoreCouponIssue)
    {
        List<YunStoreCouponIssueVo> list = yunStoreCouponIssueService.selectYunStoreCouponIssueList(yunStoreCouponIssue);
        ExcelUtil<YunStoreCouponIssueVo> util = new ExcelUtil<YunStoreCouponIssueVo>(YunStoreCouponIssueVo.class);
        return util.exportExcel(list, "issue");
    }

    /**
     * 获取店铺优惠券发布详细信息
     */
    @ApiOperation("获取店铺优惠券发布详细信息")
    @ApiImplicitParam(name = "id", value = "获取店铺优惠券发布详细信息",
            dataType = "Long", required = true, paramType = "path")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult getInfo(@PathVariable Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if(loginUser!=null) {
        return AjaxResult.success(yunStoreCouponIssueService.selectYunStoreCouponIssueById(id));
        }else{
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 领用店铺优惠券
     */
    @ApiOperation("领用店铺优惠券")
    @ApiImplicitParam(name = "id", value = "领用店铺优惠券",
            dataType = "Long")
    @GetMapping(value = "/receive/{id}")
    public AjaxResult receive(@PathVariable("id") Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if(loginUser!=null) {
                YunStoreCouponIssue yunStoreCouponIssue=yunStoreCouponIssueService.selectYunStoreCouponIssueById(id);
                if(yunStoreCouponIssue.getStatus().equals(CouponIssueStatus.CLOSE.getCode())){
                    return AjaxResult.error(500,"此优惠卷未开启");
                }else if(yunStoreCouponIssue.getStatus().equals(CouponIssueStatus.INVA.getCode())){
                    return AjaxResult.error(500,"此优惠卷已失效");
                }else if(yunStoreCouponIssue.getLeadStartTime().getTime()>new Date().getTime()){
                    return AjaxResult.error(500,"此优惠卷开始时间还未到");
                }else if(yunStoreCouponIssue.getLeadEndTime().getTime()<new Date().getTime()){
                    return AjaxResult.error(500,"此优惠卷已经过期");
                }else if(yunStoreCouponIssue.getIsPermanent().equals("0")&&yunStoreCouponIssue.getTotalCount()==0){
                    return AjaxResult.error(500,"此优惠卷已经领取完毕");//0是限量1不限量
                }else{
                    YunStoreCouponReceive yunStoreCouponReceive=new YunStoreCouponReceive();
                    yunStoreCouponReceive.setSciId(id);
                    yunStoreCouponReceive.setCreateById(SecurityUtils.getUserId());
                    yunStoreCouponReceive.setCreateBy(SecurityUtils.getUsername());
                    yunStoreCouponReceive.setCreateTime(new Date());
                    yunStoreCouponReceive.setStatus(receiveStatus.STORE.getCode());//未使用
                    int row= yunStoreCouponReceiveService.insertYunStoreCouponReceive(yunStoreCouponReceive);
                    if(row>=1){
                        yunStoreCouponIssue.setLeadCount(yunStoreCouponIssue.getLeadCount()+1);
                        if(yunStoreCouponIssue.getIsPermanent().equals(0)){
                            yunStoreCouponIssue.setRemainCount(yunStoreCouponIssue.getTotalCount()-1);
                        }
                        yunStoreCouponIssueService.updateYunStoreCouponIssue(yunStoreCouponIssue);
                        return AjaxResult.success("领取成功");
                    }else{
                        return  AjaxResult.error(500,"系统错误");
                    }
                }
        }else{
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 新增店铺优惠券发布
     */
//    @ApiOperation("新增店铺优惠券发布")
//    @ApiImplicitParam(name = "yunStoreCouponIssue", value = "新增店铺优惠券发布",
//            dataType = "YunStoreCouponIssue")
//    @Log(title = "店铺优惠券发布", businessType = BusinessType.INSERT)
//    @PostMapping
//    public AjaxResult add(@RequestBody YunStoreCouponIssue yunStoreCouponIssue)
//    {
//        return toAjax(yunStoreCouponIssueService.insertYunStoreCouponIssue(yunStoreCouponIssue));
//    }

    /**
     * 修改店铺优惠券发布
     */
//    @ApiOperation("修改店铺优惠券发布")
//    @ApiImplicitParam(name = "yunStoreCouponIssue", value = "修改店铺优惠券发布",
//            dataType = "YunStoreCouponIssue")
//    @PreAuthorize("@ss.hasPermi('store:publishCoupon:edit')")
//    @Log(title = "店铺优惠券发布", businessType = BusinessType.UPDATE)
//    @PutMapping
//    public AjaxResult edit(@RequestBody YunStoreCouponIssue yunStoreCouponIssue)
//    {
//        return toAjax(yunStoreCouponIssueService.updateYunStoreCouponIssue(yunStoreCouponIssue));
//    }

    /**
     * 删除店铺优惠券发布
     */
    @ApiOperation("删除店铺优惠券发布")
    @ApiImplicitParam(name = "ids", value = "删除店铺优惠券发布",
            dataType = "Integer[]")
    @Log(title = "店铺优惠券发布", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if(loginUser!=null) {
        return toAjax(yunStoreCouponIssueService.deleteYunStoreCouponIssueByIds(ids));
        }else{
            return AjaxResult.error("token无效");
        }
    }
}
