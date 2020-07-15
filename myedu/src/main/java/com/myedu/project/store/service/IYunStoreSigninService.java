package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreSignin;
import com.myedu.project.store.domain.vo.YunStoreSigninVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 点名签到Service接口
 * 
 * @author 梁龙飞
 * @date 2020-02-29
 */
@CacheConfig(cacheNames = "yunStoreSignins")
public interface IYunStoreSigninService 
{
    /**
     * 查询点名签到
     * 
     * @param id 点名签到ID
     * @return 点名签到
     */
    @Cacheable
    public YunStoreSigninVo selectYunStoreSigninById(Long id);

    /**
     * 查询点名签到列表
     * 
     * @param yunStoreSignin 点名签到
     * @return 点名签到集合
     */
    @Cacheable
    public List<YunStoreSigninVo> selectYunStoreSigninList(YunStoreSigninVo yunStoreSignin);

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
     * 批量删除点名签到
     * 
     * @param ids 需要删除的点名签到ID
     * @return 结果
     */
    @CacheEvict
    public int deleteYunStoreSigninByIds(Long[] ids);

    /**
     * 删除点名签到信息
     * 
     * @param id 点名签到ID
     * @return 结果
     */
    public int deleteYunStoreSigninById(Long id);
}
