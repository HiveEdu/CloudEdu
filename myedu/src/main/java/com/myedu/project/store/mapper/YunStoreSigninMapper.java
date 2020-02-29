package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunStoreSignin;
import java.util.List;

/**
 * 点名签到Mapper接口
 * 
 * @author 梁龙飞
 * @date 2020-02-29
 */
public interface YunStoreSigninMapper 
{
    /**
     * 查询点名签到
     * 
     * @param id 点名签到ID
     * @return 点名签到
     */
    public YunStoreSignin selectYunStoreSigninById(Long id);

    /**
     * 查询点名签到列表
     * 
     * @param yunStoreSignin 点名签到
     * @return 点名签到集合
     */
    public List<YunStoreSignin> selectYunStoreSigninList(YunStoreSignin yunStoreSignin);

    /**
     * 新增点名签到
     * 
     * @param yunStoreSignin 点名签到
     * @return 结果
     */
    public int insertYunStoreSignin(YunStoreSignin yunStoreSignin);

    /**
     * 修改点名签到
     * 
     * @param yunStoreSignin 点名签到
     * @return 结果
     */
    public int updateYunStoreSignin(YunStoreSignin yunStoreSignin);

    /**
     * 删除点名签到
     * 
     * @param id 点名签到ID
     * @return 结果
     */
    public int deleteYunStoreSigninById(Long id);

    /**
     * 批量删除点名签到
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStoreSigninByIds(Long[] ids);
}
