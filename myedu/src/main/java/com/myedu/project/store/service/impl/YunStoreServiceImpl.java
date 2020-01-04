package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreMapper;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.service.IYunStoreService;

/**
 * 门店Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@Service
public class YunStoreServiceImpl implements IYunStoreService 
{
    @Autowired
    private YunStoreMapper yunStoreMapper;

    /**
     * 查询门店
     * 
     * @param id 门店ID
     * @return 门店
     */
    @Override
    public YunStore selectYunStoreById(Long id)
    {
        return yunStoreMapper.selectYunStoreById(id);
    }

    /**
     * 查询门店列表
     * 
     * @param yunStore 门店
     * @return 门店
     */
    @Override
    public List<YunStore> selectYunStoreList(YunStore yunStore)
    {
        return yunStoreMapper.selectYunStoreList(yunStore);
    }

    /**
     * 新增门店
     * 
     * @param yunStore 门店
     * @return 结果
     */
    @Override
    public int insertYunStore(YunStore yunStore)
    {
        yunStore.setCreateTime(DateUtils.getNowDate());
        return yunStoreMapper.insertYunStore(yunStore);
    }

    /**
     * 修改门店
     * 
     * @param yunStore 门店
     * @return 结果
     */
    @Override
    public int updateYunStore(YunStore yunStore)
    {
        yunStore.setUpdateTime(DateUtils.getNowDate());
        return yunStoreMapper.updateYunStore(yunStore);
    }

    /**
     * 批量删除门店
     * 
     * @param ids 需要删除的门店ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreByIds(Long[] ids)
    {
        return yunStoreMapper.deleteYunStoreByIds(ids);
    }

    /**
     * 删除门店信息
     * 
     * @param id 门店ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreById(Long id)
    {
        return yunStoreMapper.deleteYunStoreById(id);
    }
}
