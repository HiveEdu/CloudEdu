package com.myedu.project.system.service;

import com.myedu.project.system.domain.YunSystemGroupData;
import org.springframework.data.domain.Pageable;
import java.util.Map;
/**
 * 组合数据详情Service接口
 * 
 * @author myedu
 * @date 2020-06-20
 */
public interface IYunSystemGroupDataService 
{
    /**
     * 查询组合数据详情
     * 
     * @param id 组合数据详情ID
     * @return 组合数据详情
     */
    public YunSystemGroupData selectYunSystemGroupDataById(Long id);

    /**
     * 查询组合数据详情列表
     * 
     * @param yunSystemGroupData 组合数据详情
     * @return 组合数据详情集合
     */
    public  Map<String,Object> selectYunSystemGroupDataList(YunSystemGroupData yunSystemGroupData, Pageable pageable);

    /**
     * 新增组合数据详情
     * 
     * @param yunSystemGroupData 组合数据详情
     * @return 结果
     */
    public int insertYunSystemGroupData(YunSystemGroupData yunSystemGroupData);

    /**
     * 修改组合数据详情
     * 
     * @param yunSystemGroupData 组合数据详情
     * @return 结果
     */
    public int updateYunSystemGroupData(YunSystemGroupData yunSystemGroupData);

    /**
     * 批量删除组合数据详情
     * 
     * @param ids 需要删除的组合数据详情ID
     * @return 结果
     */
    public int deleteYunSystemGroupDataByIds(Long[] ids);

    /**
     * 删除组合数据详情信息
     * 
     * @param id 组合数据详情ID
     * @return 结果
     */
    public int deleteYunSystemGroupDataById(Long id);
}
