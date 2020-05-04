package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreEqu;
import java.util.List;

/**
 * 门店设备Service接口
 * 
 * @author lsp
 * @date 2020-05-04
 */
public interface IYunStoreEquService 
{
    /**
     * 查询门店设备
     * 
     * @param id 门店设备ID
     * @return 门店设备
     */
    public YunStoreEqu selectYunStoreEquById(Long id);

    /**
     * 查询门店设备列表
     * 
     * @param yunStoreEqu 门店设备
     * @return 门店设备集合
     */
    public List<YunStoreEqu> selectYunStoreEquList(YunStoreEqu yunStoreEqu);

    /**
     * 新增门店设备
     * 
     * @param yunStoreEqu 门店设备
     * @return 结果
     */
    public int insertYunStoreEqu(YunStoreEqu yunStoreEqu);

    /**
     * 修改门店设备
     * 
     * @param yunStoreEqu 门店设备
     * @return 结果
     */
    public int updateYunStoreEqu(YunStoreEqu yunStoreEqu);

    /**
     * 批量删除门店设备
     * 
     * @param ids 需要删除的门店设备ID
     * @return 结果
     */
    public int deleteYunStoreEquByIds(Long[] ids);

    /**
     * 删除门店设备信息
     * 
     * @param id 门店设备ID
     * @return 结果
     */
    public int deleteYunStoreEquById(Long id);
}
