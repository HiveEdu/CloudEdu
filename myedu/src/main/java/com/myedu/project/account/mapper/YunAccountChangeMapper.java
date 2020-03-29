package com.myedu.project.account.mapper;

import com.myedu.project.account.domain.YunAccountChange;
import java.util.List;

/**
 * 账户资金变动流水Mapper接口
 * 
 * @author 梁少鹏
 * @date 2020-01-25
 */
public interface YunAccountChangeMapper 
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
     * 删除账户资金变动流水
     * 
     * @param id 账户资金变动流水ID
     * @return 结果
     */
    public int deleteYunAccountChangeById(Long id);

    /**
     * 批量删除账户资金变动流水
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunAccountChangeByIds(Long[] ids);

    /**
     * 根据用户id删除账户变动表
     *
     * @param userId 账户id
     * @return 结果
     */
    public int deleteYunAccountChangeByUserId(Long userId);


    /*
     * @Description :根据用户Id查询账单
     * @Author : 梁少鹏
     * @Date : 2020/2/2 17:50
     */
    public List<YunAccountChange> selectYunAccountChangeByUserId(Long userId);
}
