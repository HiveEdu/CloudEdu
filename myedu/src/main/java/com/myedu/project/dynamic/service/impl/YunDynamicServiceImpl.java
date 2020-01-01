package com.myedu.project.dynamic.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.dynamic.mapper.YunDynamicMapper;
import com.myedu.project.dynamic.domain.YunDynamic;
import com.myedu.project.dynamic.service.IYunDynamicService;

/**
 * 云托管动态管理Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@Service
public class YunDynamicServiceImpl implements IYunDynamicService 
{
    @Autowired
    private YunDynamicMapper yunDynamicMapper;

    /**
     * 查询云托管动态管理
     * 
     * @param id 云托管动态管理ID
     * @return 云托管动态管理
     */
    @Override
    public YunDynamic selectYunDynamicById(Long id)
    {
        return yunDynamicMapper.selectYunDynamicById(id);
    }

    /**
     * 查询云托管动态管理列表
     * 
     * @param yunDynamic 云托管动态管理
     * @return 云托管动态管理
     */
    @Override
    public List<YunDynamic> selectYunDynamicList(YunDynamic yunDynamic)
    {
        return yunDynamicMapper.selectYunDynamicList(yunDynamic);
    }

    /**
     * 新增云托管动态管理
     * 
     * @param yunDynamic 云托管动态管理
     * @return 结果
     */
    @Override
    public int insertYunDynamic(YunDynamic yunDynamic)
    {
        yunDynamic.setCreateTime(DateUtils.getNowDate());
        return yunDynamicMapper.insertYunDynamic(yunDynamic);
    }

    /**
     * 修改云托管动态管理
     * 
     * @param yunDynamic 云托管动态管理
     * @return 结果
     */
    @Override
    public int updateYunDynamic(YunDynamic yunDynamic)
    {
        yunDynamic.setUpdateTime(DateUtils.getNowDate());
        return yunDynamicMapper.updateYunDynamic(yunDynamic);
    }

    /**
     * 批量删除云托管动态管理
     * 
     * @param ids 需要删除的云托管动态管理ID
     * @return 结果
     */
    @Override
    public int deleteYunDynamicByIds(Long[] ids)
    {
        return yunDynamicMapper.deleteYunDynamicByIds(ids);
    }

    /**
     * 删除云托管动态管理信息
     * 
     * @param id 云托管动态管理ID
     * @return 结果
     */
    @Override
    public int deleteYunDynamicById(Long id)
    {
        return yunDynamicMapper.deleteYunDynamicById(id);
    }
}
