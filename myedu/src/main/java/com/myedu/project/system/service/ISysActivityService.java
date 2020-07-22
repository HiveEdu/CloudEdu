package com.myedu.project.system.service;

import com.myedu.project.system.domain.SysActivity;
import java.util.List;

/**
 * 活动管理Service接口
 * 
 * @author myedu
 * @date 2020-07-22
 */
public interface ISysActivityService 
{
    /**
     * 查询活动管理
     * 
     * @param id 活动管理ID
     * @return 活动管理
     */
    public SysActivity selectSysActivityById(Long id);

    /**
     * 查询活动管理列表
     * 
     * @param sysActivity 活动管理
     * @return 活动管理集合
     */
    public List<SysActivity> selectSysActivityList(SysActivity sysActivity);

    /**
     * 新增活动管理
     * 
     * @param sysActivity 活动管理
     * @return 结果
     */
    public int insertSysActivity(SysActivity sysActivity);

    /**
     * 修改活动管理
     * 
     * @param sysActivity 活动管理
     * @return 结果
     */
    public int updateSysActivity(SysActivity sysActivity);

    /**
     * 批量删除活动管理
     * 
     * @param ids 需要删除的活动管理ID
     * @return 结果
     */
    public int deleteSysActivityByIds(Long[] ids);

    /**
     * 删除活动管理信息
     * 
     * @param id 活动管理ID
     * @return 结果
     */
    public int deleteSysActivityById(Long id);
}
