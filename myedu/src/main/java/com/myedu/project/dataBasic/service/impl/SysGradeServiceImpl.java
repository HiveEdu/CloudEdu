package com.myedu.project.dataBasic.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.dataBasic.mapper.SysGradeMapper;
import com.myedu.project.dataBasic.domain.SysGrade;
import com.myedu.project.dataBasic.service.ISysGradeService;

/**
 * 年级基础Service业务层处理
 * 
 * @author myedu
 * @date 2019-12-26
 */
@Service
public class SysGradeServiceImpl implements ISysGradeService 
{
    @Autowired
    private SysGradeMapper sysGradeMapper;

    /**
     * 查询年级基础
     * 
     * @param id 年级基础ID
     * @return 年级基础
     */
    @Override
    public SysGrade selectSysGradeById(Long id)
    {
        return sysGradeMapper.selectSysGradeById(id);
    }

    /**
     * 查询年级基础列表
     * 
     * @param sysGrade 年级基础
     * @return 年级基础
     */
    @Override
    public List<SysGrade> selectSysGradeList(SysGrade sysGrade)
    {
        return sysGradeMapper.selectSysGradeList(sysGrade);
    }

    /**
     * 新增年级基础
     * 
     * @param sysGrade 年级基础
     * @return 结果
     */
    @Override
    public int insertSysGrade(SysGrade sysGrade)
    {
        sysGrade.setCreateTime(DateUtils.getNowDate());
        return sysGradeMapper.insertSysGrade(sysGrade);
    }

    /**
     * 修改年级基础
     * 
     * @param sysGrade 年级基础
     * @return 结果
     */
    @Override
    public int updateSysGrade(SysGrade sysGrade)
    {
        sysGrade.setUpdateTime(DateUtils.getNowDate());
        return sysGradeMapper.updateSysGrade(sysGrade);
    }

    /**
     * 批量删除年级基础
     * 
     * @param ids 需要删除的年级基础ID
     * @return 结果
     */
    @Override
    public int deleteSysGradeByIds(Long[] ids)
    {
        return sysGradeMapper.deleteSysGradeByIds(ids);
    }

    /**
     * 删除年级基础信息
     * 
     * @param id 年级基础ID
     * @return 结果
     */
    @Override
    public int deleteSysGradeById(Long id)
    {
        return sysGradeMapper.deleteSysGradeById(id);
    }
}
