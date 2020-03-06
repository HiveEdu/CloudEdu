package com.myedu.project.store.controller;

import java.util.List;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.domain.YunStoreSignin;
import com.myedu.project.store.domain.vo.YunStoreStuVo;
import com.myedu.project.store.enums.StoreStatus;
import com.myedu.project.store.enums.StudengStatus;
import com.myedu.project.store.service.IYunStoreSigninService;
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
import com.myedu.project.store.domain.YunStoreStu;
import com.myedu.project.store.service.IYunStoreStuService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 门店学生管理Controller
 * 
 * @author 梁少鹏
 * @date 2020-03-03
 */
@RestController
@RequestMapping("/store/storeStudent")
public class YunStoreStuController extends BaseController
{
    @Autowired
    private IYunStoreStuService yunStoreStuService;
    @Autowired
    private IYunStoreSigninService yunStoreSigninService;
    /**
     * 查询门店学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreStuVo yunStoreStu)
    {
        startPage();
        List<YunStoreStuVo> list = yunStoreStuService.selectYunStoreStuList(yunStoreStu);
        return getDataTable(list);
    }

    /**
     * 导出门店学生管理列表
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:export')")
    @Log(title = "门店学生管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreStuVo yunStoreStu)
    {
        List<YunStoreStuVo> list = yunStoreStuService.selectYunStoreStuList(yunStoreStu);
        ExcelUtil<YunStoreStuVo> util = new ExcelUtil<YunStoreStuVo>(YunStoreStuVo.class);
        return util.exportExcel(list, "storeStudent");
    }

    /**
     * 获取门店学生管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunStoreStuService.selectYunStoreStuById(id));
    }

    /**
     * 新增门店学生管理
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:add')")
    @Log(title = "门店学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreStu yunStoreStu)
    {
        return toAjax(yunStoreStuService.insertYunStoreStu(yunStoreStu));
    }

    /**
     * 修改门店学生管理
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:edit')")
    @Log(title = "门店学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreStu yunStoreStu)
    {
        return toAjax(yunStoreStuService.updateYunStoreStu(yunStoreStu));
    }

    /**
     * 删除门店学生管理
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:remove')")
    @Log(title = "门店学生管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreStuService.deleteYunStoreStuByIds(ids));
    }


    /*
     * @Description :更改学生状态离校
     * @Author : 梁少鹏
     * @Date : 2020/3/6 16:30
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:changeStatusOff')")
    @Log(title = "更改学生状态离校", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOff/{ids}")
    public AjaxResult changeStatusOff(@PathVariable Long[] ids)
    {
        int rows=0;
        for (Long id:ids) {
            YunStoreStu yunStoreStu= yunStoreStuService.selectYunStoreStuById(id);
            if(yunStoreStu!=null){
                yunStoreStu.setStatus(StudengStatus.OFFLINE.getCode());
                rows=yunStoreStuService.updateYunStoreStu(yunStoreStu);
            }
        }
        return toAjax(rows);
    }

    /*
     * @Description :更改学生状态在校
     * @Author : 梁少鹏
     * @Date : 2020/3/6 16:30
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:changeStatusOn')")
    @Log(title = "更改学生状态在校", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOn/{ids}")
    public AjaxResult changeStatusOn(@PathVariable Long[] ids)
    {
        int rows=0;
        for (Long id:ids) {
            YunStoreStu yunStoreStu= yunStoreStuService.selectYunStoreStuById(id);
            if(yunStoreStu!=null){
                yunStoreStu.setStatus(StudengStatus.ONLINE.getCode());
                rows=yunStoreStuService.updateYunStoreStu(yunStoreStu);
            }
        }
        return toAjax(rows);
    }

    /*
     * @Description :签到
     * @Author : 梁少鹏
     * @Date : 2020/3/6 16:30
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:sigint')")
    @Log(title = "签到", businessType = BusinessType.UPDATE)
    @GetMapping("/sigint/{ids}")
    public AjaxResult sigint(@PathVariable Long[] ids)
    {
        int rows=0;
        for (Long id:ids) {
            YunStoreStu yunStoreStu= yunStoreStuService.selectYunStoreStuById(id);
            if(yunStoreStu!=null){
                yunStoreStu.setSigin(yunStoreStu.getSigin()+1);//签到次数加1
                rows=yunStoreStuService.updateYunStoreStu(yunStoreStu);
                if(rows>0){
                    YunStoreSignin yunStoreSignin=new YunStoreSignin();
                    yunStoreSignin.setStoreId(yunStoreStu.getStoreId());
                    yunStoreSignin.setStuId(yunStoreStu.getStuId());
                    yunStoreSignin.setSigninType("0");//签到
                    yunStoreSignin.setCreateById(SecurityUtils.getUserId());
                    yunStoreSignin.setCreateBy(SecurityUtils.getUsername());
                    yunStoreSignin.setCreateTime(DateUtils.getNowDate());
                    yunStoreSigninService.insertYunStoreSignin(yunStoreSignin);
                }
            }
        }
        return toAjax(rows);
    }

    /*
     * @Description :签退
     * @Author : 梁少鹏
     * @Date : 2020/3/6 16:30
     */
    @PreAuthorize("@ss.hasPermi('store:storeStudent:sigout')")
    @Log(title = "签退", businessType = BusinessType.UPDATE)
    @GetMapping("/sigout/{ids}")
    public AjaxResult sigout(@PathVariable Long[] ids)
    {
        int rows=0;
        for (Long id:ids) {
            YunStoreStu yunStoreStu= yunStoreStuService.selectYunStoreStuById(id);
            if(yunStoreStu!=null){
                yunStoreStu.setSigout(yunStoreStu.getSigout()+1);//签退次数加1
                rows=yunStoreStuService.updateYunStoreStu(yunStoreStu);
                if(rows>0){
                    YunStoreSignin yunStoreSignin=new YunStoreSignin();
                    yunStoreSignin.setStoreId(yunStoreStu.getStoreId());
                    yunStoreSignin.setStuId(yunStoreStu.getStuId());
                    yunStoreSignin.setSigninType("1");//签退
                    yunStoreSignin.setCreateById(SecurityUtils.getUserId());
                    yunStoreSignin.setCreateBy(SecurityUtils.getUsername());
                    yunStoreSignin.setCreateTime(DateUtils.getNowDate());
                    yunStoreSigninService.insertYunStoreSignin(yunStoreSignin);
                }
            }
        }
        return toAjax(rows);
    }
}
