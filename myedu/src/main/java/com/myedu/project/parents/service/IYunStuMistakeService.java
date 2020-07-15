package com.myedu.project.parents.service;

import com.myedu.project.parents.domain.YunStuMistake;
import com.myedu.project.parents.domain.vo.YunStuMistakeVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 学生错题记录Service接口
 * 
 * @author 梁少鹏
 * @date 2019-12-29
 */
@CacheConfig(cacheNames = "yunStuMistakes")
public interface IYunStuMistakeService 
{
    /**
     * 查询学生错题记录
     * 
     * @param id 学生错题记录ID
     * @return 学生错题记录
     */
    @Cacheable
    public YunStuMistakeVo selectYunStuMistakeById(Long id);

    /**
     * 查询学生错题记录列表
     * 
     * @param yunStuMistake 学生错题记录
     * @return 学生错题记录集合
     */
    @Cacheable
    public List<YunStuMistakeVo> selectYunStuMistakeList(YunStuMistakeVo yunStuMistake);

    /**
     * 新增学生错题记录
     * 
     * @param yunStuMistake 学生错题记录
     * @return 结果
     */
    public int insertYunStuMistake(YunStuMistake yunStuMistake);

    /**
     * 修改学生错题记录
     * 
     * @param yunStuMistake 学生错题记录
     * @return 结果
     */
    public int updateYunStuMistake(YunStuMistake yunStuMistake);

    /**
     * 批量删除学生错题记录
     * 
     * @param ids 需要删除的学生错题记录ID
     * @return 结果
     */
    @CacheEvict
    public int deleteYunStuMistakeByIds(Long[] ids);

    /**
     * 删除学生错题记录信息
     * 
     * @param id 学生错题记录ID
     * @return 结果
     */
    public int deleteYunStuMistakeById(Long id);
}
