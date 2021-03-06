package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunStoreAccount;
import java.util.List;

/**
 * 门店账目记录Mapper接口
 * 
 * @author lsp
 * @date 2020-04-25
 */
public interface YunStoreAccountMapper 
{
    /**
     * 查询门店账目记录
     * 
     * @param id 门店账目记录ID
     * @return 门店账目记录
     */
    public YunStoreAccount selectYunStoreAccountById(Long id);

    /**
     * 查询门店账目记录列表
     * 
     * @param yunStoreAccount 门店账目记录
     * @return 门店账目记录集合
     */
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
     * 删除门店账目记录
     * 
     * @param id 门店账目记录ID
     * @return 结果
     */
    public int deleteYunStoreAccountById(Long id);

    /**
     * 批量删除门店账目记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStoreAccountByIds(Long[] ids);
}
