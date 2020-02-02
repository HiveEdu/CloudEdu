package com.myedu.project.account.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.account.mapper.YunAccountChangeMapper;
import com.myedu.project.account.domain.YunAccountChange;
import com.myedu.project.account.service.IYunAccountChangeService;

/**
 * 账户资金变动流水Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-25
 */
@Service
public class YunAccountChangeServiceImpl implements IYunAccountChangeService 
{
    @Autowired
    private YunAccountChangeMapper yunAccountChangeMapper;

    /**
     * 查询账户资金变动流水
     * 
     * @param id 账户资金变动流水ID
     * @return 账户资金变动流水
     */
    @Override
    public YunAccountChange selectYunAccountChangeById(Long id)
    {
        return yunAccountChangeMapper.selectYunAccountChangeById(id);
    }

    /**
     * 查询账户资金变动流水列表
     * 
     * @param yunAccountChange 账户资金变动流水
     * @return 账户资金变动流水
     */
    @Override
    public List<YunAccountChange> selectYunAccountChangeList(YunAccountChange yunAccountChange)
    {
        return yunAccountChangeMapper.selectYunAccountChangeList(yunAccountChange);
    }

    /**
     * 新增账户资金变动流水
     * 
     * @param yunAccountChange 账户资金变动流水
     * @return 结果
     */
    @Override
    public int insertYunAccountChange(YunAccountChange yunAccountChange)
    {
        yunAccountChange.setCreateTime(DateUtils.getNowDate());
        return yunAccountChangeMapper.insertYunAccountChange(yunAccountChange);
    }

    /**
     * 修改账户资金变动流水
     * 
     * @param yunAccountChange 账户资金变动流水
     * @return 结果
     */
    @Override
    public int updateYunAccountChange(YunAccountChange yunAccountChange)
    {
        yunAccountChange.setUpdateTime(DateUtils.getNowDate());
        return yunAccountChangeMapper.updateYunAccountChange(yunAccountChange);
    }

    /**
     * 批量删除账户资金变动流水
     * 
     * @param ids 需要删除的账户资金变动流水ID
     * @return 结果
     */
    @Override
    public int deleteYunAccountChangeByIds(Long[] ids)
    {
        return yunAccountChangeMapper.deleteYunAccountChangeByIds(ids);
    }

    /**
     * 删除账户资金变动流水信息
     * 
     * @param id 账户资金变动流水ID
     * @return 结果
     */
    @Override
    public int deleteYunAccountChangeById(Long id)
    {
        return yunAccountChangeMapper.deleteYunAccountChangeById(id);
    }


    /*
     * @Description :根据账户Id查询账单
     * @Author : 梁少鹏
     * @Date : 2020/2/2 17:50
     */
    @Override
    public List<YunAccountChange> selectYunAccountChangeByAccountId(Long accountId) {
        return yunAccountChangeMapper.selectYunAccountChangeByAccountId(accountId);
    }
}
