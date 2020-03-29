package com.myedu.project.account.service;

import com.myedu.project.account.domain.YunAccountChange;
import java.util.List;

/**
 * 账户资金变动流水Service接口
 * 
 * @author 梁少鹏
 * @date 2020-01-25
 */
public interface IYunAccountChangeService 
{
    /**
     * 查询账户资金变动流水
     * 
     * @param id 账户资金变动流水ID
     * @return 账户资金变动流水
     */
    public YunAccountChange selectYunAccountChangeById(Long id);

    /**
     * 查询账户资金变动流水列表
     * 
     * @param yunAccountChange 账户资金变动流水
     * @return 账户资金变动流水集合
     */
    public List<YunAccountChange> selectYunAccountChangeList(YunAccountChange yunAccountChange);

    /**
     * 新增账户资金变动流水
     * 
     * @param yunAccountChange 账户资金变动流水
     * @return 结果
     */
    public int insertYunAccountChange(YunAccountChange yunAccountChange);

    /**
     * 修改账户资金变动流水
     * 
     * @param yunAccountChange 账户资金变动流水
     * @return 结果
     */
    public int updateYunAccountChange(YunAccountChange yunAccountChange);

    /**
     * 批量删除账户资金变动流水
     * 
     * @param ids 需要删除的账户资金变动流水ID
     * @return 结果
     */
    public int deleteYunAccountChangeByIds(Long[] ids);

    /**
     * 删除账户资金变动流水信息
     * 
     * @param id 账户资金变动流水ID
     * @return 结果
     */
    public int deleteYunAccountChangeById(Long id);



    /*
     * @Description :根据账户Id查询账单
     * @Author : 梁少鹏
     * @Date : 2020/2/2 17:50
     */
    public List<YunAccountChange> selectYunAccountChangeByUserId(Long userId);
}
