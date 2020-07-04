package com.myedu.app.parents.controller;

import com.myedu.common.utils.ServletUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.parents.domain.YunComplaint;
import com.myedu.project.parents.service.IYunComplaintService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 投诉Controller
 * 
 * @author myedu
 * @date 2020-01-18
 */
@Api("APP家长投诉管理")
@RestController
@RequestMapping("/app/parents/complaint")
public class AppStComplaintController extends BaseController
{
    @Autowired
    private IYunComplaintService yunComplaintService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询投诉列表
     */
    @AutoIdempotent
    @ApiOperation("查询当前用户下的投诉列表")
    @ApiImplicitParam(name = "yunComplaint", value = "查询投诉列表",
            dataType = "YunComplaint")
    @GetMapping("/list")
    public TableDataInfo list(YunComplaint yunComplaint)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null){
            startPage();
            yunComplaint.setCreateById(loginUser.getUser().getUserId());
            List<YunComplaint> list = yunComplaintService.selectYunComplaintList(yunComplaint);
            return getDataTable(list);
        }else{
            return getDataTableLose(null);
        }

    }



    /**
     * 获取投诉详细信息
     */
    @AutoIdempotent
    @ApiOperation("获取投诉详细信息")
    @ApiImplicitParam(name = "id", value = "获取投诉详细信息",
            dataType = "Long")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
      return AjaxResult.success(yunComplaintService.selectYunComplaintById(id));
    }

    /**
     * 新增投诉
     */
    @AutoIdempotent
    @ApiOperation("新增投诉")
    @ApiImplicitParam(name = "yunComplaint", value = "新增投诉",
            dataType = "YunComplaint")
    @Log(title = "投诉", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody YunComplaint yunComplaint)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunComplaint.setCreateById(loginUser.getUser().getUserId());
        yunComplaint.setCreateBy(loginUser.getUser().getNickName());
        return toAjax(yunComplaintService.insertYunComplaint(yunComplaint));
    }

    /**
     * 修改投诉
     */
    @AutoIdempotent
    @ApiOperation("修改投诉")
    @ApiImplicitParam(name = "yunComplaint", value = "修改投诉",
            dataType = "YunComplaint")
    @Log(title = "投诉", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunComplaint yunComplaint)
    {
      return toAjax(yunComplaintService.updateYunComplaint(yunComplaint));
    }

    /**
     * 删除投诉
     */
    @AutoIdempotent
    @ApiOperation("删除投诉")
    @ApiImplicitParam(name = "ids", value = "删除投诉",
            dataType = "Long[]")
    @Log(title = "投诉", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunComplaintService.deleteYunComplaintByIds(ids));
    }
}
