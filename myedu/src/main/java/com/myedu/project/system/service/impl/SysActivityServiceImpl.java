package com.myedu.project.system.service.impl;

import com.myedu.common.utils.DateUtils;
import com.myedu.project.system.domain.SysActivity;
import com.myedu.project.system.mapper.SysActivityMapper;
import com.myedu.project.system.service.ISysActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 活动管理Service业务层处理
 * 
 * @author myedu
 * @date 2020-07-22
 */
@Service
public class SysActivityServiceImpl implements ISysActivityService 
{
    @Autowired
    private SysActivityMapper sysActivityMapper;

    /**
     * 查询活动管理
     * 
     * @param id 活动管理ID
     * @return 活动管理
     */
    @Override
    public SysActivity selectSysActivityById(Long id)
    {
        return sysActivityMapper.selectSysActivityById(id);
    }

    /**
     * 查询活动管理列表
     * 
     * @param sysActivity 活动管理
     * @return 活动管理
     */
    @Override
    public List<SysActivity> selectSysActivityList(SysActivity sysActivity)
    {
        return sysActivityMapper.selectSysActivityList(sysActivity);
    }

    /**
     * 新增活动管理
     * 
     * @param sysActivity 活动管理
     * @return 结果
     */
    @Override
    public int insertSysActivity(SysActivity sysActivity)
    {
        sysActivity.setCreateTime(DateUtils.getNowDate());
        return sysActivityMapper.insertSysActivity(sysActivity);
    }

    /**
     * 修改活动管理
     * 
     * @param sysActivity 活动管理
     * @return 结果
     */
    @Override
    public int updateSysActivity(SysActivity sysActivity)
    {
        return sysActivityMapper.updateSysActivity(sysActivity);
    }

    /**
     * 批量删除活动管理
     * 
     * @param ids 需要删除的活动管理ID
     * @return 结果
     */
    @Override
    public int deleteSysActivityByIds(Long[] ids)
    {
        return sysActivityMapper.deleteSysActivityByIds(ids);
    }

    /**
     * 删除活动管理信息
     * 
     * @param id 活动管理ID
     * @return 结果
     */
    @Override
    public int deleteSysActivityById(Long id)
    {
        return sysActivityMapper.deleteSysActivityById(id);
    }
}
