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
import com.myedu.project.dataBasic.domain.SysCourse;
import com.myedu.project.dataBasic.domain.SysGrade;
import com.myedu.project.dataBasic.service.ISysCourseService;
import com.myedu.project.dataBasic.service.ISysGradeService;
import com.myedu.project.store.domain.YunCourse;
import com.myedu.project.store.domain.vo.YunCourseVo;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.enums.CourseType;
import com.myedu.project.store.service.IYunCourseService;
import com.myedu.project.store.service.IYunStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 课程Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-21
 */
@Api("门店课程设置")
@RestController
@RequestMapping("/app/store/course")
public class APPYunCourseController extends BaseController
{
    @Autowired
    private IYunCourseService yunCourseService;
    @Autowired
    private ISysGradeService sysGradeService;

    @Autowired
    private ISysCourseService sysCourseService;

    @Autowired
    private IYunStoreService yunStoreService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询课程列表
     */
    @AutoIdempotent
    @ApiOperation("查询课程列表")
    @ApiImplicitParam(name = "yunCourse", value = "查询课程列表",
            dataType = "YunCourse")
    @GetMapping("/list")
    public TableDataInfo list(YunCourse yunCourse)
    {
        startPage();
        List<YunCourseVo> list = yunCourseService.selectYunCourseList(yunCourse);
        return getDataTable(list);
    }



    /**
     * 获取课程详细信息
     */
    @ApiOperation("获取课程详细信息")
    @AutoIdempotent
    @ApiImplicitParam(name = "id", value = "获取课程详细信息",
            dataType = "Long", required = true, paramType = "path")
    @GetMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult getInfo(@PathVariable Long id)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        AjaxResult ajax = AjaxResult.success();
        SysGrade sysGrade = new SysGrade();
        ajax.put("sysGrades", sysGradeService.selectSysGradeList(sysGrade));

        SysCourse sysCourse=new  SysCourse();
        ajax.put("sysCourses", sysCourseService.selectSysCourseList(sysCourse));

        YunStoreVo yunStore = new YunStoreVo();
        yunStore.setCreateById(loginUser.getUser().getUserId());
        List<YunStoreVo> stores = yunStoreService.selectYunStoreList(yunStore);
        ajax.put("stores", stores);
        if (StringUtils.isNotNull(id)) {
            ajax.put(AjaxResult.DATA_TAG, yunCourseService.selectYunCourseById(id));
        }
        return ajax;
    }

    /**
     * 新增课程
     */
    @ApiOperation("新增课程")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunCourse", value = "新增课程",
            dataType = "YunCourse")
    @Log(title = "课程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(YunCourse yunCourse)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunCourse.setCreateById(loginUser.getUser().getUserId());
        yunCourse.setCreateBy(loginUser.getUser().getNickName());
        yunCourse.setCreateTime(DateUtils.getNowDate());
        return toAjax(yunCourseService.insertYunCourse(yunCourse));
    }

    /**
     * 修改课程
     */
    @ApiOperation("修改课程")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunCourse", value = "修改课程",
            dataType = "YunCourse")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(YunCourse yunCourse)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunCourse.setUpdateBy(loginUser.getUser().getNickName());
        yunCourse.setUpdateTime(DateUtils.getNowDate());
        return toAjax(yunCourseService.updateYunCourse(yunCourse));
    }

    /**
     * 删除课程
     */
    @ApiOperation("删除课程")
    @AutoIdempotent
    @ApiImplicitParam(name = "ids", value = "删除课程",
            dataType = "Long[]")
    @Log(title = "课程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunCourseService.deleteYunCourseByIds(ids));
    }

    /**
     * @Description :更改课程状态下线
     * @Author : 梁少鹏
     * @Date : 2020/1/21 16:30
     */
    @ApiOperation("更改课程状态下线")
    @AutoIdempotent
    @ApiImplicitParam(name = "ids", value = "更改课程状态下线",
            dataType = "Long[]")
    @Log(title = "课程", businessType = BusinessType.UPDATE)
    @GetMapping("/changeStatusOff/{ids}")
    public AjaxResult changeStatusOff(@PathVariable Long[] ids)
    {
        int rows=0;
        for (Long id:ids) {
            YunCourse yunCourse= yunCourseService.selectYunCourseById(id);
            if(yunCourse!=null){
                yunCourse.setStatus(CourseType.OFFLINE.getCode());
                rows=yunCourseService.updateYunCourse(yunCourse);
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
            YunCourse yunCourse= yunCourseService.selectYunCourseById(id);
            if(yunCourse!=null){
                yunCourse.setStatus(CourseType.ONLINE.getCode());
                rows=yunCourseService.updateYunCourse(yunCourse);
            }
        }
        return toAjax(rows);
    }
}
