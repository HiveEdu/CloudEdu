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
import com.myedu.project.dataBasic.domain.SysLabel;
import com.myedu.project.dataBasic.domain.SysStoreType;
import com.myedu.project.dataBasic.service.ISysLabelService;
import com.myedu.project.dataBasic.service.ISysStoreTypeService;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.enums.StoreStatus;
import com.myedu.project.store.enums.StoryType;
import com.myedu.project.store.enums.labelType;
import com.myedu.project.store.service.IYunStoreHitsService;
import com.myedu.project.store.service.IYunStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 门店Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@Api("门店管理")
@Validated
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
    @Autowired
    private IYunStoreHitsService yunStoreHitsService;
    /**
     * 查询门店列表
     */
    @ApiOperation("查询门店列表")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStore", value = "查询门店列表",
            dataType = "YunStore")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreVo yunStore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        yunStore.setCreateById(loginUser.getUser().getUserId());
        List<YunStoreVo> list = yunStoreService.selectYunStoreList(yunStore);
        return getDataTable(list);
    }

    @AutoIdempotent
    @ApiOperation("查询当前用户下的门店列表")
    @ApiImplicitParam(name = "yunStoreVo", value = "查询当前用户下的门店列表",
            dataType = "YunStoreVo")
    @GetMapping("/getMyStores")
    public TableDataInfo  getMyStudent(YunStoreVo yunStoreVo)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null){
            startPage();
            yunStoreVo.setCreateById(loginUser.getUser().getUserId());
            List<YunStoreVo> list = yunStoreService.selectYunStoreList(yunStoreVo);
            return getDataTable(list);
        }else{
            return getDataTableLose(null);
        }
    }


    /**
     * 获取门店详细信息
     */
    @ApiOperation("获取门店详细信息")
    @AutoIdempotent
    @ApiImplicitParam(name = "id", value = "获取门店详细信息",
            dataType = "Long", required = true, paramType = "path")
    @GetMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult getInfo(@PathVariable Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        AjaxResult ajax = AjaxResult.success();
        SysStoreType sysStoreType=new SysStoreType();
        sysStoreType.setType(StoryType.STORE.getCode());
        ajax.put("storeTypes", sysStoreTypeService.selectSysStoreTypeList(sysStoreType));

        SysLabel sysLabel =new SysLabel();
        sysLabel.setType(labelType.STORE.getCode());
        ajax.put("storeLabels", syslabelService.selectSysLabelList(sysLabel));
        if (StringUtils.isNotNull(id))
        {
            YunStoreVo yunStoreVo=yunStoreService.selectYunStoreById(id);
            //增加点击记录到redis中
            yunStoreHitsService.savehitsRedis(id, loginUser.getUser().getUserId());
            ajax.put(AjaxResult.DATA_TAG, yunStoreService.selectYunStoreById(id));
            ajax.put("storeTypeIds", sysStoreTypeService.selectStoreTypeListByStoreId(id));
            ajax.put("storeLabelIds", syslabelService.selectLabelListById(id));
        }

        return ajax;
    }

    /**
     * 新增门店
     */
    @ApiOperation("新增门店")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStore", value = "新增门店",
            dataType = "YunStore")
    @Log(title = "门店", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody YunStore yunStore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStore.setCreateById(loginUser.getUser().getUserId());
        yunStore.setCreateBy(loginUser.getUser().getNickName());
        yunStore.setCreateTime(DateUtils.getNowDate());
        return toAjax(yunStoreService.insertYunStore(yunStore));
    }

    /**
     * 修改门店
     */
    @ApiOperation("修改门店")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStore", value = "修改门店",
            dataType = "YunStore")
    @Log(title = "门店", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody YunStore yunStore)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStore.setUpdateBy(loginUser.getUser().getNickName());
        yunStore.setUpdateTime(DateUtils.getNowDate());
        return toAjax(yunStoreService.updateYunStore(yunStore));
    }

    /**
     * 删除门店
     */
    @ApiOperation("删除门店")
    @AutoIdempotent
    @ApiImplicitParam(name = "ids", value = "删除门店",
            dataType = "Long[]")
    @Log(title = "门店", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreService.deleteYunStoreByIds(ids));
    }

    /**
     * @Description :更改课程状态下线
     * @Author : 梁少鹏
     * @Date : 2020/1/21 16:30
     */
    @ApiOperation("课程状态下线")
    @AutoIdempotent
    @ApiImplicitParam(name = "ids", value = "课程状态下线",
            dataType = "Long[]")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOff/{ids}")
    public AjaxResult changeStatusOff(@PathVariable Long[] ids)
    {
        int rows=0;
        for (Long id:ids) {
            YunStore yunStore= yunStoreService.selectYunStoreById(id);
            if(yunStore!=null){
                yunStore.setStatus(StoreStatus.OFFLINE.getCode());
                rows=yunStoreService.updateYunStore(yunStore);
            }
        }
        return toAjax(rows);
    }

    /**
     * @Description :更改课程状态在售
     * @Author : 梁少鹏
     * @Date : 2020/1/21 16:30
     */
    @ApiOperation("更改课程状态在售")
    @AutoIdempotent
    @ApiImplicitParam(name = "ids", value = "更改课程状态在售",
            dataType = "Long[]")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOn/{ids}")
    public AjaxResult changeStatusOn(@PathVariable Long[] ids)
    {
        int rows=0;
        for (Long id:ids) {
            YunStore yunStore= yunStoreService.selectYunStoreById(id);
            if(yunStore!=null){
                yunStore.setStatus(StoreStatus.ONLINE.getCode());
                rows=yunStoreService.updateYunStore(yunStore);
            }
        }
        return toAjax(rows);
    }
}
