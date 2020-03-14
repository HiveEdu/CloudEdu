package com.myedu.project.store.activity.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.activity.mapper.YunStoreBargainMapper;
import com.myedu.project.store.activity.domain.YunStoreBargain;
import com.myedu.project.store.activity.service.IYunStoreBargainService;

/**
 * 门店砍价活动Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2020-03-14
 */
@Service
public class YunStoreBargainServiceImpl implements IYunStoreBargainService 
{
    @Autowired
    private YunStoreBargainMapper yunStoreBargainMapper;

    /**
     * 查询门店砍价活动
     * 
     * @param id 门店砍价活动ID
     * @return 门店砍价活动
     */
    @Override
    public YunStoreBargain selectYunStoreBargainById(Long id)
    {
        return yunStoreBargainMapper.selectYunStoreBargainById(id);
    }

    /**
     * 查询门店砍价活动列表
     * 
     * @param yunStoreBargain 门店砍价活动
     * @return 门店砍价活动
     */
    @Override
    public List<YunStoreBargain> selectYunStoreBargainList(YunStoreBargain yunStoreBargain)
    {
        return yunStoreBargainMapper.selectYunStoreBargainList(yunStoreBargain);
    }

    /**
     * 新增门店砍价活动
     * 
     * @param yunStoreBargain 门店砍价活动
     * @return 结果
     */
    @Override
    public int insertYunStoreBargain(YunStoreBargain yunStoreBargain)
    {
        yunStoreBargain.setCreateTime(DateUtils.getNowDate());
        return yunStoreBargainMapper.insertYunStoreBargain(yunStoreBargain);
    }

    /**
     * 修改门店砍价活动
     * 
     * @param yunStoreBargain 门店砍价活动
     * @return 结果
     */
    @Override
    public int updateYunStoreBargain(YunStoreBargain yunStoreBargain)
    {
        yunStoreBargain.setUpdateTime(DateUtils.getNowDate());
        return yunStoreBargainMapper.updateYunStoreBargain(yunStoreBargain);
    }

    /**
     * 批量删除门店砍价活动
     * 
     * @param ids 需要删除的门店砍价活动ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreBargainByIds(Long[] ids)
    {
        return yunStoreBargainMapper.deleteYunStoreBargainByIds(ids);
    }

    /**
     * 删除门店砍价活动信息
     * 
     * @param id 门店砍价活动ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreBargainById(Long id)
    {
        return yunStoreBargainMapper.deleteYunStoreBargainById(id);
    }
}
