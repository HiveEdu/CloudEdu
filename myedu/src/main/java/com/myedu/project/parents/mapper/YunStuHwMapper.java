package com.myedu.project.parents.mapper;

import com.myedu.project.parents.domain.YunStuHw;
import com.myedu.project.parents.domain.vo.YunStuHwVo;

import java.util.List;

/**
 * 学生身高体重记录Mapper接口
 * 
 * @author  llf
 * @date 2019-12-29
 */
public interface YunStuHwMapper 
{
    /**
     * 查询学生身高体重记录
     * 
     * @param id 学生身高体重记录ID
     * @return 学生身高体重记录
     */
    public YunStuHwVo selectYunStuHwById(Long id);

    /**
     * 查询学生身高体重记录列表
     * 
     * @param yunStuHw 学生身高体重记录
     * @return 学生身高体重记录集合
     */
    public List<YunStuHwVo> selectYunStuHwList(YunStuHwVo yunStuHw);
    public int insertYunStuHw(YunStuHw yunStuHw);

    /**
     * 修改学生身高体重记录
     * 
     * @param yunStuHw 学生身高体重记录
     * @return 结果
     */
    public int updateYunStuHw(YunStuHw yunStuHw);

    /**
     * 删除学生身高体重记录
     * 
     * @param id 学生身高体重记录ID
     * @return 结果
     */
    public int deleteYunStuHwById(Long id);

    /**
     * 批量删除学生身高体重记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStuHwByIds(Long[] ids);
}
