package com.myedu.project.dataBasic.service;

import com.myedu.project.dataBasic.domain.SysGrade;
import java.util.List;

/**
 * 年级基础Service接口
 * 
 * @author myedu
 * @date 2019-12-26
 */
public interface ISysGradeService 
{
    /**
     * 查询年级基础
     * 
     * @param id 年级基础ID
     * @return 年级基础
     */
    public SysGrade selectSysGradeById(Long id);

    /**
     * 查询年级基础列表
     * 
     * @param sysGrade 年级基础
     * @return 年级基础集合
     */
    public List<SysGrade> selectSysGradeList(SysGrade sysGrade);

    /**
     * 新增年级基础
     * 
     * @param sysGrade 年级基础
     * @return 结果
     */
    public int insertSysGrade(SysGrade sysGrade);

    /**
     * 修改年级基础
     * 
     * @param sysGrade 年级基础
     * @return 结果
     */
    public int updateSysGrade(SysGrade sysGrade);

    /**
     * 批量删除年级基础
     * 
     * @param ids 需要删除的年级基础ID
     * @return 结果
     */
    public int deleteSysGradeByIds(Long[] ids);

    /**
     * 删除年级基础信息
     * 
     * @param id 年级基础ID
     * @return 结果
     */
    public int deleteSysGradeById(Long id);
}
