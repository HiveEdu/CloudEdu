package com.myedu.project.dataBasic.service;

import com.myedu.project.dataBasic.domain.SysLabel;
import java.util.List;

/**
 * 标签管理Service接口
 * 
 * @author 梁龙飞
 * @date 2020-01-04
 */
public interface ISysLabelService 
{
    /**
     * 查询标签管理
     * 
     * @param id 标签管理ID
     * @return 标签管理
     */
    public SysLabel selectSysLabelById(Long id);

    /**
     * 查询标签管理列表
     * 
     * @param sysLabel 标签管理
     * @return 标签管理集合
     */
    public List<SysLabel> selectSysLabelList(SysLabel sysLabel);

    /**
     * 新增标签管理
     * 
     * @param sysLabel 标签管理
     * @return 结果
     */
    public int insertSysLabel(SysLabel sysLabel);

    /**
     * 修改标签管理
     * 
     * @param sysLabel 标签管理
     * @return 结果
     */
    public int updateSysLabel(SysLabel sysLabel);

    /**
     * 批量删除标签管理
     * 
     * @param ids 需要删除的标签管理ID
     * @return 结果
     */
    public int deleteSysLabelByIds(Long[] ids);

    /**
     * 删除标签管理信息
     * 
     * @param id 标签管理ID
     * @return 结果
     */
    public int deleteSysLabelById(Long id);

    /**
     * 根据标签ID获取标签类型选择框列表
     *
     * @param id 标签ID
     * @return 选中门店类型ID列表
     */
    public List<Integer> selectLabelListById(Long id);
}
