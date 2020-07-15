package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreAccount;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 门店账目记录Service接口
 * 
 * @author lsp
 * @date 2020-04-25
 */
@CacheConfig(cacheNames = "accounts")
public interface IYunStoreAccountService 
{
    /**
     * 查询门店账目记录
     * 
     * @param id 门店账目记录ID
     * @return 门店账目记录
     */
    @Cacheable
    public YunStoreAccount selectYunStoreAccountById(Long id);

    /**
     * 查询门店账目记录列表
     * 
     * @param yunStoreAccount 门店账目记录
     * @return 门店账目记录集合
     */
    @Cacheable
    public List<YunStoreAccount> selectYunStoreAccountList(YunStoreAccount yunStoreAccount);

    /**
     * 新增门店账目记录
     * 
     * @param yunStoreAccount 门店账目记录
     * @return 结果
     */
    public int insertYunStoreAccount(YunStoreAccount yunStoreAccount);

    /**
     * 修改门店账目记录
     * 
     * @param yunStoreAccount 门店账目记录
     * @return 结果
     */
    public int updateYunStoreAccount(YunStoreAccount yunStoreAccount);

    /**
     * 批量删除门店账目记录
     * 
     * @param ids 需要删除的门店账目记录ID
     * @return 结果
     */
    @CacheEvict
    public int deleteYunStoreAccountByIds(Long[] ids);

    /**
     * 删除门店账目记录信息
     * 
     * @param id 门店账目记录ID
     * @return 结果
     */
    public int deleteYunStoreAccountById(Long id);
}
