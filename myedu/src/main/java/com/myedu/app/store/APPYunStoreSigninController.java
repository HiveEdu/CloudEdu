package com.myedu.app.store;

import com.myedu.common.utils.DateUtils;
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
import com.myedu.project.parents.domain.vo.YunStudentVo;
import com.myedu.project.parents.service.IYunStudentService;
import com.myedu.project.store.domain.YunStoreSignin;
import com.myedu.project.store.domain.vo.YunStoreSigninVo;
import com.myedu.project.store.service.IYunStoreSigninService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 点名签到Controller
 * 
 * @author 梁龙飞
 * @date 2020-02-29
 */
@Api("门店学生点名签到")
@RestController
@RequestMapping("/app/store/signin")
public class APPYunStoreSigninController extends BaseController
{
    @Autowired
    private IYunStoreSigninService yunStoreSigninService;
    @Autowired
    private IYunStudentService yunStudentService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询点名签到列表
     */
    @ApiOperation("查询点名签到列表")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreSignin", value = "查询点名签到列表",
            dataType = "YunStoreSigninVo")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreSigninVo yunStoreSignin)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreSignin.setCreateById(loginUser.getUser().getUserId());
        startPage();
        List<YunStoreSigninVo> list = yunStoreSigninService.selectYunStoreSigninList(yunStoreSignin);
        return getDataTable(list);

    }


    /**
     * 获取点名签到详细信息
     */
    @ApiOperation("获取点名签到详细信息")
    @AutoIdempotent
    @ApiImplicitParam(name = "id", value = "获取点名签到详细信息",
            dataType = "Long")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value ="id", required = false) Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        AjaxResult ajax = AjaxResult.success();
        YunStudentVo yunStudentVo=new YunStudentVo();
        yunStudentVo.setCreateById(loginUser.getUser().getUserId());
        List<YunStudentVo> list = yunStudentService.selectYunStudentList(yunStudentVo);
        ajax.put("studentLists", list);
        if (StringUtils.isNotNull(id)) {
            ajax.put(AjaxResult.DATA_TAG,yunStoreSigninService.selectYunStoreSigninById(id));
        }
        return ajax;
    }

    /**
     * 新增点名签到
     */
    @ApiOperation("新增点名签到")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreSignin", value = "新增点名签到",
            dataType = "YunStoreSignin")
    @Log(title = "点名签到", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreSignin yunStoreSignin)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreSignin.setCreateById(loginUser.getUser().getUserId());
        yunStoreSignin.setCreateBy(loginUser.getUser().getNickName());
        yunStoreSignin.setCreateTime(DateUtils.getNowDate());
        return toAjax(yunStoreSigninService.insertYunStoreSignin(yunStoreSignin));
    }

    /**
     * 修改点名签到
     */
    @ApiOperation("修改点名签到")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreSignin", value = "修改点名签到",
            dataType = "YunStoreSignin")
    @Log(title = "点名签到", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreSignin yunStoreSignin)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreSignin.setUpdateBy(loginUser.getUser().getNickName());
        yunStoreSignin.setUpdateTime(DateUtils.getNowDate());
        return toAjax(yunStoreSigninService.updateYunStoreSignin(yunStoreSignin));
    }

    /**
     * 删除点名签到
     */
    @ApiOperation("删除点名签到")
    @AutoIdempotent
    @ApiImplicitParam(name = "ids", value = "删除点名签到",
            dataType = "Long[]")
    @Log(title = "点名签到", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreSigninService.deleteYunStoreSigninByIds(ids));

    }
}
