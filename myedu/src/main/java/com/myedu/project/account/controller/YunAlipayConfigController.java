package com.myedu.project.account.controller;

import java.util.List;

import com.myedu.common.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.project.account.domain.YunAlipayConfig;
import com.myedu.project.account.service.IYunAlipayConfigService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.web.page.TableDataInfo;

/**
 * 支付宝配置类Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-27
 */
@RestController
@RequestMapping("/account/alipay")
public class YunAlipayConfigController extends BaseController
{
    @Autowired
    private IYunAlipayConfigService yunAlipayConfigService;

    /**
     * 查询支付宝配置类列表
     */
    @PreAuthorize("@ss.hasPermi('account:alipay:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunAlipayConfig yunAlipayConfig)
    {
        startPage();
        List<YunAlipayConfig> list = yunAlipayConfigService.selectYunAlipayConfigList(yunAlipayConfig);
        return getDataTable(list);
    }

    /**
     * 导出支付宝配置类列表
     */
    @PreAuthorize("@ss.hasPermi('account:alipay:export')")
    @Log(title = "支付宝配置类", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(YunAlipayConfig yunAlipayConfig)
    {
        List<YunAlipayConfig> list = yunAlipayConfigService.selectYunAlipayConfigList(yunAlipayConfig);
        ExcelUtil<YunAlipayConfig> util = new ExcelUtil<YunAlipayConfig>(YunAlipayConfig.class);
        return util.exportExcel(list, "alipay");
    }

    /**
     * 获取支付宝配置类详细信息
     */
    @PreAuthorize("@ss.hasPermi('account:alipay:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
        AjaxResult ajax = AjaxResult.success();
        if(id!=null){
            ajax.put(AjaxResult.DATA_TAG,yunAlipayConfigService.selectYunAlipayConfigById(id));
            return ajax;
        }else{
            YunAlipayConfig yunAlipayConfig=new YunAlipayConfig();
            yunAlipayConfig.setCreateById(SecurityUtils.getUserId());
            List<YunAlipayConfig> yunAlipayConfigs=yunAlipayConfigService.selectYunAlipayConfigList(yunAlipayConfig);
//            if(yunAlipayConfigs.size()>0){
//                return AjaxResult.error(204,"账户配置已经存在");
//            }else{
                return AjaxResult.success("配置");
//            }
        }
//        return AjaxResult.success(yunAlipayConfigService.selectYunAlipayConfigById(id));
    }

    /**
     * 新增支付宝配置类
     */
    @PreAuthorize("@ss.hasPermi('account:alipay:add')")
    @Log(title = "支付宝配置类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YunAlipayConfig yunAlipayConfig)
    {
        return toAjax(yunAlipayConfigService.insertYunAlipayConfig(yunAlipayConfig));
    }

    /**
     * 修改支付宝配置类
     */
    @PreAuthorize("@ss.hasPermi('account:alipay:edit')")
    @Log(title = "支付宝配置类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YunAlipayConfig yunAlipayConfig)
    {
        return toAjax(yunAlipayConfigService.updateYunAlipayConfig(yunAlipayConfig));
    }

    /**
     * 删除支付宝配置类
     */
    @PreAuthorize("@ss.hasPermi('account:alipay:remove')")
    @Log(title = "支付宝配置类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunAlipayConfigService.deleteYunAlipayConfigByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('account:alipay:toPayAsPC')")
    @Log(title = "支付宝PC网页支付")
    @PostMapping(value = "/toPayAsPC")
    public AjaxResult toPayAsPc(@RequestBody YunAlipayConfig yunAlipayConfig) throws Exception{
        AjaxResult ajax = AjaxResult.success();
        String payUrl = yunAlipayConfigService.toPayAsPc(yunAlipayConfig);
        ajax.put("url",payUrl);
        return ajax;
    }
}
