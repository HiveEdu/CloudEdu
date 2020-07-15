package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreWork;
import com.myedu.project.store.domain.vo.YunStoreWorkVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 门店作业管理Service接口
 * 
 * @author lianglongfei
 * @date 2020-07-05
 */
@CacheConfig(cacheNames = "yunStoreWorks")
public interface IYunStoreWorkService 
{
    /**
     * 查询门店作业管理
     * 
     * @param id 门店作业管理ID
     * @return 门店作业管理
     */
    @Cacheable
    public YunStoreWorkVo selectYunStoreWorkById(Long id);

    /**
     * 查询门店作业管理列表
     * 
     * @param yunStoreWork 门店作业管理
     * @return 门店作业管理集合
     */
    @Cacheable
    public List<YunStoreWorkVo> selectYunStoreWorkList(YunStoreWork yunStoreWork);

    /**
     * 新增门店作业管理
     * 
     * @param yunStoreWork 门店作业管理
     * @return 结果
     */
    public int insertYunStoreWork(YunStoreWorkVo yunStoreWork);

    /**
     * 修改门店作业管理
     * 
     * @param yunStoreWork 门店作业管理
     * @return 结果
     */
    public int updateYunStoreWork(YunStoreWorkVo yunStoreWork);

    /**
     * 批量删除门店作业管理
     * 
     * @param ids 需要删除的门店作业管理ID
     * @return 结果
     */
    @CacheEvict
    public int deleteYunStoreWorkByIds(Long[] ids);

    /**
     * 删除门店作业管理信息
     *
     * @param id 门店作业管理ID
     * @return 结果
     */
    public int deleteYunStoreWorkById(Long id);
}
