package com.myedu.project.hometeacher.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.hometeacher.mapper.YunUserInfoMapper;
import com.myedu.project.hometeacher.domain.YunUserInfo;
import com.myedu.project.hometeacher.service.IYunUserInfoService;

/**
 * 家教老师表Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2020-02-10
 */
@Service
public class YunUserInfoServiceImpl implements IYunUserInfoService 
{
    @Autowired
    private YunUserInfoMapper yunUserInfoMapper;

    /**
     * 查询家教老师表
     * 
     * @param id 家教老师表ID
     * @return 家教老师表
     */
    @Override
    public YunUserInfo selectYunUserInfoById(Long id)
    {
        return yunUserInfoMapper.selectYunUserInfoById(id);
    }

    /**
     * 查询家教老师表列表
     * 
     * @param yunUserInfo 家教老师表
     * @return 家教老师表
     */
    @Override
    public List<YunUserInfo> selectYunUserInfoList(YunUserInfo yunUserInfo)
    {
        return yunUserInfoMapper.selectYunUserInfoList(yunUserInfo);
    }

    /**
     * 新增家教老师表
     * 
     * @param yunUserInfo 家教老师表
     * @return 结果
     */
    @Override
    public int insertYunUserInfo(YunUserInfo yunUserInfo)
    {
        yunUserInfo.setUserId(SecurityUtils.getUserId());
        yunUserInfo.setCreateBy(SecurityUtils.getUsername());
        yunUserInfo.setCreateTime(DateUtils.getNowDate());
        return yunUserInfoMapper.insertYunUserInfo(yunUserInfo);
    }

    /**
     * 修改家教老师表
     * 
     * @param yunUserInfo 家教老师表
     * @return 结果
     */
    @Override
    public int updateYunUserInfo(YunUserInfo yunUserInfo)
    {
        yunUserInfo.setUpdateTime(DateUtils.getNowDate());
        return yunUserInfoMapper.updateYunUserInfo(yunUserInfo);
    }

    /**
     * 批量删除家教老师表
     * 
     * @param ids 需要删除的家教老师表ID
     * @return 结果
     */
    @Override
    public int deleteYunUserInfoByIds(Long[] ids)
    {
        return yunUserInfoMapper.deleteYunUserInfoByIds(ids);
    }

    /**
     * 删除家教老师表信息
     * 
     * @param id 家教老师表ID
     * @return 结果
     */
    @Override
    public int deleteYunUserInfoById(Long id)
    {
        return yunUserInfoMapper.deleteYunUserInfoById(id);
    }
}
