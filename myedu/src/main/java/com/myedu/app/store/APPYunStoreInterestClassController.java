package com.myedu.app.store;

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
import com.myedu.project.dataBasic.domain.SysCourse;
import com.myedu.project.dataBasic.service.ISysCourseService;
import com.myedu.project.store.domain.YunStoreInterestClass;
import com.myedu.project.store.domain.vo.YunStoreInterestClassVo;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.service.IYunStoreInterestClassService;
import com.myedu.project.store.service.IYunStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店兴趣班Controller
 * 
 * @author myedu
 * @date 2020-07-19
 */
@Api("门店兴趣分班管理")
@RestController
@RequestMapping("/app/store/interclass")
public class APPYunStoreInterestClassController extends BaseController
{
    @Autowired
    private IYunStoreInterestClassService yunStoreInterestClassService;

    @Autowired
    private IYunStoreService yunStoreService;
    @Autowired
    private ISysCourseService sysCourseService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询门店兴趣班列表
     */
    @ApiOperation("查询门店兴趣班列表")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreInterestClass", value = "查询门店兴趣班列表",
            dataType = "YunStoreInterestClass")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreInterestClass yunStoreInterestClass)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreInterestClass.setCreateById(loginUser.getUser().getUserId());
        startPage();
        List<YunStoreInterestClassVo> list =
                yunStoreInterestClassService.selectYunStoreInterestClassList(yunStoreInterestClass);
        return getDataTable(list);
    }


    /**
     * 获取门店兴趣班详细信息
     */
    @ApiOperation("获取门店兴趣班详细信息")
    @AutoIdempotent
    @ApiImplicitParam(name = "id", value = "获取门店兴趣班详细信息",
            dataType = "Long")
    @GetMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        AjaxResult ajax = AjaxResult.success();
        YunStoreVo yunStore=new YunStoreVo();
        yunStore.setCreateById(loginUser.getUser().getUserId());
        List<YunStoreVo> stores=yunStoreService.selectYunStoreList(yunStore);
        ajax.put("stores", stores);
        SysCourse sysCourses=new SysCourse();
        ajax.put("sysCourses", sysCourseService.selectSysCourseList(sysCourses));
        if (StringUtils.isNotNull(id)) {
            ajax.put(AjaxResult.DATA_TAG,
                    yunStoreInterestClassService.selectYunStoreInterestClassById(id));
        }
        return ajax;
    }

    /**
     * 新增门店兴趣班
     */
    @ApiOperation("新增门店兴趣班")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreInterestClass", value = "新增门店兴趣班",
            dataType = "YunStoreInterestClass")
    @Log(title = "门店兴趣班", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreInterestClass yunStoreInterestClass)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreInterestClass.setCreateById(loginUser.getUser().getUserId());
        yunStoreInterestClass.setCreateBy(loginUser.getUser().getNickName());
        return toAjax(yunStoreInterestClassService.insertYunStoreInterestClass(yunStoreInterestClass));
    }

    /**
     * 修改门店兴趣班
     */
    @ApiOperation("修改门店兴趣班")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreInterestClass", value = "修改门店兴趣班",
            dataType = "YunStoreInterestClass")
    @Log(title = "门店兴趣班", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreInterestClass yunStoreInterestClass)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreInterestClass.setUpdateBy(loginUser.getUser().getNickName());
        yunStoreInterestClass.setUpdateById(loginUser.getUser().getUserId());
        return toAjax(yunStoreInterestClassService.updateYunStoreInterestClass(yunStoreInterestClass));
    }

    /**
     * 删除门店兴趣班
     */
    @ApiOperation("删除门店兴趣班")
    @AutoIdempotent
    @ApiImplicitParam(name = "ids", value = "删除门店兴趣班",
            dataType = "Long[]")
    @Log(title = "门店兴趣班", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreInterestClassService.deleteYunStoreInterestClassByIds(ids));
    }
}
