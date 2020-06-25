package com.myedu.project.system.service;

import com.myedu.project.system.domain.YunUserCollect;
import java.util.List;

/**
 * 用户收藏Service接口
 * 
 * @author myedu
 * @date 2020-06-25
 */
public interface IYunUserCollectService
{


    /**
     * 查询用户收藏
     * 
     * @param id 用户收藏ID
     * @return 用户收藏
     */
    public YunUserCollect selectYunUserCollectById(Long id);

    /**
     * 查询用户收藏列表
     * 
     * @param yunUserCollect 用户收藏
     * @return 用户收藏集合
     */
    public List<YunUserCollect> selectYunUserCollectList(YunUserCollect yunUserCollect);

    /**
     * 新增用户收藏
     * 
     * @param yunUserCollect 用户收藏
     * @return 结果
     */
    public int insertYunUserCollect(YunUserCollect yunUserCollect);

    /**
     * 修改用户收藏
     * 
     * @param yunUserCollect 用户收藏
     * @return 结果
     */
    public int updateYunUserCollect(YunUserCollect yunUserCollect);

    /**
     * 批量删除用户收藏
     * 
     * @param ids 需要删除的用户收藏ID
     * @return 结果
     */
    public int deleteYunUserCollectByIds(Long[] ids);

    /**
     * 删除用户收藏信息
     * 
     * @param storeId 用户收藏ID
     * @return 结果
     */
     int delete(Long storeId);
}
