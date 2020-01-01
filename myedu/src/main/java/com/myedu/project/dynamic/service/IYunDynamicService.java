package com.myedu.project.dynamic.service;

import com.myedu.project.dynamic.domain.YunDynamic;
import java.util.List;

/**
 * 云托管动态管理Service接口
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
public interface IYunDynamicService 
{
    /**
     * 查询云托管动态管理
     * 
     * @param id 云托管动态管理ID
     * @return 云托管动态管理
     */
    public YunDynamic selectYunDynamicById(Long id);

    /**
     * 查询云托管动态管理列表
     * 
     * @param yunDynamic 云托管动态管理
     * @return 云托管动态管理集合
     */
    public List<YunDynamic> selectYunDynamicList(YunDynamic yunDynamic);

    /**
     * 新增云托管动态管理
     * 
     * @param yunDynamic 云托管动态管理
     * @return 结果
     */
    public int insertYunDynamic(YunDynamic yunDynamic);

    /**
     * 修改云托管动态管理
     * 
     * @param yunDynamic 云托管动态管理
     * @return 结果
     */
    public int updateYunDynamic(YunDynamic yunDynamic);

    /**
     * 批量删除云托管动态管理
     * 
     * @param ids 需要删除的云托管动态管理ID
     * @return 结果
     */
    public int deleteYunDynamicByIds(Long[] ids);

    /**
     * 删除云托管动态管理信息
     * 
     * @param id 云托管动态管理ID
     * @return 结果
     */
    public int deleteYunDynamicById(Long id);
}
