package com.myedu.app.hometeacher;

import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysCourse;
import com.myedu.project.dataBasic.service.ISysCourseService;
import com.myedu.project.hometeacher.domain.YunUserInfo;
import com.myedu.project.hometeacher.domain.vo.YunUserInfoVo;
import com.myedu.project.hometeacher.service.IYunUserInfoService;
import com.myedu.project.store.enums.StoreStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 家教老师表Controller
 * 
 * @author 梁龙飞
 * @date 2020-02-10
 */
@Api("家教老师信息管理")
@RestController
@RequestMapping("/app/hometeacher/info")
public class APPYunUserInfoController extends BaseController
{
    @Autowired
    private IYunUserInfoService yunUserInfoService;

    @Autowired
    private ISysCourseService sysCourseService;

    /**
     * 查询家教老师表列表
     */
    @ApiOperation("查询家教老师表列表")
    @ApiImplicitParam(name = "yunUserInfo", value = "查询家教老师表列表",
            dataType = "YunUserInfo")
    @PreAuthorize("@ss.hasPermi('hometeacher:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunUserInfo yunUserInfo)
    {
        startPage();
        List<YunUserInfoVo> list = yunUserInfoService.selectYunUserInfoList(yunUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出家教老师表列表
     */
    @ApiOperation("导出家教老师表列表")
    @ApiImplicitParam(name = "yunUserInfo", value = "导出家教老师表列表",
            dataType = "YunUserInfo")
    @PreAuthorize("@ss.hasPermi('hometeacher:info:export')")
    @Log(title = "家教老师表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunUserInfo yunUserInfo)
    {
        List<YunUserInfoVo> list = yunUserInfoService.selectYunUserInfoList(yunUserInfo);
        ExcelUtil<YunUserInfoVo> util = new ExcelUtil<YunUserInfoVo>(YunUserInfoVo.class);
        return util.exportExcel(list, "info");
    }

    /**
     * 获取家教老师表详细信息
     */
    @ApiOperation("获取家教老师表详细信息")
    @ApiImplicitParam(name = "id", value = "获取家教老师表详细信息",
            dataType = "Long")
    @PreAuthorize("@ss.hasPermi('hometeacher:info:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        //获取课程列表信息
        SysCourse sysCourse = new SysCourse();
        ajax.put("sysCourses", sysCourseService.selectSysCourseList(sysCourse));
        if(id!=null){
            ajax.put(AjaxResult.DATA_TAG,yunUserInfoService.selectYunUserInfoById(id));
            return ajax;
        }else{
            YunUserInfo yunUserInfo=new YunUserInfo();
            List<YunUserInfoVo> yunUserInfos=  yunUserInfoService.selectYunUserInfoList(yunUserInfo);
            if(yunUserInfos.size()>0){
                ajax.put("CODE_TAG",204);
                ajax.put("MSG_TAG","您的个人信息已经存在");
                return ajax;
            }else{
                ajax.put("CODE_TAG",200);
                ajax.put("MSG_TAG","您可以创建个人信息");
                return ajax;
            }
        }

    }

    /**
     * 新增家教老师表
     */
    @ApiOperation("新增家教老师表")
    @ApiImplicitParam(name = "yunUserInfo", value = "新增家教老师表",
            dataType = "YunUserInfo")
    @PreAuthorize("@ss.hasPermi('hometeacher:info:add')")
    @Log(title = "家教老师表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunUserInfo yunUserInfo)
    {
        return toAjax(yunUserInfoService.insertYunUserInfo(yunUserInfo));

    }

    /**
     * 修改家教老师表
     */
    @ApiOperation("修改家教老师表")
    @ApiImplicitParam(name = "yunUserInfo", value = "修改家教老师表",
            dataType = "YunUserInfo")
    @PreAuthorize("@ss.hasPermi('hometeacher:info:edit')")
    @Log(title = "家教老师表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunUserInfo yunUserInfo)
    {
        return toAjax(yunUserInfoService.updateYunUserInfo(yunUserInfo));
    }

    /**
     * 删除家教老师表
     */
    @ApiOperation("删除家教老师表")
    @ApiImplicitParam(name = "ids", value = "删除家教老师表",
            dataType = "Long[]")
    @PreAuthorize("@ss.hasPermi('hometeacher:info:remove')")
    @Log(title = "家教老师表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunUserInfoService.deleteYunUserInfoByIds(ids));
    }


    /**
     * @Description :更改课程状态下线
     * @Author : 梁少鹏
     * @Date : 2020/1/21 16:30
     */
    @ApiOperation("更改课程状态下线")
    @ApiImplicitParam(name = "ids", value = "更改课程状态下线",
            dataType = "Long[]")
    @PreAuthorize("@ss.hasPermi('store:course:changeStatusOff')")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOff/{ids}")
    public AjaxResult changeStatusOff(@PathVariable Long[] ids)
    {
        int rows=0;
        for (Long id:ids) {
            YunUserInfo yunUserInfo= yunUserInfoService.selectYunUserInfoById(id);
            if(yunUserInfo!=null){
                yunUserInfo.setStatus(StoreStatus.OFFLINE.getCode());
                rows=yunUserInfoService.updateYunUserInfo(yunUserInfo);
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
    @ApiImplicitParam(name = "ids", value = "更改课程状态在售",
            dataType = "Long[]")
    @PreAuthorize("@ss.hasPermi('store:course:changeStatusOn')")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOn/{ids}")
    public AjaxResult changeStatusOn(@PathVariable Long[] ids)
    {
        int rows=0;
        for (Long id:ids) {
            YunUserInfo yunUserInfo= yunUserInfoService.selectYunUserInfoById(id);
            if(yunUserInfo!=null){
                yunUserInfo.setStatus(StoreStatus.ONLINE.getCode());
                rows=yunUserInfoService.updateYunUserInfo(yunUserInfo);
            }
        }
        return toAjax(rows);
    }

}
