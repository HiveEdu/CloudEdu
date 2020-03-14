package com.myedu.project.dataBasic.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.dataBasic.mapper.SysMemberLevelMapper;
import com.myedu.project.dataBasic.domain.SysMemberLevel;
import com.myedu.project.dataBasic.service.ISysMemberLevelService;

/**
 * 会员等级Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-03-14
 */
@Service
public class SysMemberLevelServiceImpl implements ISysMemberLevelService 
{
    @Autowired
    private SysMemberLevelMapper sysMemberLevelMapper;

    /**
     * 查询会员等级
     * 
     * @param id 会员等级ID
     * @return 会员等级
     */
    @Override
    public SysMemberLevel selectSysMemberLevelById(Long id)
    {
        return sysMemberLevelMapper.selectSysMemberLevelById(id);
    }

    /**
     * 查询会员等级列表
     * 
     * @param sysMemberLevel 会员等级
     * @return 会员等级
     */
    @Override
    public List<SysMemberLevel> selectSysMemberLevelList(SysMemberLevel sysMemberLevel)
    {
        return sysMemberLevelMapper.selectSysMemberLevelList(sysMemberLevel);
    }

    /**
     * 新增会员等级
     * 
     * @param sysMemberLevel 会员等级
     * @return 结果
     */
    @Override
    public int insertSysMemberLevel(SysMemberLevel sysMemberLevel)
    {
        sysMemberLevel.setCreateTime(DateUtils.getNowDate());
        return sysMemberLevelMapper.insertSysMemberLevel(sysMemberLevel);
    }

    /**
     * 修改会员等级
     * 
     * @param sysMemberLevel 会员等级
     * @return 结果
     */
    @Override
    public int updateSysMemberLevel(SysMemberLevel sysMemberLevel)
    {
        sysMemberLevel.setUpdateTime(DateUtils.getNowDate());
        return sysMemberLevelMapper.updateSysMemberLevel(sysMemberLevel);
    }

    /**
     * 批量删除会员等级
     * 
     * @param ids 需要删除的会员等级ID
     * @return 结果
     */
    @Override
    public int deleteSysMemberLevelByIds(Long[] ids)
    {
        return sysMemberLevelMapper.deleteSysMemberLevelByIds(ids);
    }

    /**
     * 删除会员等级信息
     * 
     * @param id 会员等级ID
     * @return 结果
     */
    @Override
    public int deleteSysMemberLevelById(Long id)
    {
        return sysMemberLevelMapper.deleteSysMemberLevelById(id);
    }
}
