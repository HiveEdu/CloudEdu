package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStore;
import java.util.List;

/**
 * 门店Service接口
 * 
 * @author 梁龙飞
 * @date 2020-01-01
 */
public interface IYunStoreService 
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
     * 根据用户ID查询用户所属角色组
     *
     * @param storeName 用户名
     * @return 结果
     */
    public String selectStoreTypeGroup(String storeName);

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
     * 批量删除门店
     * 
     * @param ids 需要删除的门店ID
     * @return 结果
     */
    public int deleteYunStoreByIds(Long[] ids);

    /**
     * 删除门店信息
     * 
     * @param id 门店ID
     * @return 结果
     */
    public int deleteYunStoreById(Long id);
}
