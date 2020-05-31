package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunStoreHits;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * 门店日访问量统计Mapper接口
 * 
 * @author LSP
 * @date 2020-04-18
 */
public interface YunStoreHitsMapper 
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
     * 删除门店日访问量统计
     * 
     * @param id 门店日访问量统计ID
     * @return 结果
     */
    public int deleteYunStoreHitsById(Long id);

    /**
     * 批量删除门店日访问量统计
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStoreHitsByIds(Long[] ids);


    /**
     * 获取门店总点击量
     *
     * @param storeId
     * @return 结果
     */
    public int selectYunStoreHitsCountAll(@Param(value="storeId") Long storeId);
}
