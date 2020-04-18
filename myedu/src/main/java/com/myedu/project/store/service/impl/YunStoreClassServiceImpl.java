package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreClassMapper;
import com.myedu.project.store.domain.YunStoreClass;
import com.myedu.project.store.service.IYunStoreClassService;

/**
 * 分班管理Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2020-04-18
 */
@Service
public class YunStoreClassServiceImpl implements IYunStoreClassService 
{
    @Autowired
    private YunStoreClassMapper yunStoreClassMapper;

    /**
     * 查询分班管理
     * 
     * @param id 分班管理ID
     * @return 分班管理
     */
    @Override
    public YunStoreClass selectYunStoreClassById(Long id)
    {
        return yunStoreClassMapper.selectYunStoreClassById(id);
    }

    /**
     * 查询分班管理列表
     * 
     * @param yunStoreClass 分班管理
     * @return 分班管理
     */
    @Override
    public List<YunStoreClass> selectYunStoreClassList(YunStoreClass yunStoreClass)
    {
        return yunStoreClassMapper.selectYunStoreClassList(yunStoreClass);
    }

    /**
     * 新增分班管理
     * 
     * @param yunStoreClass 分班管理
     * @return 结果
     */
    @Override
    public int insertYunStoreClass(YunStoreClass yunStoreClass)
    {
        yunStoreClass.setCreateTime(DateUtils.getNowDate());
        return yunStoreClassMapper.insertYunStoreClass(yunStoreClass);
    }

    /**
     * 修改分班管理
     * 
     * @param yunStoreClass 分班管理
     * @return 结果
     */
    @Override
    public int updateYunStoreClass(YunStoreClass yunStoreClass)
    {
        yunStoreClass.setUpdateTime(DateUtils.getNowDate());
        return yunStoreClassMapper.updateYunStoreClass(yunStoreClass);
    }

    /**
     * 批量删除分班管理
     * 
     * @param ids 需要删除的分班管理ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreClassByIds(Long[] ids)
    {
        return yunStoreClassMapper.deleteYunStoreClassByIds(ids);
    }

    /**
     * 删除分班管理信息
     * 
     * @param id 分班管理ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreClassById(Long id)
    {
        return yunStoreClassMapper.deleteYunStoreClassById(id);
    }
}
