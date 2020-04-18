package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreHits;
import java.util.List;

/**
 * 门店日访问量统计Service接口
 * 
 * @author LSP
 * @date 2020-04-18
 */
public interface IYunStoreHitsService 
{
    /**
     * 查询门店日访问量统计
     * 
     * @param id 门店日访问量统计ID
     * @return 门店日访问量统计
     */
    public YunStoreHits selectYunStoreHitsById(Long id);

    /**
     * 查询门店日访问量统计列表
     * 
     * @param yunStoreHits 门店日访问量统计
     * @return 门店日访问量统计集合
     */
    public List<YunStoreHits> selectYunStoreHitsList(YunStoreHits yunStoreHits);

    /**
     * 新增门店日访问量统计
     * 
     * @param yunStoreHits 门店日访问量统计
     * @return 结果
     */
    public int insertYunStoreHits(YunStoreHits yunStoreHits);

    /**
     * 修改门店日访问量统计
     * 
     * @param yunStoreHits 门店日访问量统计
     * @return 结果
     */
    public int updateYunStoreHits(YunStoreHits yunStoreHits);

    /**
     * 批量删除门店日访问量统计
     * 
     * @param ids 需要删除的门店日访问量统计ID
     * @return 结果
     */
    public int deleteYunStoreHitsByIds(Long[] ids);

    /**
     * 删除门店日访问量统计信息
     * 
     * @param id 门店日访问量统计ID
     * @return 结果
     */
    public int deleteYunStoreHitsById(Long id);

    public void  savehitsRedis(Long storeId,Long userId);

    public void transHitsCountFromRedis2DB();
}
