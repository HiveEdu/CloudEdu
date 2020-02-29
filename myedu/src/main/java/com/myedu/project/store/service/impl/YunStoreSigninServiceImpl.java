package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreSigninMapper;
import com.myedu.project.store.domain.YunStoreSignin;
import com.myedu.project.store.service.IYunStoreSigninService;

/**
 * 点名签到Service业务层处理
 * 
 * @author 梁龙飞
 * @date 2020-02-29
 */
@Service
public class YunStoreSigninServiceImpl implements IYunStoreSigninService 
{
    @Autowired
    private YunStoreSigninMapper yunStoreSigninMapper;

    /**
     * 查询点名签到
     * 
     * @param id 点名签到ID
     * @return 点名签到
     */
    @Override
    public YunStoreSignin selectYunStoreSigninById(Long id)
    {
        return yunStoreSigninMapper.selectYunStoreSigninById(id);
    }

    /**
     * 查询点名签到列表
     * 
     * @param yunStoreSignin 点名签到
     * @return 点名签到
     */
    @Override
    public List<YunStoreSignin> selectYunStoreSigninList(YunStoreSignin yunStoreSignin)
    {
        return yunStoreSigninMapper.selectYunStoreSigninList(yunStoreSignin);
    }

    /**
     * 新增点名签到
     * 
     * @param yunStoreSignin 点名签到
     * @return 结果
     */
    @Override
    public int insertYunStoreSignin(YunStoreSignin yunStoreSignin)
    {
        yunStoreSignin.setCreateTime(DateUtils.getNowDate());
        return yunStoreSigninMapper.insertYunStoreSignin(yunStoreSignin);
    }

    /**
     * 修改点名签到
     * 
     * @param yunStoreSignin 点名签到
     * @return 结果
     */
    @Override
    public int updateYunStoreSignin(YunStoreSignin yunStoreSignin)
    {
        yunStoreSignin.setUpdateTime(DateUtils.getNowDate());
        return yunStoreSigninMapper.updateYunStoreSignin(yunStoreSignin);
    }

    /**
     * 批量删除点名签到
     * 
     * @param ids 需要删除的点名签到ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreSigninByIds(Long[] ids)
    {
        return yunStoreSigninMapper.deleteYunStoreSigninByIds(ids);
    }

    /**
     * 删除点名签到信息
     * 
     * @param id 点名签到ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreSigninById(Long id)
    {
        return yunStoreSigninMapper.deleteYunStoreSigninById(id);
    }
}
