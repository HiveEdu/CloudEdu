package com.myedu.project.store.service.impl;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.domain.YunStoreLabel;
import com.myedu.project.store.domain.YunStoreType;
import com.myedu.project.store.mapper.YunStoreLabelMapper;
import com.myedu.project.store.mapper.YunStoreMapper;
import com.myedu.project.store.mapper.YunStoreTypeMapper;
import com.myedu.project.store.service.IYunStoreService;
import com.myedu.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private YunStoreTypeMapper yunStoreTypeMapper;
    @Autowired
    private YunStoreLabelMapper yunStorelabelMapper;
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
        // 新增门店
        int rows =yunStoreMapper.insertYunStore(yunStore);
        // 新增门店门店类型关联表
        insertStoreType(yunStore);
        return rows;
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
        Long storeId = yunStore.getId();
        // 删除门店与门店类型关联
        yunStoreTypeMapper.deleteStoreTypeByStoreId(storeId);
        // 删除门店与标签类型关联
        yunStorelabelMapper.deleteYunStoreLabelById(storeId);
        // 新增门店门店类型关联表
        insertStoreType(yunStore);
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
        //删除与门店类型关联
        //删除与标签类型关联
        for (Long id : ids)
        {
            yunStoreTypeMapper.deleteStoreTypeByStoreId(id);
            yunStorelabelMapper.deleteYunStoreLabelById(id);
        }
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
        //删除与门店类型关联
        yunStoreTypeMapper.deleteStoreTypeByStoreId(id);
        //删除与标签类型关联
        yunStorelabelMapper.deleteYunStoreLabelById(id);
        return yunStoreMapper.deleteYunStoreById(id);
    }

    /*
     * @Description :新增门店类型
     * @Author : 梁少鹏
     * @Date : 2020/1/5 10:13
     */
    public void insertStoreType(YunStore yunStore)
    {
        Long[] storeTypeIds = yunStore.getStoreTypeIds();
        if (StringUtils.isNotNull(storeTypeIds))
        {
            // 新增门店与门店类型
            List<YunStoreType> list = new ArrayList<YunStoreType>();
            for (Long storeTypeId : storeTypeIds)
            {
                YunStoreType yunStoreType = new YunStoreType();
                yunStoreType.setStoreId(yunStore.getId());
                yunStoreType.setTypeId(storeTypeId);
                list.add(yunStoreType);
            }
            if (list.size() > 0)
            {
                yunStoreTypeMapper.batchStoreType(list);
            }
        }
    }

    /*
     * @Description :新增门店标签
     * @Author : 梁龙飞
     * @Date : 2020/1/5 14:50
     */
    public void insertStoreLabel(YunStore yunStore)
    {
        Long[] storeTypeIds = yunStore.getStoreTypeIds();
        if (StringUtils.isNotNull(storeTypeIds))
        {
            // 新增门店与标签
            List<YunStoreLabel> list1 = new ArrayList<YunStoreLabel>();
            for (Long storeTypeId : storeTypeIds)
            {
                YunStoreLabel yunStoreLabel = new YunStoreLabel();
                yunStoreLabel.setStoreId(yunStore.getId());
                yunStoreLabel.setTypeId(storeTypeId);
                list1.add(yunStoreLabel);
            }
            if (list1.size() > 0)
            {
                yunStorelabelMapper.batchStoreType(list1);
            }
        }
    }

}
