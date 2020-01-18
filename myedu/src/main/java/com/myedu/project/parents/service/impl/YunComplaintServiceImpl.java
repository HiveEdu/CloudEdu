package com.myedu.project.parents.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.parents.mapper.YunComplaintMapper;
import com.myedu.project.parents.domain.YunComplaint;
import com.myedu.project.parents.service.IYunComplaintService;

/**
 * 投诉Service业务层处理
 * 
 * @author myedu
 * @date 2020-01-18
 */
@Service
public class YunComplaintServiceImpl implements IYunComplaintService 
{
    @Autowired
    private YunComplaintMapper yunComplaintMapper;

    /**
     * 查询投诉
     * 
     * @param id 投诉ID
     * @return 投诉
     */
    @Override
    public YunComplaint selectYunComplaintById(Long id)
    {
        return yunComplaintMapper.selectYunComplaintById(id);
    }

    /**
     * 查询投诉列表
     * 
     * @param yunComplaint 投诉
     * @return 投诉
     */
    @Override
    public List<YunComplaint> selectYunComplaintList(YunComplaint yunComplaint)
    {
        return yunComplaintMapper.selectYunComplaintList(yunComplaint);
    }

    /**
     * 新增投诉
     * 
     * @param yunComplaint 投诉
     * @return 结果
     */
    @Override
    public int insertYunComplaint(YunComplaint yunComplaint)
    {
        yunComplaint.setCreateTime(DateUtils.getNowDate());
        return yunComplaintMapper.insertYunComplaint(yunComplaint);
    }

    /**
     * 修改投诉
     * 
     * @param yunComplaint 投诉
     * @return 结果
     */
    @Override
    public int updateYunComplaint(YunComplaint yunComplaint)
    {
        yunComplaint.setUpdateTime(DateUtils.getNowDate());
        return yunComplaintMapper.updateYunComplaint(yunComplaint);
    }

    /**
     * 批量删除投诉
     * 
     * @param ids 需要删除的投诉ID
     * @return 结果
     */
    @Override
    public int deleteYunComplaintByIds(Long[] ids)
    {
        return yunComplaintMapper.deleteYunComplaintByIds(ids);
    }

    /**
     * 删除投诉信息
     * 
     * @param id 投诉ID
     * @return 结果
     */
    @Override
    public int deleteYunComplaintById(Long id)
    {
        return yunComplaintMapper.deleteYunComplaintById(id);
    }
}
