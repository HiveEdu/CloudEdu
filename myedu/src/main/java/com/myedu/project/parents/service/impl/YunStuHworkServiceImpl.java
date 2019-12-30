package com.myedu.project.parents.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.project.parents.domain.vo.YunStuHworkVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.parents.mapper.YunStuHworkMapper;
import com.myedu.project.parents.domain.YunStuHwork;
import com.myedu.project.parents.service.IYunStuHworkService;

/**
 * 学生作业Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
@Service
public class YunStuHworkServiceImpl implements IYunStuHworkService 
{
    @Autowired
    private YunStuHworkMapper yunStuHworkMapper;

    /**
     * 查询学生作业
     * 
     * @param id 学生作业ID
     * @return 学生作业
     */
    @Override
    public YunStuHworkVo selectYunStuHworkById(Long id)
    {
        return yunStuHworkMapper.selectYunStuHworkById(id);
    }

    /**
     * 查询学生作业列表
     * 
     * @param yunStuHwork 学生作业
     * @return 学生作业
     */
    @Override
    public List<YunStuHworkVo> selectYunStuHworkList(YunStuHworkVo yunStuHwork)
    {
        return yunStuHworkMapper.selectYunStuHworkList(yunStuHwork);
    }

    /**
     * 新增学生作业
     * 
     * @param yunStuHwork 学生作业
     * @return 结果
     */
    @Override
    public int insertYunStuHwork(YunStuHwork yunStuHwork)
    {
        yunStuHwork.setCreateTime(DateUtils.getNowDate());
        return yunStuHworkMapper.insertYunStuHwork(yunStuHwork);
    }

    /**
     * 修改学生作业
     * 
     * @param yunStuHwork 学生作业
     * @return 结果
     */
    @Override
    public int updateYunStuHwork(YunStuHwork yunStuHwork)
    {
        yunStuHwork.setUpdateTime(DateUtils.getNowDate());
        return yunStuHworkMapper.updateYunStuHwork(yunStuHwork);
    }

    /**
     * 批量删除学生作业
     * 
     * @param ids 需要删除的学生作业ID
     * @return 结果
     */
    @Override
    public int deleteYunStuHworkByIds(Long[] ids)
    {
        return yunStuHworkMapper.deleteYunStuHworkByIds(ids);
    }

    /**
     * 删除学生作业信息
     * 
     * @param id 学生作业ID
     * @return 结果
     */
    @Override
    public int deleteYunStuHworkById(Long id)
    {
        return yunStuHworkMapper.deleteYunStuHworkById(id);
    }
}
