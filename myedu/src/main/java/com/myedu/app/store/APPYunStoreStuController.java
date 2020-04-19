package com.myedu.app.store;

import com.myedu.common.utils.DateUtils;
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
import com.myedu.project.store.domain.YunStoreSignin;
import com.myedu.project.store.domain.YunStoreStu;
import com.myedu.project.store.domain.vo.YunStoreStuVo;
import com.myedu.project.store.enums.StudengStatus;
import com.myedu.project.store.service.IYunStoreSigninService;
import com.myedu.project.store.service.IYunStoreStuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店学生管理Controller
 * 
 * @author 梁少鹏
 * @date 2020-03-03
 */
@Api("门店学生管理")
@RestController
@RequestMapping("/app/store/storeStudent")
public class APPYunStoreStuController extends BaseController
{
    @Autowired
    private IYunStoreStuService yunStoreStuService;
    @Autowired
    private IYunStoreSigninService yunStoreSigninService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询门店学生管理列表
     */
    @ApiOperation("查询门店学生管理列表")
    @ApiImplicitParam(name = "yunStoreStu", value = "查询门店学生管理列表",
            dataType = "YunStoreStuVo")
    public TableDataInfo list(YunStoreStuVo yunStoreStu)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            startPage();
            List<YunStoreStuVo> list = yunStoreStuService.selectYunStoreStuList(yunStoreStu);
            return getDataTable(list);
        }else {
            return getDataTableLose(null);
        }

    }

    /**
     * 导出门店学生管理列表
     */
    @ApiOperation("导出门店学生管理列表")
    @ApiImplicitParam(name = "yunStoreStu", value = "导出门店学生管理列表",
            dataType = "YunStoreStuVo")
    @Log(title = "门店学生管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreStuVo yunStoreStu)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            List<YunStoreStuVo> list = yunStoreStuService.selectYunStoreStuList(yunStoreStu);
            ExcelUtil<YunStoreStuVo> util = new ExcelUtil<YunStoreStuVo>(YunStoreStuVo.class);
            return util.exportExcel(list, "storeStudent");
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 获取门店学生管理详细信息
     */
    @ApiOperation("获取门店学生管理详细信息")
    @ApiImplicitParam(name = "id", value = "获取门店学生管理详细信息",
            dataType = "Long")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return AjaxResult.success(yunStoreStuService.selectYunStoreStuById(id));
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 新增门店学生管理
     */
    @ApiOperation("新增门店学生管理")
    @ApiImplicitParam(name = "yunStoreStu", value = "新增门店学生管理",
            dataType = "YunStoreStu")
    @Log(title = "门店学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreStu yunStoreStu)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return toAjax(yunStoreStuService.insertYunStoreStu(yunStoreStu));
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 修改门店学生管理
     */
    @ApiOperation("修改门店学生管理")
    @ApiImplicitParam(name = "yunStoreStu", value = "修改门店学生管理",
            dataType = "YunStoreStu")
    @Log(title = "门店学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreStu yunStoreStu)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return toAjax(yunStoreStuService.updateYunStoreStu(yunStoreStu));
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 删除门店学生管理
     */
    @ApiOperation("删除门店学生管理")
    @ApiImplicitParam(name = "ids", value = "删除门店学生管理",
            dataType = "Long[]")
    @Log(title = "门店学生管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return toAjax(yunStoreStuService.deleteYunStoreStuByIds(ids));
        }else {
            return AjaxResult.error("token无效");
        }

    }


    /**
     * @Description :更改学生状态离校
     * @Author : 梁少鹏
     * @Date : 2020/3/6 16:30
     */
    @ApiOperation("更改学生状态离校")
    @ApiImplicitParam(name = "ids", value = "更改学生状态离校",
            dataType = "Long[]")
    @Log(title = "更改学生状态离校", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOff/{ids}")
    public AjaxResult changeStatusOff(@PathVariable Long[] ids)
    {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            int rows=0;
            for (Long id:ids) {
                YunStoreStu yunStoreStu= yunStoreStuService.selectYunStoreStuById(id);
                if(yunStoreStu!=null){
                    yunStoreStu.setStatus(StudengStatus.OFFLINE.getCode());
                    rows=yunStoreStuService.updateYunStoreStu(yunStoreStu);
                }
            }
            return toAjax(rows);
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * @Description :更改学生状态在校
     * @Author : 梁少鹏
     * @Date : 2020/3/6 16:30
     */
    @ApiOperation("更改学生状态在校")
    @ApiImplicitParam(name = "ids", value = "更改学生状态在校",
            dataType = "Long[]")
    @Log(title = "更改学生状态在校", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOn/{ids}")
    public AjaxResult changeStatusOn(@PathVariable Long[] ids)
    {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            int rows=0;
            for (Long id:ids) {
                YunStoreStu yunStoreStu= yunStoreStuService.selectYunStoreStuById(id);
                if(yunStoreStu!=null){
                    yunStoreStu.setStatus(StudengStatus.ONLINE.getCode());
                    rows=yunStoreStuService.updateYunStoreStu(yunStoreStu);
                }
            }
            return toAjax(rows);
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * @Description :签到
     * @Author : 梁少鹏
     * @Date : 2020/3/6 16:30
     */
    @ApiOperation("签到")
    @ApiImplicitParam(name = "ids", value = "签到",
            dataType = "Long[]")
    @Log(title = "签到", businessType = BusinessType.UPDATE)
    @GetMapping("/sigint/{ids}")
    public AjaxResult sigint(@PathVariable Long[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
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
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * @Description :签退
     * @Author : 梁少鹏
     * @Date : 2020/3/6 16:30
     */
    @ApiOperation("签退")
    @ApiImplicitParam(name = "ids", value = "签退",
            dataType = "Long[]")
    @Log(title = "签退", businessType = BusinessType.UPDATE)
    @GetMapping("/sigout/{ids}")
    public AjaxResult sigout(@PathVariable Long[] ids)
    {

        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
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
        }else {
            return AjaxResult.error("token无效");
        }

    }
}
