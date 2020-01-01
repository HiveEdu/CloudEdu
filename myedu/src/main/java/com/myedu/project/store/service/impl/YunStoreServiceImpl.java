package com.myedu.project.store.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.StringUtils;
import com.myedu.project.dataBasic.domain.SysStoreType;
import com.myedu.project.dataBasic.mapper.SysStoreTypeMapper;
import com.myedu.project.store.domain.YunStoreType;
import com.myedu.project.store.mapper.StoreTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreMapper;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.service.IYunStoreService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 门店Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2020-01-01
 */
@Service
public class YunStoreServiceImpl implements IYunStoreService 
{
    @Autowired
    private YunStoreMapper yunStoreMapper;

    @Autowired
    private StoreTypeMapper storeTypeMapper;

    @Autowired
    private SysStoreTypeMapper sysStoreTypeMapper;

    /**
     * 查询门店所属类型组
     *
     * @param Name 门店名
     * @return 结果
     */
    @Override
    public String selectStoreTypeGroup(String Name)
    {
        List<SysStoreType> list = sysStoreTypeMapper.selectTypesByStoreName(Name);
        StringBuffer idsStr = new StringBuffer();
        for (SysStoreType type : list)
        {
            idsStr.append(type.getName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString()))
        {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }


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
    @Transactional
    @Override
    public int insertYunStore(YunStore yunStore)
    {
        yunStore.setCreateTime(DateUtils.getNowDate());

        // 新增 门店类型
        int rows = yunStoreMapper.insertYunStore(yunStore);
        // 新增用户与角色管理
        insertStoreType(yunStore);
        return rows;
    }
    /**
     * 新增门店类型信息
     *
     * @param store 门店对象
     */
    public void insertStoreType(YunStore store)
    {
        Long[] types = store.getTypeIds();
        if (StringUtils.isNotNull(types))
        {
            // 新增门店与类型管理
            List<YunStoreType> list = new ArrayList<YunStoreType>();
            for (Long typeId : types)
            {
                YunStoreType st = new YunStoreType();
                st.setStoreId(store.getId());
                st.setTypeId(typeId);
                list.add(st);
            }
            if (list.size() > 0)
            {
                storeTypeMapper.batchStoreType(list);
            }
        }
    }
    /**
     * 修改门店
     * 
     * @param yunStore 门店
     * @return 结果
     */
    @Override
    @Transactional
    public int updateYunStore(YunStore yunStore)
    {
        Long storeId = yunStore.getId();

        yunStore.setUpdateTime(DateUtils.getNowDate());

        storeTypeMapper.deleteStoreTypeByStoreId(storeId);

        // 新增门店与类型管理
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
