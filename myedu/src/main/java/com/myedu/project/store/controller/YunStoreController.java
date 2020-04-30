package com.myedu.project.store.controller;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.common.utils.poi.ExcelUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.config.Experience;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.dataBasic.domain.SysLabel;
import com.myedu.project.dataBasic.domain.SysStoreType;
import com.myedu.project.dataBasic.service.ISysLabelService;
import com.myedu.project.dataBasic.service.ISysStoreTypeService;
import com.myedu.project.order.domain.YunOrder;
import com.myedu.project.order.domain.vo.YunOrderVo;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.enums.StoreStatus;
import com.myedu.project.store.enums.StoryType;
import com.myedu.project.store.enums.labelType;
import com.myedu.project.store.service.IYunStoreHitsService;
import com.myedu.project.store.service.IYunStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 门店Controller
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */

@Validated
@RestController
@RequestMapping("/store/store")
public class YunStoreController extends BaseController
{
    @Autowired
    private IYunStoreService yunStoreService;

    @Autowired
    private ISysStoreTypeService sysStoreTypeService;

    @Autowired
    private ISysLabelService syslabelService;

    @Autowired
    private IYunStoreHitsService yunStoreHitsService;

    /**
     * 查询门店列表
     */
    @PreAuthorize("@ss.hasPermi('store:store:list')")
    @GetMapping("/list")
    public TableDataInfo list(YunStoreVo yunStore)
    {
        startPage();
        List<YunStoreVo> list = yunStoreService.selectYunStoreList(yunStore);
        return getDataTable(list);
    }

    /**
     * 导出门店列表
     */
    @PreAuthorize("@ss.hasPermi('store:store:export')")
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
    @PreAuthorize("@ss.hasPermi('store:store:query')")
    @GetMapping(value = { "/", "/{id}" })
    public AjaxResult getInfo(@PathVariable(value = "id", required = false) Long id)
    {
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
            yunStoreHitsService.savehitsRedis(id, SecurityUtils.getUserId());
            ajax.put(AjaxResult.DATA_TAG, yunStoreService.selectYunStoreById(id));
            ajax.put("storeTypeIds", sysStoreTypeService.selectStoreTypeListByStoreId(id));
            ajax.put("storeLabelIds", syslabelService.selectLabelListById(id));
        }

        return ajax;
    }

    /**
     * 新增门店
     */
    @PreAuthorize("@ss.hasPermi('store:store:add')")
    @Log(title = "门店", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody YunStore yunStore)
    {
        yunStore.setCreateById(SecurityUtils.getUserId());
        yunStore.setCreateBy(SecurityUtils.getUsername());
        //向位置表插入数据
        return toAjax(yunStoreService.insertYunStore(yunStore));
    }

    /**
     * 修改门店
     */
    @PreAuthorize("@ss.hasPermi('store:store:edit')")
    @Log(title = "门店", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Valid @RequestBody YunStore yunStore)
    {
        yunStore.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(yunStoreService.updateYunStore(yunStore));
    }
    /**
     * 门店审核
     */
    @PreAuthorize("@ss.hasPermi('store:store:checkStore')")
    @Log(title = "门店", businessType = BusinessType.UPDATE)
    @PutMapping(value = "checkStore")
    public AjaxResult checkStore(@RequestBody YunStore yunStore)
    {
        yunStore.setUpdateBy(SecurityUtils.getUsername());
        if(yunStore.getStatus().equals(StoreStatus.APPROVE.getCode())){
            yunStore.setBeginExperienceTime(new Date());
            Integer day=Experience.getTime();//获取初始体验时间
            Date endExperienceTime= DateUtils.getAfterDayTime(yunStore.getBeginExperienceTime(),day);
            yunStore.setEndExperienceTime(endExperienceTime);
        }
        return toAjax(yunStoreService.updateYunStore(yunStore));
    }

    /**
     * 删除门店
     */
    @PreAuthorize("@ss.hasPermi('store:store:remove')")
    @Log(title = "门店", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunStoreService.deleteYunStoreByIds(ids));
    }

    /*
     * @Description :更改课程状态下线
     * @Author : 梁少鹏
     * @Date : 2020/1/21 16:30
     */
    @PreAuthorize("@ss.hasPermi('store:course:changeStatusOff')")
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

    /*
     * @Description :更改课程状态在售
     * @Author : 梁少鹏
     * @Date : 2020/1/21 16:30
     */
    @PreAuthorize("@ss.hasPermi('store:course:changeStatusOn')")
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


    @PreAuthorize("@ss.hasPermi('store:store:toPayAsPC')")
    @Log(title = "支付宝PC网页支付")
    @PostMapping(value = "/toPayAsPC")
    public AjaxResult toPayAsPc(@RequestBody YunOrder yunOrder) throws Exception{
        AjaxResult ajax = AjaxResult.success();
        YunStore yunStore=new YunStore();
        yunStore.setId(yunOrder.getId());
        String payUrl = yunStoreService.toPayAsPc(yunStore,yunOrder.getTotalMoney());
        ajax.put("url",payUrl);
        return ajax;
    }

    @PreAuthorize("@ss.hasPermi('order:order:toPayAsWeb')")
    @Log(title = "支付宝PC网页支付")
    @PostMapping(value = "/toPayAsWeb")
    public AjaxResult toPayAsWeb(@RequestBody YunOrderVo yunOrder) throws Exception{
        AjaxResult ajax = AjaxResult.success();
        YunStore yunStore=new YunStore();
        yunStore.setId(yunOrder.getId());
        String payUrl = yunStoreService.toPayAsWeb(yunStore,yunOrder.getTotalMoney());
        ajax.put("url",payUrl);
        return ajax;
    }

    /*
     * @Description :同步通知
     * @Author : 梁少鹏
     * @Date : 2020/2/12 11:35
     */
    @GetMapping(value = "/getReturnUrlInfo")
    public String alipayReturnUrlInfo(HttpServletRequest request) {
        String result=yunStoreService.synchronous(request);
        if(result.equals("success")){
            return "<html>\n" +
                    "<head>\n" +
                    "<script type=\"text/javascript\"> function load() { window.close(); } </script>\n" +
                    "</head>\n" +
                    "<body onload=\"" +
                    "load()\"> </body>\n" +
                    "</html>";
        }else{
            result="支付失败";
        }
        return result;
    }
    /*
     * @Description :异步通知
     * @Author : 梁少鹏
     * @Date : 2020/2/12 11:35
     */
    @PostMapping(value = "/getNotifyUrlInfo")
    public void alipayNotifyUrlInfo(HttpServletRequest request, HttpServletResponse response){
        yunStoreService.notify(request,response);
    }
}
