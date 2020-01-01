package com.myedu.project.dynamic.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.ServletUtils;
import com.myedu.common.utils.file.FileUploadUtils;
import com.myedu.framework.config.MyEduConfig;
import com.myedu.framework.security.LoginUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.project.dynamic.domain.YunDynamic;
import com.myedu.project.dynamic.service.IYunDynamicService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 云托管动态管理Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@RestController
@RequestMapping("/dynamic/dynamic")
public class YunDynamicController extends BaseController
{
    @Autowired
    private IYunDynamicService yunDynamicService;

    /**
     * 查询云托管动态管理列表
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunDynamic yunDynamic)
    {
        startPage();
        List<YunDynamic> list = yunDynamicService.selectYunDynamicList(yunDynamic);
        return getDataTable(list);
    }

    /**
     * 导出云托管动态管理列表
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:export')")
    @Log(title = "云托管动态管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunDynamic yunDynamic)
    {
        List<YunDynamic> list = yunDynamicService.selectYunDynamicList(yunDynamic);
        ExcelUtil<YunDynamic> util = new ExcelUtil<YunDynamic>(YunDynamic.class);
        return util.exportExcel(list, "dynamic");
    }

    /**
     * 获取云托管动态管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunDynamicService.selectYunDynamicById(id));
    }

    /**
     * 新增云托管动态管理
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:add')")
    @Log(title = "云托管动态管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunDynamic yunDynamic)
    {
        yunDynamic.setCreateById(SecurityUtils.getUserId());
        yunDynamic.setCreateBy(SecurityUtils.getUsername());
        return toAjax(yunDynamicService.insertYunDynamic(yunDynamic));
    }

    /**
     * 修改云托管动态管理
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:edit')")
    @Log(title = "云托管动态管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunDynamic yunDynamic)
    {
        yunDynamic.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunDynamicService.updateYunDynamic(yunDynamic));
    }

    /**
     * 删除云托管动态管理
     */
    @PreAuthorize("@ss.hasPermi('dynamic:dynamic:remove')")
    @Log(title = "云托管动态管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunDynamicService.deleteYunDynamicByIds(ids));
    }

    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("imagefile") MultipartFile file) throws IOException
    {
//        List<MultipartFile> files =((MultipartHttpServletRequest)request).getFiles("imagefiles");
//        for (int i =0; i< files.length; ++i) {
//            if (!files[i].isEmpty())
//            {
//                String avatar = FileUploadUtils.upload(MyEduConfig.getAvatarPath(), files[i]);
//            }
//
//        }
        String avatar = FileUploadUtils.upload(MyEduConfig.getAvatarPath(), file);
        return AjaxResult.error("上传图片异常，请联系管理员");
    }
}
