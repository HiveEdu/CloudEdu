package com.myedu.project.parents.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.parents.mapper.YunStuHwMapper;
import com.myedu.project.parents.domain.YunStuHw;
import com.myedu.project.parents.service.IYunStuHwService;

/**
 * 学生身高体重记录Service业务层处理
 * 
 * @author  llf
 * @date 2019-12-29
 */
@Service
public class YunStuHwServiceImpl implements IYunStuHwService 
{
    @Autowired
    private YunStuHwMapper yunStuHwMapper;

    /**
     * 查询学生身高体重记录
     * 
     * @param id 学生身高体重记录ID
     * @return 学生身高体重记录
     */
    @Override
    public YunStuHw selectYunStuHwById(Long id)
    {
        return yunStuHwMapper.selectYunStuHwById(id);
    }

    /**
     * 查询学生身高体重记录列表
     * 
     * @param yunStuHw 学生身高体重记录
     * @return 学生身高体重记录
     */
    @Override
    public List<YunStuHw> selectYunStuHwList(YunStuHw yunStuHw)
    {
        return yunStuHwMapper.selectYunStuHwList(yunStuHw);
    }

    /**
     * 新增学生身高体重记录
     * 
     * @param yunStuHw 学生身高体重记录
     * @return 结果
     */
    @Override
    public int insertYunStuHw(YunStuHw yunStuHw)
    {
        yunStuHw.setCreateTime(DateUtils.getNowDate());
        return yunStuHwMapper.insertYunStuHw(yunStuHw);
    }

    /**
     * 修改学生身高体重记录
     * 
     * @param yunStuHw 学生身高体重记录
     * @return 结果
     */
    @Override
    public int updateYunStuHw(YunStuHw yunStuHw)
    {
        yunStuHw.setUpdateTime(DateUtils.getNowDate());
        return yunStuHwMapper.updateYunStuHw(yunStuHw);
    }

    /**
     * 批量删除学生身高体重记录
     * 
     * @param ids 需要删除的学生身高体重记录ID
     * @return 结果
     */
    @Override
    public int deleteYunStuHwByIds(Long[] ids)
    {
        return yunStuHwMapper.deleteYunStuHwByIds(ids);
    }

    /**
     * 删除学生身高体重记录信息
     * 
     * @param id 学生身高体重记录ID
     * @return 结果
     */
    @Override
    public int deleteYunStuHwById(Long id)
    {
        return yunStuHwMapper.deleteYunStuHwById(id);
    }
}
