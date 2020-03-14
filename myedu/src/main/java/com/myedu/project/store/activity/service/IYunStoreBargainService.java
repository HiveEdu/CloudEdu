package com.myedu.project.store.activity.service;

import com.myedu.project.store.activity.domain.YunStoreBargain;
import java.util.List;

/**
 * 门店砍价活动Service接口
 * 
 * @author 梁龙飞
 * @date 2020-03-14
 */
public interface IYunStoreBargainService 
{
    /**
     * 查询门店砍价活动
     * 
     * @param id 门店砍价活动ID
     * @return 门店砍价活动
     */
    public YunStoreBargain selectYunStoreBargainById(Long id);

    /**
     * 查询门店砍价活动列表
     * 
     * @param yunStoreBargain 门店砍价活动
     * @return 门店砍价活动集合
     */
    public List<YunStoreBargain> selectYunStoreBargainList(YunStoreBargain yunStoreBargain);

    /**
     * 新增门店砍价活动
     * 
     * @param yunStoreBargain 门店砍价活动
     * @return 结果
     */
    public int insertYunStoreBargain(YunStoreBargain yunStoreBargain);

    /**
     * 修改门店砍价活动
     * 
     * @param yunStoreBargain 门店砍价活动
     * @return 结果
     */
    public int updateYunStoreBargain(YunStoreBargain yunStoreBargain);

    /**
     * 批量删除门店砍价活动
     * 
     * @param ids 需要删除的门店砍价活动ID
     * @return 结果
     */
    public int deleteYunStoreBargainByIds(Long[] ids);

    /**
     * 删除门店砍价活动信息
     * 
     * @param id 门店砍价活动ID
     * @return 结果
     */
    public int deleteYunStoreBargainById(Long id);
}
