package com.myedu.project.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.myedu.common.dozer.service.IGenerator;
import com.myedu.project.system.domain.YunSystemGroupData;
import com.myedu.project.system.domain.vo.YxSystemGroupDataDto;
import com.myedu.project.system.mapper.YunSystemGroupDataMapper;
import com.myedu.project.system.service.IYunSystemGroupDataService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import com.github.pagehelper.PageInfo;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * 组合数据详情Service业务层处理
 * 
 * @author myedu
 * @date 2020-06-20
 */
@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class YunSystemGroupDataServiceImpl implements IYunSystemGroupDataService 
{
    @Autowired
    private YunSystemGroupDataMapper yunSystemGroupDataMapper;

    /**
     * 查询组合数据详情
     * 
     * @param id 组合数据详情ID
     * @return 组合数据详情
     */
    @Override
    public YunSystemGroupData selectYunSystemGroupDataById(Long id)
    {
        return yunSystemGroupDataMapper.selectYunSystemGroupDataById(id);
    }

    /**
     * 查询组合数据详情列表
     * 
     * @param yunSystemGroupData 组合数据详情
     * @return 组合数据详情
     */

     private final IGenerator generator;


    @Override
    public Map<String, Object> selectYunSystemGroupDataList(YunSystemGroupData yunSystemGroupData,Pageable pageable)
    {
       // getPage(pageable);
        PageInfo<YunSystemGroupData> page = new PageInfo<>(yunSystemGroupDataMapper.selectYunSystemGroupDataList(yunSystemGroupData));
        Map<String, Object> map = new LinkedHashMap<>(2);
        List<YxSystemGroupDataDto> systemGroupDataDTOS = new ArrayList<>();
        for (YunSystemGroupData systemGroupData : page.getList()) {

            YxSystemGroupDataDto systemGroupDataDTO = generator.convert(systemGroupData,YxSystemGroupDataDto.class);
            systemGroupDataDTO.setMap(JSON.parseObject(systemGroupData.getValue()));
            systemGroupDataDTOS.add(systemGroupDataDTO);
        }
        map.put("content",systemGroupDataDTOS);
        map.put("totalElements",page.getTotal());
        return map;
    }




    /**
     * 新增组合数据详情
     * 
     * @param yunSystemGroupData 组合数据详情
     * @return 结果
     */
    @Override
    public int insertYunSystemGroupData(YunSystemGroupData yunSystemGroupData)
    {
        return yunSystemGroupDataMapper.insertYunSystemGroupData(yunSystemGroupData);
    }

    /**
     * 修改组合数据详情
     * 
     * @param yunSystemGroupData 组合数据详情
     * @return 结果
     */
    @Override
    public int updateYunSystemGroupData(YunSystemGroupData yunSystemGroupData)
    {
        return yunSystemGroupDataMapper.updateYunSystemGroupData(yunSystemGroupData);
    }

    /**
     * 批量删除组合数据详情
     * 
     * @param ids 需要删除的组合数据详情ID
     * @return 结果
     */
    @Override
    public int deleteYunSystemGroupDataByIds(Long[] ids)
    {
        return yunSystemGroupDataMapper.deleteYunSystemGroupDataByIds(ids);
    }

    /**
     * 删除组合数据详情信息
     * 
     * @param id 组合数据详情ID
     * @return 结果
     */
    @Override
    public int deleteYunSystemGroupDataById(Long id)
    {
        return yunSystemGroupDataMapper.deleteYunSystemGroupDataById(id);
    }
}
