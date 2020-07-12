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
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.service.IYunStoreService;
import com.myedu.project.system.domain.SysNotice;
import com.myedu.project.system.service.ISysNoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公告 信息操作处理
 * 
 * @author ruoyi
 */
@Api("门店通知公告管理")
@RestController
@RequestMapping("/app/system/notice")
public class AppYunStoreNoticeController extends BaseController
{
    @Autowired
    private ISysNoticeService noticeService;
    @Autowired
    private IYunStoreService yunStoreService;
    @Autowired
    private TokenService tokenService;

    /**
     * 获取通知公告列表
     */
    @ApiOperation("获取通知公告列表")
    @AutoIdempotent
    @ApiImplicitParam(name = "notice", value = "获取通知公告列表",
            dataType = "SysNotice")
    @GetMapping("/list")
    public TableDataInfo list(SysNotice notice)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        startPage();
        notice.setCreateById(loginUser.getUser().getUserId());
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @ApiOperation("根据通知公告编号获取详细信息")
    @AutoIdempotent
    @ApiImplicitParam(name = "noticeId", value = "根据通知公告编号获取详细信息",
            dataType = "Long")
    @GetMapping(value = { "/", "/{noticeId}" })
    public AjaxResult getInfo(@PathVariable(value = "noticeId", required = false) Long noticeId)
    {
        AjaxResult ajax = AjaxResult.success();
        YunStoreVo yunStore=new YunStoreVo();
        yunStore.setCreateById(SecurityUtils.getUserId());
        List<YunStoreVo> stores=yunStoreService.selectYunStoreList(yunStore);
        ajax.put("stores", stores);
        if (StringUtils.isNotNull(noticeId))
        {
            ajax.put(AjaxResult.DATA_TAG,noticeService.selectNoticeById(noticeId));
        }
        return ajax;
    }

    /**
     * 新增通知公告
     */
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @ApiOperation("新增通知公告")
    @AutoIdempotent
    @ApiImplicitParam(name = "notice", value = "新增通知公告",
            dataType = "SysNotice")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysNotice notice)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        notice.setCreateById(loginUser.getUser().getUserId());
        notice.setCreateBy(loginUser.getUser().getNickName());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @ApiOperation("修改通知公告")
    @AutoIdempotent
    @ApiImplicitParam(name = "notice", value = "修改通知公告",
            dataType = "SysNotice")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysNotice notice)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        notice.setUpdateById(loginUser.getUser().getUserId());
        notice.setUpdateBy(loginUser.getUser().getNickName());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeId}")
    public AjaxResult remove(@PathVariable Long noticeId)
    {
        return toAjax(noticeService.deleteNoticeById(noticeId));
    }
}
