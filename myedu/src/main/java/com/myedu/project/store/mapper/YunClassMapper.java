package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunClass;
import java.util.List;

/**
 * 课程设置Mapper接口
 * 
 * @author myedu
 * @date 2020-01-11
 */
public interface YunClassMapper 
{
    /**
     * 查询课程设置
     * 
     * @param id 课程设置ID
     * @return 课程设置
     */
    public YunClass selectYunClassById(Long id);

    /**
     * 查询课程设置列表
     * 
     * @param yunClass 课程设置
     * @return 课程设置集合
     */
    public List<YunClass> selectYunClassList(YunClass yunClass);

    /**
     * 新增课程设置
     * 
     * @param yunClass 课程设置
     * @return 结果
     */
    public int insertYunClass(YunClass yunClass);

    /**
     * 修改课程设置
     * 
     * @param yunClass 课程设置
     * @return 结果
     */
    public int updateYunClass(YunClass yunClass);

    /**
     * 删除课程设置
     * 
     * @param id 课程设置ID
     * @return 结果
     */
    public int deleteYunClassById(Long id);

    /**
     * 批量删除课程设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunClassByIds(Long[] ids);
}
