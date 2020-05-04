package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreEquMapper;
import com.myedu.project.store.domain.YunStoreEqu;
import com.myedu.project.store.service.IYunStoreEquService;

/**
 * 门店设备Service业务层处理
 * 
 * @author lsp
 * @date 2020-05-04
 */
@Service
public class YunStoreEquServiceImpl implements IYunStoreEquService 
{
    @Autowired
    private YunStoreEquMapper yunStoreEquMapper;

    /**
     * 查询门店设备
     * 
     * @param id 门店设备ID
     * @return 门店设备
     */
    @Override
    public YunStoreEqu selectYunStoreEquById(Long id)
    {
        return yunStoreEquMapper.selectYunStoreEquById(id);
    }

    /**
     * 查询门店设备列表
     * 
     * @param yunStoreEqu 门店设备
     * @return 门店设备
     */
    @Override
    public List<YunStoreEqu> selectYunStoreEquList(YunStoreEqu yunStoreEqu)
    {
        return yunStoreEquMapper.selectYunStoreEquList(yunStoreEqu);
    }

    /**
     * 新增门店设备
     * 
     * @param yunStoreEqu 门店设备
     * @return 结果
     */
    @Override
    public int insertYunStoreEqu(YunStoreEqu yunStoreEqu)
    {
        yunStoreEqu.setCreateTime(DateUtils.getNowDate());
        return yunStoreEquMapper.insertYunStoreEqu(yunStoreEqu);
    }

    /**
     * 修改门店设备
     * 
     * @param yunStoreEqu 门店设备
     * @return 结果
     */
    @Override
    public int updateYunStoreEqu(YunStoreEqu yunStoreEqu)
    {
        yunStoreEqu.setUpdateTime(DateUtils.getNowDate());
        return yunStoreEquMapper.updateYunStoreEqu(yunStoreEqu);
    }

    /**
     * 批量删除门店设备
     * 
     * @param ids 需要删除的门店设备ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreEquByIds(Long[] ids)
    {
        return yunStoreEquMapper.deleteYunStoreEquByIds(ids);
    }

    /**
     * 删除门店设备信息
     * 
     * @param id 门店设备ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreEquById(Long id)
    {
        return yunStoreEquMapper.deleteYunStoreEquById(id);
    }
}
