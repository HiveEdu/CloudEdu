package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunStore;
import java.util.List;

/**
 * 门店Mapper接口
 * 
 * @author 梁龙飞
 * @date 2019-12-29
 */
public interface YunStoreMapper 
{
    /**
     * 查询门店
     * 
     * @param id 门店ID
     * @return 门店
     */
    public YunStore selectYunStoreById(Long id);

    /**
     * 查询门店列表
     * 
     * @param yunStore 门店
     * @return 门店集合
     */
    public List<YunStore> selectYunStoreList(YunStore yunStore);

    /**
     * 新增门店
     * 
     * @param yunStore 门店
     * @return 结果
     */
    public int insertYunStore(YunStore yunStore);

    /**
     * 修改门店
     * 
     * @param yunStore 门店
     * @return 结果
     */
    public int updateYunStore(YunStore yunStore);

    /**
     * 删除门店
     * 
     * @param id 门店ID
     * @return 结果
     */
    public int deleteYunStoreById(Long id);

    /**
     * 批量删除门店
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStoreByIds(Long[] ids);
}