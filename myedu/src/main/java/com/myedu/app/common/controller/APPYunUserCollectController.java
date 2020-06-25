package com.myedu.app.common.controller;

import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.system.domain.YunUserCollect;
import com.myedu.project.system.service.IYunUserCollectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户收藏Controller
 * 
 * @author myedu
 * @date 2020-06-25
 */
@RestController
@RequestMapping("/app/system/collect")
public class APPYunUserCollectController extends BaseController
{
    @Autowired
    private IYunUserCollectService yunUserCollectService;
    @Autowired
    private TokenService tokenService;

    /**
     * 查询用户收藏列表
     */
    @PreAuthorize("@ss.hasPermi('system:collect:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunUserCollect yunUserCollect)
    {
        startPage();
        List<YunUserCollect> list = yunUserCollectService.selectYunUserCollectList(yunUserCollect);
        return getDataTable(list);
    }

    /**
     * 导出用户收藏列表
     */
    @PreAuthorize("@ss.hasPermi('system:collect:export')")
    @Log(title = "用户收藏", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunUserCollect yunUserCollect)
    {
        List<YunUserCollect> list = yunUserCollectService.selectYunUserCollectList(yunUserCollect);
        ExcelUtil<YunUserCollect> util = new ExcelUtil<YunUserCollect>(YunUserCollect.class);
        return util.exportExcel(list, "collect");
    }

    /**
     * 获取用户收藏详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:collect:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunUserCollectService.selectYunUserCollectById(id));
    }

    /**
     * 新增用户收藏
     */
    @ApiOperation("新增用户收藏")
    @AutoIdempotent
    @ApiImplicitParam(name = "yunUserCollect", value = "新增用户收藏",
            dataType = "YunUserCollect")
    @Log(title = "用户收藏", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunUserCollect yunUserCollect)
    {
        int count = yunUserCollectService.insertYunUserCollect(yunUserCollect);
        if (count > 0) {
            return AjaxResult.success(count);
        } else {
            return AjaxResult.error();
        }
    }

    /**
     * 修改用户收藏
     */
    @PreAuthorize("@ss.hasPermi('system:collect:edit')")
    @Log(title = "用户收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunUserCollect yunUserCollect)
    {
        return toAjax(yunUserCollectService.updateYunUserCollect(yunUserCollect));
    }

    /**
     * 删除用户收藏
     */
    @PreAuthorize("@ss.hasPermi('system:collect:remove')")
    @Log(title = "用户收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunUserCollectService.deleteYunUserCollectByIds(ids));
    }
}
