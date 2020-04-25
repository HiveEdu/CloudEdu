package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreAccountMapper;
import com.myedu.project.store.domain.YunStoreAccount;
import com.myedu.project.store.service.IYunStoreAccountService;

/**
 * 门店账目记录Service业务层处理
 * 
 * @author lsp
 * @date 2020-04-25
 */
@Service
public class YunStoreAccountServiceImpl implements IYunStoreAccountService 
{
    @Autowired
    private YunStoreAccountMapper yunStoreAccountMapper;

    /**
     * 查询门店账目记录
     * 
     * @param id 门店账目记录ID
     * @return 门店账目记录
     */
    @Override
    public YunStoreAccount selectYunStoreAccountById(Long id)
    {
        return yunStoreAccountMapper.selectYunStoreAccountById(id);
    }

    /**
     * 查询门店账目记录列表
     * 
     * @param yunStoreAccount 门店账目记录
     * @return 门店账目记录
     */
    @Override
    public List<YunStoreAccount> selectYunStoreAccountList(YunStoreAccount yunStoreAccount)
    {
        return yunStoreAccountMapper.selectYunStoreAccountList(yunStoreAccount);
    }

    /**
     * 新增门店账目记录
     * 
     * @param yunStoreAccount 门店账目记录
     * @return 结果
     */
    @Override
    public int insertYunStoreAccount(YunStoreAccount yunStoreAccount)
    {
        yunStoreAccount.setCreateTime(DateUtils.getNowDate());
        return yunStoreAccountMapper.insertYunStoreAccount(yunStoreAccount);
    }

    /**
     * 修改门店账目记录
     * 
     * @param yunStoreAccount 门店账目记录
     * @return 结果
     */
    @Override
    public int updateYunStoreAccount(YunStoreAccount yunStoreAccount)
    {
        yunStoreAccount.setUpdateTime(DateUtils.getNowDate());
        return yunStoreAccountMapper.updateYunStoreAccount(yunStoreAccount);
    }

    /**
     * 批量删除门店账目记录
     * 
     * @param ids 需要删除的门店账目记录ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreAccountByIds(Long[] ids)
    {
        return yunStoreAccountMapper.deleteYunStoreAccountByIds(ids);
    }

    /**
     * 删除门店账目记录信息
     * 
     * @param id 门店账目记录ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreAccountById(Long id)
    {
        return yunStoreAccountMapper.deleteYunStoreAccountById(id);
    }
}
