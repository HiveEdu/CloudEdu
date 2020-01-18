package com.myedu.project.parents.mapper;

import com.myedu.project.parents.domain.YunComplaint;
import java.util.List;

/**
 * 投诉Mapper接口
 * 
 * @author myedu
 * @date 2020-01-18
 */
public interface YunComplaintMapper 
{
    /**
     * 查询投诉
     * 
     * @param id 投诉ID
     * @return 投诉
     */
    public YunComplaint selectYunComplaintById(Long id);

    /**
     * 查询投诉列表
     * 
     * @param yunComplaint 投诉
     * @return 投诉集合
     */
    public List<YunComplaint> selectYunComplaintList(YunComplaint yunComplaint);

    /**
     * 新增投诉
     * 
     * @param yunComplaint 投诉
     * @return 结果
     */
    public int insertYunComplaint(YunComplaint yunComplaint);

    /**
     * 修改投诉
     * 
     * @param yunComplaint 投诉
     * @return 结果
     */
    public int updateYunComplaint(YunComplaint yunComplaint);

    /**
     * 删除投诉
     * 
     * @param id 投诉ID
     * @return 结果
     */
    public int deleteYunComplaintById(Long id);

    /**
     * 批量删除投诉
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunComplaintByIds(Long[] ids);
}
