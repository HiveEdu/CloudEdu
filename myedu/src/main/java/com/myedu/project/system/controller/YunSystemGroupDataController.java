package com.myedu.project.system.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.myedu.common.utils.OrderUtil;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.aspectj.lang.enums.BusinessType;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.project.system.domain.YunSystemGroupData;
import com.myedu.project.system.service.IYunSystemGroupDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
/**
 * 组合数据详情Controller
 * 
 * @author myedu
 * @date 2020-06-20
 */
@RestController
@RequestMapping("/system/yunSystemGroupData")
public class YunSystemGroupDataController extends BaseController
{
    @Autowired
    private IYunSystemGroupDataService yunSystemGroupDataService;

    /**
     * 查询组合数据详情列表
     */
    @PreAuthorize("@ss.hasPermi('system:yunSystemGroupData:list')")
    @GetMapping("/list")
    public ResponseEntity list(YunSystemGroupData yunSystemGroupData, Pageable pageable)
    {

        Sort sort = new Sort(Sort.Direction.DESC, "sort");
        Pageable pageableT = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                sort);
        return new ResponseEntity(yunSystemGroupDataService.
                selectYunSystemGroupDataList(yunSystemGroupData,pageableT),HttpStatus.OK);
    }



    /**
     * 获取组合数据详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:yunSystemGroupData:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(yunSystemGroupDataService.selectYunSystemGroupDataById(id));
    }

    /**
     * 新增组合数据详情
     */
    @PreAuthorize("@ss.hasPermi('system:yunSystemGroupData:add')")
    @Log(title = "组合数据详情", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody String jsonStr)
    {
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        YunSystemGroupData yxSystemGroupData = new YunSystemGroupData();
        yxSystemGroupData.setGroupName(jsonObject.get("groupName").toString());
        jsonObject.remove("groupName");
        yxSystemGroupData.setValue(jsonObject.toJSONString());
        yxSystemGroupData.setStatus(jsonObject.getInteger("status"));
        yxSystemGroupData.setSort(jsonObject.getInteger("sort"));
        yxSystemGroupData.setAddTime(OrderUtil.getSecondTimestampTwo());

        return toAjax(yunSystemGroupDataService.insertYunSystemGroupData(yxSystemGroupData));
    }

    /**
     * 修改组合数据详情
     */
    @PreAuthorize("@ss.hasPermi('system:yunSystemGroupData:edit')")
    @Log(title = "组合数据详情", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody String jsonStr)
    {

        JSONObject jsonObject = JSON.parseObject(jsonStr);
        YunSystemGroupData yxSystemGroupData = new YunSystemGroupData();

        yxSystemGroupData.setGroupName(jsonObject.get("groupName").toString());
        jsonObject.remove("groupName");
        yxSystemGroupData.setValue(jsonObject.toJSONString());
        if(jsonObject.getInteger("status") == null){
            yxSystemGroupData.setStatus(1);
        }else{
            yxSystemGroupData.setStatus(jsonObject.getInteger("status"));
        }
        if(jsonObject.getLong("sort") == null){
            //yxSystemGroupData.setSort(0);
            yxSystemGroupData.setSort(0);
        }else{
            yxSystemGroupData.setSort(jsonObject.getInteger("sort"));
        }


        yxSystemGroupData.setId(Long.valueOf(jsonObject.get("id").toString()));
        return toAjax(yunSystemGroupDataService.updateYunSystemGroupData(yxSystemGroupData));
    }

    /**
     * 删除组合数据详情
     */
    @PreAuthorize("@ss.hasPermi('system:yunSystemGroupData:remove')")
    @Log(title = "组合数据详情", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(yunSystemGroupDataService.deleteYunSystemGroupDataByIds(ids));
    }
}
