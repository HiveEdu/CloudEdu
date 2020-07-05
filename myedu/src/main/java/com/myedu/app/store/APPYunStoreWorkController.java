package com.myedu.app.store;

import com.myedu.common.utils.SecurityUtils;
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
import com.myedu.project.store.domain.YunStoreWork;
import com.myedu.project.store.domain.YunStoreWorkStu;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.domain.vo.YunStoreWorkVo;
import com.myedu.project.store.service.IYunStoreService;
import com.myedu.project.store.service.IYunStoreWorkService;
import com.myedu.project.store.service.IYunStoreWorkStuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 门店作业管理Controller
 * 
 * @author lianglongfei
 * @date 2020-07-05
 */
@Api("门店作业管理")
@RestController
@RequestMapping("/app/store/storework")
public class APPYunStoreWorkController extends BaseController
{
    @Autowired
    private IYunStoreWorkService yunStoreWorkService;
    @Autowired
    private IYunStoreService yunStoreService;
    @Autowired
    private IYunStudentService yunStudentService;
    @Autowired
    private IYunStoreWorkStuService yunStoreWorkStuService;
    @Autowired
    private TokenService tokenService;
    /**
     * 查询门店作业管理列表
     */
    @ApiOperation("查询门店作业管理列表")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreWork", value = "查询门店作业管理列表",
            dataType = "YunStoreWork")
    public TableDataInfo list(YunStoreWork yunStoreWork)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreWork.setCreateById(loginUser.getUser().getUserId());
        startPage();
        List<YunStoreWorkVo> list = yunStoreWorkService.selectYunStoreWorkList(yunStoreWork);
        return getDataTable(list);
    }

    /**
     * 获取门店作业管理详细信息
     */
    @ApiOperation("获取门店作业管理详细信息")
    @AutoIdempotent
    @ApiImplicitParam(name = "id", value = "获取门店作业管理详细信息",
            dataType = "Long")
    @GetMapping(value = { "/", "/{id}" }, produces = MediaType.APPLICATION_JSON_VALUE)
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        YunStudentVo yunStus= new YunStudentVo();
        ajax.put("yunStuents", yunStudentService.selectYunStudentList(yunStus));
        YunStoreVo yunStore=new YunStoreVo();
        yunStore.setCreateById(SecurityUtils.getUserId());
        List<YunStoreVo> stores=yunStoreService.selectYunStoreList(yunStore);
        ajax.put("stores", stores);
        if (StringUtils.isNotNull(id))
        {
            YunStoreWorkVo yunStoreWork=yunStoreWorkService.selectYunStoreWorkById(id);
            YunStoreWorkStu yunStoreWorkStu=new YunStoreWorkStu();
            yunStoreWorkStu.setStoreWorkId(id);
            List<YunStoreWorkStu>  yunStoreWorkStus=yunStoreWorkStuService.selectYunStoreWorkStuList(yunStoreWorkStu);
            String stuIds="";
            for (YunStoreWorkStu y:yunStoreWorkStus) {
                stuIds=stuIds+y.getStuId()+",";
            }
            stuIds="["+stuIds.substring(0,stuIds.length()-1)+"]";
            yunStoreWork.setStuIds(stuIds);
            ajax.put(AjaxResult.DATA_TAG,yunStoreWork);
        }
        return ajax;

    }

    /**
     * 新增门店作业管理
     */
    @ApiOperation("新增门店作业管理")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreWork", value = "新增门店作业管理",
            dataType = "YunStoreWorkVo")
    @Log(title = "门店作业管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunStoreWorkVo yunStoreWork)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreWork.setCreateById(loginUser.getUser().getUserId());
        yunStoreWork.setCreateBy(loginUser.getUser().getNickName());
        return toAjax(yunStoreWorkService.insertYunStoreWork(yunStoreWork));
    }

    /**
     * 修改门店作业管理
     */
    @ApiOperation("修改门店作业管理")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunStoreWork", value = "修改门店作业管理",
            dataType = "YunStoreWorkVo")
    @Log(title = "门店作业管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunStoreWorkVo yunStoreWork)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        yunStoreWork.setUpdateBy(loginUser.getUser().getNickName());
        return toAjax(yunStoreWorkService.updateYunStoreWork(yunStoreWork));
    }

    /**
     * 删除门店作业管理
     */
    @ApiOperation("删除门店作业管理")
    @AutoIdempotent
    @ApiImplicitParam(name = "ids", value = "删除门店作业管理",
            dataType = "Long[]")
    @Log(title = "门店作业管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreWorkService.deleteYunStoreWorkByIds(ids));
    }
}
