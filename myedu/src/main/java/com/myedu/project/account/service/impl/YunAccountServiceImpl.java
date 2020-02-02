package com.myedu.project.account.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.project.account.domain.YunAccountChange;
import com.myedu.project.account.enums.AccountChangeType;
import com.myedu.project.account.mapper.YunAccountChangeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.account.mapper.YunAccountMapper;
import com.myedu.project.account.domain.YunAccount;
import com.myedu.project.account.service.IYunAccountService;

/**
 * 账户管理Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-25
 */
@Service
public class YunAccountServiceImpl implements IYunAccountService 
{
    @Autowired
    private YunAccountMapper yunAccountMapper;
    @Autowired
    private YunAccountChangeMapper yunAccountChangeMapper;


    /**
     * 查询账户管理
     * 
     * @param id 账户管理ID
     * @return 账户管理
     */
    @Override
    public YunAccount selectYunAccountById(Long id)
    {
        return yunAccountMapper.selectYunAccountById(id);
    }

    /**
     * 查询账户管理列表
     * 
     * @param yunAccount 账户管理
     * @return 账户管理
     */
    @Override
    public List<YunAccount> selectYunAccountList(YunAccount yunAccount)
    {
        return yunAccountMapper.selectYunAccountList(yunAccount);
    }

    /**
     * 新增账户管理
     * 
     * @param yunAccount 账户管理
     * @return 结果
     */
    @Override
    public int insertYunAccount(YunAccount yunAccount)
    {
        yunAccount.setCreateById(SecurityUtils.getUserId());
        yunAccount.setCreateBy(SecurityUtils.getUsername());
        yunAccount.setCreateTime(DateUtils.getNowDate());
        //初始添加总金额与可提金额一致
        yunAccount.setCaseAmount(yunAccount.getTotalAmount());
        int result=yunAccountMapper.insertYunAccount(yunAccount);
        if(result==1){//主表添加成功增加记录表
            YunAccountChange yunAccountChange=new YunAccountChange();
            yunAccountChange.setAccountId(yunAccount.getId());
            yunAccountChange.setPreAmount(new BigDecimal(0));
            yunAccountChange.setCashAmount(yunAccount.getTotalAmount());
            yunAccountChange.setUncashAmount(new BigDecimal(0));
            yunAccountChange.setChangeType(AccountChangeType.RECHARGE.getCode());
            yunAccountChange.setCreateById(SecurityUtils.getUserId());
            yunAccountChange.setCreateBy(SecurityUtils.getUsername());
            yunAccountChange.setCreateTime(DateUtils.getNowDate());
            yunAccountChangeMapper.insertYunAccountChange(yunAccountChange);
        }
        return result;
    }

    /**
     * 修改账户管理
     * 
     * @param yunAccount 账户管理
     * @return 结果
     */
    @Override
    public int updateYunAccount(YunAccount yunAccount)
    {
        yunAccount.setUpdateTime(DateUtils.getNowDate());
        return yunAccountMapper.updateYunAccount(yunAccount);
    }

    /**
     * 批量删除账户管理
     * 
     * @param ids 需要删除的账户管理ID
     * @return 结果
     */
    @Override
    public int deleteYunAccountByIds(Long[] ids)
    {
        for (Long id: ids) {
            //删除关联账户变动表
            yunAccountChangeMapper.deleteYunAccountChangeByAccountId(id);
        }
        return yunAccountMapper.deleteYunAccountByIds(ids);
    }

    /**
     * 删除账户管理信息
     * 
     * @param id 账户管理ID
     * @return 结果
     */
    @Override
    public int deleteYunAccountById(Long id)
    {
        //删除关联账户变动表
        yunAccountChangeMapper.deleteYunAccountChangeByAccountId(id);
        return yunAccountMapper.deleteYunAccountById(id);
    }
}
