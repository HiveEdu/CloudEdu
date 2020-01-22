package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.mapper.YunStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunCourseMapper;
import com.myedu.project.store.domain.YunCourse;
import com.myedu.project.store.service.IYunCourseService;

/**
 * 课程Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-21
 */
@Service
public class YunCourseServiceImpl implements IYunCourseService 
{
    @Autowired
    private YunCourseMapper yunCourseMapper;
    @Autowired
    private YunStoreMapper yunStoreMapper;
    /**
     * 查询课程
     * 
     * @param id 课程ID
     * @return 课程
     */
    @Override
    public YunCourse selectYunCourseById(Long id)
    {
        return yunCourseMapper.selectYunCourseById(id);
    }

    /**
     * 查询课程列表
     * 
     * @param yunCourse 课程
     * @return 课程
     */
    @Override
    public List<YunCourse> selectYunCourseList(YunCourse yunCourse)
    {
        return yunCourseMapper.selectYunCourseList(yunCourse);
    }

    /**
     * 新增课程
     * 
     * @param yunCourse 课程
     * @return 结果
     */
    @Override
    public int insertYunCourse(YunCourse yunCourse)
    {
        YunStore yunStore=new YunStore();
        yunStore.setCreateById(SecurityUtils.getUserId());
        List<YunStore> yunStores=yunStoreMapper.selectYunStoreList(yunStore);
        Long storeId=null;
        if(!yunStores.isEmpty()){
            storeId=yunStores.get(0).getId();
            yunCourse.setStoreId(storeId);
        }
        yunCourse.setCreateById(SecurityUtils.getUserId());
        yunCourse.setCreateBy(SecurityUtils.getUsername());
        yunCourse.setCreateTime(DateUtils.getNowDate());
        return yunCourseMapper.insertYunCourse(yunCourse);
    }

    /**
     * 修改课程
     * 
     * @param yunCourse 课程
     * @return 结果
     */
    @Override
    public int updateYunCourse(YunCourse yunCourse)
    {
        yunCourse.setUpdateTime(DateUtils.getNowDate());
        return yunCourseMapper.updateYunCourse(yunCourse);
    }

    /**
     * 批量删除课程
     * 
     * @param ids 需要删除的课程ID
     * @return 结果
     */
    @Override
    public int deleteYunCourseByIds(Long[] ids)
    {
        return yunCourseMapper.deleteYunCourseByIds(ids);
    }

    /**
     * 删除课程信息
     * 
     * @param id 课程ID
     * @return 结果
     */
    @Override
    public int deleteYunCourseById(Long id)
    {
        return yunCourseMapper.deleteYunCourseById(id);
    }
}
