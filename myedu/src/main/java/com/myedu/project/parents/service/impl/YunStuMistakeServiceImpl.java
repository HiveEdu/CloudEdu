package com.myedu.project.parents.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.project.parents.domain.vo.YunStuMistakeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.parents.mapper.YunStuMistakeMapper;
import com.myedu.project.parents.domain.YunStuMistake;
import com.myedu.project.parents.service.IYunStuMistakeService;

/**
 * 学生错题记录Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
@Service
public class YunStuMistakeServiceImpl implements IYunStuMistakeService 
{
    @Autowired
    private YunStuMistakeMapper yunStuMistakeMapper;

    /**
     * 查询学生错题记录
     * 
     * @param id 学生错题记录ID
     * @return 学生错题记录
     */
    @Override
    public YunStuMistakeVo selectYunStuMistakeById(Long id)
    {
        return yunStuMistakeMapper.selectYunStuMistakeById(id);
    }

    /**
     * 查询学生错题记录列表
     * 
     * @param yunStuMistake 学生错题记录
     * @return 学生错题记录
     */
    @Override
    public List<YunStuMistakeVo> selectYunStuMistakeList(YunStuMistakeVo yunStuMistake)
    {
        return yunStuMistakeMapper.selectYunStuMistakeList(yunStuMistake);
    }

    /**
     * 新增学生错题记录
     * 
     * @param yunStuMistake 学生错题记录
     * @return 结果
     */
    @Override
    public int insertYunStuMistake(YunStuMistake yunStuMistake)
    {
        yunStuMistake.setCreateTime(DateUtils.getNowDate());
        return yunStuMistakeMapper.insertYunStuMistake(yunStuMistake);
    }

    /**
     * 修改学生错题记录
     * 
     * @param yunStuMistake 学生错题记录
     * @return 结果
     */
    @Override
    public int updateYunStuMistake(YunStuMistake yunStuMistake)
    {
        yunStuMistake.setUpdateTime(DateUtils.getNowDate());
        return yunStuMistakeMapper.updateYunStuMistake(yunStuMistake);
    }

    /**
     * 批量删除学生错题记录
     * 
     * @param ids 需要删除的学生错题记录ID
     * @return 结果
     */
    @Override
    public int deleteYunStuMistakeByIds(Long[] ids)
    {
        return yunStuMistakeMapper.deleteYunStuMistakeByIds(ids);
    }

    /**
     * 删除学生错题记录信息
     * 
     * @param id 学生错题记录ID
     * @return 结果
     */
    @Override
    public int deleteYunStuMistakeById(Long id)
    {
        return yunStuMistakeMapper.deleteYunStuMistakeById(id);
    }
}
