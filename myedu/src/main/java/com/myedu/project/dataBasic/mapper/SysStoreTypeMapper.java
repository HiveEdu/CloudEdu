package com.myedu.project.dataBasic.mapper;

import com.myedu.project.dataBasic.domain.SysStoreType;
import java.util.List;

/**
 * 门店类型Mapper接口
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
public interface SysStoreTypeMapper 
{
    /**
     * 查询门店类型
     * 
     * @param id 门店类型ID
     * @return 门店类型
     */
    public SysStoreType selectSysStoreTypeById(Long id);

    /**
     * 查询门店类型列表
     * 
     * @param sysStoreType 门店类型
     * @return 门店类型集合
     */
    public List<SysStoreType> selectSysStoreTypeList(SysStoreType sysStoreType);

    /**
     * 新增门店类型
     * 
     * @param sysStoreType 门店类型
     * @return 结果
     */
    public int insertSysStoreType(SysStoreType sysStoreType);

    /**
     * 修改门店类型
     * 
     * @param sysStoreType 门店类型
     * @return 结果
     */
    public int updateSysStoreType(SysStoreType sysStoreType);

    /**
     * 删除门店类型
     * 
     * @param id 门店类型ID
     * @return 结果
     */
    public int deleteSysStoreTypeById(Long id);

    /**
     * 批量删除门店类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysStoreTypeByIds(Long[] ids);
}
