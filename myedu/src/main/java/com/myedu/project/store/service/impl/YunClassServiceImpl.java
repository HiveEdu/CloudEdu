package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunClassMapper;
import com.myedu.project.store.domain.YunClass;
import com.myedu.project.store.service.IYunClassService;

/**
 * 课程设置Service业务层处理
 * 
 * @author myedu
 * @date 2020-01-11
 */
@Service
public class YunClassServiceImpl implements IYunClassService 
{
    @Autowired
    private YunClassMapper yunClassMapper;

    /**
     * 查询课程设置
     * 
     * @param id 课程设置ID
     * @return 课程设置
     */
    @Override
    public YunClass selectYunClassById(Long id)
    {
        return yunClassMapper.selectYunClassById(id);
    }

    /**
     * 查询课程设置列表
     * 
     * @param yunClass 课程设置
     * @return 课程设置
     */
    @Override
    public List<YunClass> selectYunClassList(YunClass yunClass)
    {
        return yunClassMapper.selectYunClassList(yunClass);
    }

    /**
     * 新增课程设置
     * 
     * @param yunClass 课程设置
     * @return 结果
     */
    @Override
    public int insertYunClass(YunClass yunClass)
    {
        yunClass.setCreateTime(DateUtils.getNowDate());
        return yunClassMapper.insertYunClass(yunClass);
    }

    /**
     * 修改课程设置
     * 
     * @param yunClass 课程设置
     * @return 结果
     */
    @Override
    public int updateYunClass(YunClass yunClass)
    {
        yunClass.setUpdateTime(DateUtils.getNowDate());
        return yunClassMapper.updateYunClass(yunClass);
    }

    /**
     * 批量删除课程设置
     * 
     * @param ids 需要删除的课程设置ID
     * @return 结果
     */
    @Override
    public int deleteYunClassByIds(Long[] ids)
    {
        return yunClassMapper.deleteYunClassByIds(ids);
    }

    /**
     * 删除课程设置信息
     * 
     * @param id 课程设置ID
     * @return 结果
     */
    @Override
    public int deleteYunClassById(Long id)
    {
        return yunClassMapper.deleteYunClassById(id);
    }
}
