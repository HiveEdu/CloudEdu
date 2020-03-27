package com.myedu.app.parents.controller;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunStuHw;
import com.myedu.project.parents.domain.vo.YunStuHwVo;
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStuHwService;
import com.myedu.project.parents.service.IYunStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生身高体重记录Controller
 * 
 * @author  llf
 * @date 2019-12-29
 */
@Api("APP学生身高体重管理")
@RestController
@RequestMapping("/app/parents/hw")
public class AppStHwController extends BaseController
{
    @Autowired
    private IYunStuHwService yunStuHwService;
    @Autowired
    private IYunStudentService yunStudentService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询学生身高体重记录列表
     */
    @ApiOperation("查询学生身高体重记录列表")
    @ApiImplicitParam(name = "yunStuHw", value = "查询学生身高体重记录列表",
            dataType = "YunStuHwVo")
    @PreAuthorize("@ss.hasPermi('parents:hw:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStuHwVo yunStuHw)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            startPage();
            List<YunStuHwVo> list = yunStuHwService.selectYunStuHwList(yunStuHw);
            return getDataTable(list);
        }else {
            return getDataTableLose(null);
        }

    }

    /**
     * 导出学生身高体重记录列表
     */
    @ApiOperation("导出学生身高体重记录列表")
    @ApiImplicitParam(name = "yunStuHw", value = "导出学生身高体重记录列表",
            dataType = "YunStuHwVo")
    @PreAuthorize("@ss.hasPermi('parents:hw:export')")
    @Log(title = "学生身高体重记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStuHwVo yunStuHw)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            List<YunStuHwVo> list = yunStuHwService.selectYunStuHwList(yunStuHw);
            ExcelUtil<YunStuHwVo> util = new ExcelUtil<YunStuHwVo>(YunStuHwVo.class);
            return util.exportExcel(list, "hw");
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 获取学生身高体重记录详细信息
     */
    @ApiOperation("获取学生身高体重记录详细信息")
    @ApiImplicitParam(name = "id", value = "获取学生身高体重记录详细信息",
            dataType = "Long")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value ="id", required = false) Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        AjaxResult ajax = AjaxResult.success();
        if (loginUser!=null) {
            YunStudentVo yunStudentVo = new YunStudentVo();
            yunStudentVo.setCreateById(loginUser.getUser().getUserId());
            List<YunStudentVo> list = yunStudentService.selectYunStudentList(yunStudentVo);
            ajax.put("studentLists", list);
            if (StringUtils.isNotNull(id)) {
                ajax.put(AjaxResult.DATA_TAG, yunStuHwService.selectYunStuHwById(id));
            }
        }else{
            return AjaxResult.error("token无效");
        }
        return ajax;
    }

    /**
     * 新增学生身高体重记录
     */
    @ApiOperation("新增学生身高体重记录")
    @ApiImplicitParam(name = "yunStuHw", value = "新增学生身高体重记录",
            dataType = "YunStuHw")
    @PreAuthorize("@ss.hasPermi('parents:hw:add')")
    @Log(title = "学生身高体重记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStuHw yunStuHw)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            yunStuHw.setCreateById(loginUser.getUser().getUserId());
            yunStuHw.setCreateBy(loginUser.getUser().getNickName());
            return toAjax(yunStuHwService.insertYunStuHw(yunStuHw));
        }else {

            return AjaxResult.error("token无效");
        }

    }

    /**
     * 修改学生身高体重记录
     */
    @ApiOperation("修改学生身高体重记录")
    @ApiImplicitParam(name = "yunStuHw", value = "修改学生身高体重记录",
            dataType = "YunStuHw")
    @PreAuthorize("@ss.hasPermi('parents:hw:edit')")
    @Log(title = "学生身高体重记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStuHw yunStuHw)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return toAjax(yunStuHwService.updateYunStuHw(yunStuHw));
        }else {
            return AjaxResult.error("token无效");
        }

    }

    /**
     * 删除学生身高体重记录
     */
    @ApiOperation("删除学生身高体重记录")
    @ApiImplicitParam(name = "ids", value = "删除学生身高体重记录",
            dataType = "Long[]")
    @PreAuthorize("@ss.hasPermi('parents:hw:remove')")
    @Log(title = "学生身高体重记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {   LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            return toAjax(yunStuHwService.deleteYunStuHwByIds(ids));
        }else {
            return AjaxResult.error("token无效");
        }

    }
}
