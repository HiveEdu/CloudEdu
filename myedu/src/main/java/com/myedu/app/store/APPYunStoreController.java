package com.myedu.app.store;

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
import com.myedu.project.dataBasic.domain.SysLabel;
import com.myedu.project.dataBasic.domain.SysStoreType;
import com.myedu.project.dataBasic.service.ISysLabelService;
import com.myedu.project.dataBasic.service.ISysStoreTypeService;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.enums.StoreStatus;
import com.myedu.project.store.enums.StoryType;
import com.myedu.project.store.enums.labelType;
import com.myedu.project.store.service.IYunStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@Api("门店管理")
@RestController
@RequestMapping("/app/store/store")
public class APPYunStoreController extends BaseController
{
    @Autowired
    private IYunStoreService yunStoreService;

    @Autowired
    private ISysStoreTypeService sysStoreTypeService;

    @Autowired
    private ISysLabelService syslabelService;

    @Autowired
    private TokenService tokenService;
    /**
     * 查询门店列表
     */
    @ApiOperation("查询门店列表")
    @ApiImplicitParam(name = "yunStore", value = "查询门店列表",
            dataType = "YunStore")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreVo yunStore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
        startPage();
        List<YunStoreVo> list = yunStoreService.selectYunStoreList(yunStore);
        return getDataTable(list);
        }else{
            return getDataTableLose(null);
        }
    }

    /**
     * 导出门店列表
     */
    @ApiOperation("导出门店列表")
    @ApiImplicitParam(name = "yunStore", value = "导出门店列表",
            dataType = "YunStore")
    @Log(title = "门店", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunStoreVo yunStore)
    {
        List<YunStoreVo> list = yunStoreService.selectYunStoreList(yunStore);
        ExcelUtil<YunStoreVo> util = new ExcelUtil<YunStoreVo>(YunStoreVo.class);
        return util.exportExcel(list, "store");
    }

    /**
     * 获取门店详细信息
     */
    @ApiOperation("获取门店详细信息")
    @ApiImplicitParam(name = "id", value = "获取门店详细信息",
            dataType = "Long", required = true, paramType = "path")
    @GetMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult getInfo(@PathVariable Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if(loginUser!=null) {
        AjaxResult ajax = AjaxResult.success();
        SysStoreType sysStoreType=new SysStoreType();
        sysStoreType.setType(StoryType.STORE.getCode());
        ajax.put("storeTypes", sysStoreTypeService.selectSysStoreTypeList(sysStoreType));

        SysLabel sysLabel =new SysLabel();
        sysLabel.setType(labelType.STORE.getCode());
        ajax.put("storeLabels", syslabelService.selectSysLabelList(sysLabel));
        if (StringUtils.isNotNull(id))
        {
            ajax.put(AjaxResult.DATA_TAG, yunStoreService.selectYunStoreById(id));
            ajax.put("storeTypeIds", sysStoreTypeService.selectStoreTypeListByStoreId(id));
            ajax.put("storeLabelIds", syslabelService.selectLabelListById(id));
        }
        return ajax;
        }else{
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 新增门店
     */
    @ApiOperation("新增门店")
    @ApiImplicitParam(name = "yunStore", value = "新增门店",
            dataType = "YunStore")
    @Log(title = "门店", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(YunStore yunStore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
        yunStore.setCreateById(SecurityUtils.getUserId());
        yunStore.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunStoreService.insertYunStore(yunStore));
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 修改门店
     */
    @ApiOperation("修改门店")
    @ApiImplicitParam(name = "yunStore", value = "修改门店",
            dataType = "YunStore")
    @Log(title = "门店", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(YunStore yunStore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
        yunStore.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunStoreService.updateYunStore(yunStore));
        }else {
            return AjaxResult.error("token无效");
        }
    }

    /**
     * 删除门店
     */
    @ApiOperation("删除门店")
    @ApiImplicitParam(name = "ids", value = "删除门店",
            dataType = "Long[]")
    @Log(title = "门店", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
          return toAjax(yunStoreService.deleteYunStoreByIds(ids));
        }else{
            return AjaxResult.error("token无效");
        }
    }

    /*
     * @Description :更改课程状态下线
     * @Author : 梁少鹏
     * @Date : 2020/1/21 16:30
     */
    @ApiOperation("课程状态下线")
    @ApiImplicitParam(name = "ids", value = "课程状态下线",
            dataType = "Long[]")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOff/{ids}")
    public AjaxResult changeStatusOff(@PathVariable Long[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
        int rows=0;
        for (Long id:ids) {
            YunStore yunStore= yunStoreService.selectYunStoreById(id);
            if(yunStore!=null){
                yunStore.setStatus(StoreStatus.OFFLINE.getCode());
                rows=yunStoreService.updateYunStore(yunStore);
            }
        }
        return toAjax(rows);
        }else{
            return AjaxResult.error("token无效");
        }
    }

    /*
     * @Description :更改课程状态在售
     * @Author : 梁少鹏
     * @Date : 2020/1/21 16:30
     */
    @ApiOperation("更改课程状态在售")
    @ApiImplicitParam(name = "ids", value = "更改课程状态在售",
            dataType = "Long[]")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOn/{ids}")
    public AjaxResult changeStatusOn(@PathVariable Long[] ids)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
        int rows=0;
        for (Long id:ids) {
            YunStore yunStore= yunStoreService.selectYunStoreById(id);
            if(yunStore!=null){
                yunStore.setStatus(StoreStatus.ONLINE.getCode());
                rows=yunStoreService.updateYunStore(yunStore);
            }
        }
        return toAjax(rows);
            }else{
            return AjaxResult.error("token无效");
        }
    }
}
