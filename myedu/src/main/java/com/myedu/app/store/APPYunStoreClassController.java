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
import com.myedu.project.store.domain.YunStoreClass;
import com.myedu.project.store.domain.vo.YunStoreClassVo;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.service.IYunStoreClassService;
import com.myedu.project.store.service.IYunStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分班管理Controller
 * 
 * @author 梁龙飞
 * @date 2020-04-18
 */
@Api("门店分班管理")
@RestController
@RequestMapping("/app/store/class")
public class APPYunStoreClassController extends BaseController
{
    @Autowired
    private IYunStoreClassService yunStoreClassService;
    @Autowired
    private IYunStoreService yunStoreService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询分班管理列表
     */
    @ApiOperation("查询分班管理列表")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreClass", value = "查询分班管理列表",
            dataType = "YunStoreClass")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreClass yunStoreClass)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreClass.setCreateById(loginUser.getUser().getUserId());
        startPage();
        List<YunStoreClassVo> list = yunStoreClassService.selectYunStoreClassList(yunStoreClass);
        return getDataTable(list);

    }

    /**
     * 获取分班管理详细信息
     */
    @ApiOperation("获取分班管理详细信息")
    @AutoIdempotent
    @ApiImplicitParam(name = "id", value = "获取分班管理详细信息",
            dataType = "Long")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        AjaxResult ajax = AjaxResult.success();
        YunStoreVo yunStore=new YunStoreVo();
        yunStore.setCreateById(loginUser.getUser().getUserId());
        List<YunStoreVo> stores=yunStoreService.selectYunStoreList(yunStore);
        ajax.put("stores", stores);
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG,yunStoreClassService.selectYunStoreClassById(id));
        }
        return ajax;

    }

    /**
     * 新增分班管理
     */
    @ApiOperation("新增分班管理")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreClass", value = "新增分班管理",
            dataType = "YunStoreClass")
    @Log(title = "分班管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreClass yunStoreClass)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreClass.setCreateById(loginUser.getUser().getUserId());
        yunStoreClass.setCreateBy(loginUser.getUser().getNickName());
        yunStoreClass.setCreateTime(DateUtils.getNowDate());
        return toAjax(yunStoreClassService.insertYunStoreClass(yunStoreClass));
    }

    /**
     * 修改分班管理
     */
    @ApiOperation("修改分班管理")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreClass", value = "修改分班管理",
            dataType = "YunStoreClass")
    @Log(title = "分班管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreClass yunStoreClass)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreClass.setUpdateById(loginUser.getUser().getUserId());
        yunStoreClass.setUpdateBy(loginUser.getUser().getNickName());
        yunStoreClass.setUpdateTime(DateUtils.getNowDate());
        return toAjax(yunStoreClassService.updateYunStoreClass(yunStoreClass));
    }

    /**
     * 删除分班管理
     */
    @ApiOperation("删除分班管理")
    @AutoIdempotent
    @ApiImplicitParam(name = "ids", value = "删除分班管理",
            dataType = "Long[]")
    @Log(title = "分班管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreClassService.deleteYunStoreClassByIds(ids));
    }
}
