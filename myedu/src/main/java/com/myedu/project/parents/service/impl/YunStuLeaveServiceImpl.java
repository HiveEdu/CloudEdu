package com.myedu.project.parents.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.project.parents.domain.vo.YunStuLeaveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.parents.mapper.YunStuLeaveMapper;
import com.myedu.project.parents.domain.YunStuLeave;
import com.myedu.project.parents.service.IYunStuLeaveService;

/**
 * 学生请假Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2019-12-28
 */
@Service
public class YunStuLeaveServiceImpl implements IYunStuLeaveService 
{
    @Autowired
    private YunStuLeaveMapper yunStuLeaveMapper;

    /**
     * 查询学生请假
     * 
     * @param id 学生请假ID
     * @return 学生请假
     */
    @Override
    public YunStuLeaveVo selectYunStuLeaveById(Long id)
    {
        return yunStuLeaveMapper.selectYunStuLeaveById(id);
    }

    /**
     * 查询学生请假列表
     * 
     * @param yunStuLeave 学生请假
     * @return 学生请假
     */
    @Override
    public List<YunStuLeaveVo> selectYunStuLeaveList(YunStuLeaveVo yunStuLeave)
    {
        return yunStuLeaveMapper.selectYunStuLeaveList(yunStuLeave);
    }

    /**
     * 新增学生请假
     * 
     * @param yunStuLeave 学生请假
     * @return 结果
     */
    @Override
    public int insertYunStuLeave(YunStuLeave yunStuLeave)
    {
        yunStuLeave.setCreateTime(DateUtils.getNowDate());
        return yunStuLeaveMapper.insertYunStuLeave(yunStuLeave);
    }

    /**
     * 修改学生请假
     * 
     * @param yunStuLeave 学生请假
     * @return 结果
     */
    @Override
    public int updateYunStuLeave(YunStuLeave yunStuLeave)
    {
        yunStuLeave.setUpdateTime(DateUtils.getNowDate());
        return yunStuLeaveMapper.updateYunStuLeave(yunStuLeave);
    }

    /**
     * 批量删除学生请假
     * 
     * @param ids 需要删除的学生请假ID
     * @return 结果
     */
    @Override
    public int deleteYunStuLeaveByIds(Long[] ids)
    {
        return yunStuLeaveMapper.deleteYunStuLeaveByIds(ids);
    }

    /**
     * 删除学生请假信息
     * 
     * @param id 学生请假ID
     * @return 结果
     */
    @Override
    public int deleteYunStuLeaveById(Long id)
    {
        return yunStuLeaveMapper.deleteYunStuLeaveById(id);
    }
}
