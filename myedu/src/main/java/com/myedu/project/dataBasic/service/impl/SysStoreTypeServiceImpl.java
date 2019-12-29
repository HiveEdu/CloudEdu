package com.myedu.project.dataBasic.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.dataBasic.mapper.SysStoreTypeMapper;
import com.myedu.project.dataBasic.domain.SysStoreType;
import com.myedu.project.dataBasic.service.ISysStoreTypeService;

/**
 * 门店类型Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
@Service
public class SysStoreTypeServiceImpl implements ISysStoreTypeService 
{
    @Autowired
    private SysStoreTypeMapper sysStoreTypeMapper;

    /**
     * 查询门店类型
     * 
     * @param id 门店类型ID
     * @return 门店类型
     */
    @Override
    public SysStoreType selectSysStoreTypeById(Long id)
    {
        return sysStoreTypeMapper.selectSysStoreTypeById(id);
    }

    /**
     * 查询门店类型列表
     * 
     * @param sysStoreType 门店类型
     * @return 门店类型
     */
    @Override
    public List<SysStoreType> selectSysStoreTypeList(SysStoreType sysStoreType)
    {
        return sysStoreTypeMapper.selectSysStoreTypeList(sysStoreType);
    }

    /**
     * 新增门店类型
     * 
     * @param sysStoreType 门店类型
     * @return 结果
     */
    @Override
    public int insertSysStoreType(SysStoreType sysStoreType)
    {
        sysStoreType.setCreateTime(DateUtils.getNowDate());
        return sysStoreTypeMapper.insertSysStoreType(sysStoreType);
    }

    /**
     * 修改门店类型
     * 
     * @param sysStoreType 门店类型
     * @return 结果
     */
    @Override
    public int updateSysStoreType(SysStoreType sysStoreType)
    {
        sysStoreType.setUpdateTime(DateUtils.getNowDate());
        return sysStoreTypeMapper.updateSysStoreType(sysStoreType);
    }

    /**
     * 批量删除门店类型
     * 
     * @param ids 需要删除的门店类型ID
     * @return 结果
     */
    @Override
    public int deleteSysStoreTypeByIds(Long[] ids)
    {
        return sysStoreTypeMapper.deleteSysStoreTypeByIds(ids);
    }

    /**
     * 删除门店类型信息
     * 
     * @param id 门店类型ID
     * @return 结果
     */
    @Override
    public int deleteSysStoreTypeById(Long id)
    {
        return sysStoreTypeMapper.deleteSysStoreTypeById(id);
    }
}
