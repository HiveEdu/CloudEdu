package com.myedu.project.store.service.impl;

import com.myedu.common.utils.DateUtils;
import com.myedu.project.store.domain.YunStoreInterestClass;
import com.myedu.project.store.domain.vo.YunStoreInterestClassVo;
import com.myedu.project.store.mapper.YunStoreInterestClassMapper;
import com.myedu.project.store.service.IYunStoreInterestClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门店兴趣班Service业务层处理
 * 
 * @author myedu
 * @date 2020-07-19
 */
@Service
public class YunStoreInterestClassServiceImpl implements IYunStoreInterestClassService 
{
    @Autowired
    private YunStoreInterestClassMapper yunStoreInterestClassMapper;

    /**
     * 查询门店兴趣班
     * 
     * @param id 门店兴趣班ID
     * @return 门店兴趣班
     */
    @Override
    public YunStoreInterestClassVo selectYunStoreInterestClassById(Long id)
    {
        return yunStoreInterestClassMapper.selectYunStoreInterestClassById(id);
    }

    /**
     * 查询门店兴趣班列表
     * 
     * @param yunStoreInterestClass 门店兴趣班
     * @return 门店兴趣班
     */
    @Override
    public List<YunStoreInterestClassVo> selectYunStoreInterestClassList(YunStoreInterestClass yunStoreInterestClass)
    {
        return yunStoreInterestClassMapper.selectYunStoreInterestClassList(yunStoreInterestClass);
    }

    /**
     * 新增门店兴趣班
     * 
     * @param yunStoreInterestClass 门店兴趣班
     * @return 结果
     */
    @Override
    public int insertYunStoreInterestClass(YunStoreInterestClass yunStoreInterestClass)
    {
        yunStoreInterestClass.setCreateTime(DateUtils.getNowDate());
        return yunStoreInterestClassMapper.insertYunStoreInterestClass(yunStoreInterestClass);
    }

    /**
     * 修改门店兴趣班
     * 
     * @param yunStoreInterestClass 门店兴趣班
     * @return 结果
     */
    @Override
    public int updateYunStoreInterestClass(YunStoreInterestClass yunStoreInterestClass)
    {
        yunStoreInterestClass.setUpdateTime(DateUtils.getNowDate());
        return yunStoreInterestClassMapper.updateYunStoreInterestClass(yunStoreInterestClass);
    }

    /**
     * 批量删除门店兴趣班
     * 
     * @param ids 需要删除的门店兴趣班ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreInterestClassByIds(Long[] ids)
    {
        return yunStoreInterestClassMapper.deleteYunStoreInterestClassByIds(ids);
    }

    /**
     * 删除门店兴趣班信息
     * 
     * @param id 门店兴趣班ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreInterestClassById(Long id)
    {
        return yunStoreInterestClassMapper.deleteYunStoreInterestClassById(id);
    }
}
