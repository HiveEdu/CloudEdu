package com.myedu.project.store.controller;

import java.util.Date;
import java.util.List;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.store.domain.YunStoreCouponReceive;
import com.myedu.project.store.domain.vo.YunStoreCouponIssueVo;
import com.myedu.project.store.enums.CouponIssueStatus;
import com.myedu.project.store.enums.receiveStatus;
import com.myedu.project.store.service.IYunStoreCouponReceiveService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.project.store.domain.YunStoreCouponIssue;
import com.myedu.project.store.service.IYunStoreCouponIssueService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 店铺优惠券发布Controller
 * 
 * @author 梁少鹏
 * @date 2020-02-03
 */
@RestController
@RequestMapping("/store/publishCoupon")
public class YunStoreCouponIssueController extends BaseController
{
    @Autowired
    private IYunStoreCouponIssueService yunStoreCouponIssueService;

    @Autowired
    private IYunStoreCouponReceiveService yunStoreCouponReceiveService;

    /**
     * 查询店铺优惠券发布列表
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreCouponIssueVo yunStoreCouponIssue)
    {
        startPage();
        List<YunStoreCouponIssueVo> list = yunStoreCouponIssueService.selectYunStoreCouponIssueList(yunStoreCouponIssue);
        return getDataTable(list);
    }

    /**
     * 导出店铺优惠券发布列表
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:export')")
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
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        YunStoreCouponReceive yunStoreCouponReceive=new YunStoreCouponReceive();
        yunStoreCouponReceive.setSciId(id);
        ajax.put("yunStoreCouponReceives", yunStoreCouponReceiveService.selectYunStoreCouponReceiveList(yunStoreCouponReceive));
        ajax.put(AjaxResult.DATA_TAG, yunStoreCouponIssueService.selectYunStoreCouponIssueById(id));
        return ajax;
    }

    /**
     * 领用店铺优惠券
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:receive')")
    @GetMapping(value = "/receive/{id}")
    public AjaxResult receive(@PathVariable("id") Long id)
    {
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
    }

    /**
     * 新增店铺优惠券发布
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:add')")
    @Log(title = "店铺优惠券发布", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreCouponIssue yunStoreCouponIssue)
    {
        return toAjax(yunStoreCouponIssueService.insertYunStoreCouponIssue(yunStoreCouponIssue));
    }

    /**
     * 修改店铺优惠券发布
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:edit')")
    @Log(title = "店铺优惠券发布", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreCouponIssue yunStoreCouponIssue)
    {
        return toAjax(yunStoreCouponIssueService.updateYunStoreCouponIssue(yunStoreCouponIssue));
    }

    /**
     * 删除店铺优惠券发布
     */
    @PreAuthorize("@ss.hasPermi('store:publishCoupon:remove')")
    @Log(title = "店铺优惠券发布", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(yunStoreCouponIssueService.deleteYunStoreCouponIssueByIds(ids));
    }
}
