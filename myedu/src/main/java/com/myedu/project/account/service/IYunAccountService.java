package com.myedu.project.account.service;

import com.myedu.project.account.domain.YunAccount;
import java.util.List;

/**
 * 账户管理Service接口
 * 
 * @author 梁少鹏
 * @date 2020-01-25
 */
public interface IYunAccountService 
{
    /**
     * 查询账户管理
     * 
     * @param id 账户管理ID
     * @return 账户管理
     */
    public YunAccount selectYunAccountById(Long id);

    /**
     * 查询账户管理列表
     * 
     * @param yunAccount 账户管理
     * @return 账户管理集合
     */
    public List<YunAccount> selectYunAccountList(YunAccount yunAccount);

    /**
     * 新增账户管理
     * 
     * @param yunAccount 账户管理
     * @return 结果
     */
    public int insertYunAccount(YunAccount yunAccount);

    /**
     * 修改账户管理
     * 
     * @param yunAccount 账户管理
     * @return 结果
     */
    public int updateYunAccount(YunAccount yunAccount);

    /**
     * 批量删除账户管理
     * 
     * @param ids 需要删除的账户管理ID
     * @return 结果
     */
    public int deleteYunAccountByIds(Long[] ids);

    /**
     * 删除账户管理信息
     * 
     * @param id 账户管理ID
     * @return 结果
     */
    public int deleteYunAccountById(Long id);
}
