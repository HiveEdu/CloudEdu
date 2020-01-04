package com.myedu.project.dataBasic.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.dataBasic.mapper.SysLabelMapper;
import com.myedu.project.dataBasic.domain.SysLabel;
import com.myedu.project.dataBasic.service.ISysLabelService;

/**
 * 标签管理Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2020-01-04
 */
@Service
public class SysLabelServiceImpl implements ISysLabelService 
{
    @Autowired
    private SysLabelMapper sysLabelMapper;

    /**
     * 查询标签管理
     * 
     * @param id 标签管理ID
     * @return 标签管理
     */
    @Override
    public SysLabel selectSysLabelById(Long id)
    {
        return sysLabelMapper.selectSysLabelById(id);
    }

    /**
     * 查询标签管理列表
     * 
     * @param sysLabel 标签管理
     * @return 标签管理
     */
    @Override
    public List<SysLabel> selectSysLabelList(SysLabel sysLabel)
    {
        return sysLabelMapper.selectSysLabelList(sysLabel);
    }

    /**
     * 新增标签管理
     * 
     * @param sysLabel 标签管理
     * @return 结果
     */
    @Override
    public int insertSysLabel(SysLabel sysLabel)
    {
        sysLabel.setCreateTime(DateUtils.getNowDate());
        return sysLabelMapper.insertSysLabel(sysLabel);
    }

    /**
     * 修改标签管理
     * 
     * @param sysLabel 标签管理
     * @return 结果
     */
    @Override
    public int updateSysLabel(SysLabel sysLabel)
    {
        sysLabel.setUpdateTime(DateUtils.getNowDate());
        return sysLabelMapper.updateSysLabel(sysLabel);
    }

    /**
     * 批量删除标签管理
     * 
     * @param ids 需要删除的标签管理ID
     * @return 结果
     */
    @Override
    public int deleteSysLabelByIds(Long[] ids)
    {
        return sysLabelMapper.deleteSysLabelByIds(ids);
    }

    /**
     * 删除标签管理信息
     * 
     * @param id 标签管理ID
     * @return 结果
     */
    @Override
    public int deleteSysLabelById(Long id)
    {
        return sysLabelMapper.deleteSysLabelById(id);
    }
}
