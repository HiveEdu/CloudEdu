package com.myedu.project.system.mapper;

import com.myedu.project.system.domain.YunSystemGroupData;
import java.util.List;

/**
 * 组合数据详情Mapper接口
 * 
 * @author myedu
 * @date 2020-06-20
 */
public interface YunSystemGroupDataMapper 
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
    public List<YunSystemGroupData> selectYunSystemGroupDataList(YunSystemGroupData yunSystemGroupData);

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
     * 删除组合数据详情
     * 
     * @param id 组合数据详情ID
     * @return 结果
     */
    public int deleteYunSystemGroupDataById(Long id);

    /**
     * 批量删除组合数据详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunSystemGroupDataByIds(Long[] ids);
}
