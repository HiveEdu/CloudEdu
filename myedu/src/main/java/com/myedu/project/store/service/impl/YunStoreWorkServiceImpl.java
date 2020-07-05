package com.myedu.project.store.service.impl;

import com.myedu.common.utils.DateUtils;
import com.myedu.project.store.domain.YunStoreWork;
import com.myedu.project.store.domain.vo.YunStoreWorkVo;
import com.myedu.project.store.mapper.YunStoreWorkMapper;
import com.myedu.project.store.service.IYunStoreWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 门店作业管理Service业务层处理
 * 
 * @author lianglongfei
 * @date 2020-07-05
 */
@Service
public class YunStoreWorkServiceImpl implements IYunStoreWorkService 
{
    @Autowired
    private YunStoreWorkMapper yunStoreWorkMapper;

    /**
     * 查询门店作业管理
     * 
     * @param id 门店作业管理ID
     * @return 门店作业管理
     */
    @Override
    public YunStoreWork selectYunStoreWorkById(Long id)
    {
        return yunStoreWorkMapper.selectYunStoreWorkById(id);
    }

    /**
     * 查询门店作业管理列表
     * 
     * @param yunStoreWork 门店作业管理
     * @return 门店作业管理
     */
    @Override
    public List<YunStoreWorkVo> selectYunStoreWorkList(YunStoreWork yunStoreWork)
    {
        return yunStoreWorkMapper.selectYunStoreWorkList(yunStoreWork);
    }

    /**
     * 新增门店作业管理
     * 
     * @param yunStoreWork 门店作业管理
     * @return 结果
     */
    @Override
    public int insertYunStoreWork(YunStoreWork yunStoreWork)
    {
        yunStoreWork.setCreateTime(DateUtils.getNowDate());
        return yunStoreWorkMapper.insertYunStoreWork(yunStoreWork);
    }

    /**
     * 修改门店作业管理
     * 
     * @param yunStoreWork 门店作业管理
     * @return 结果
     */
    @Override
    public int updateYunStoreWork(YunStoreWork yunStoreWork)
    {
        yunStoreWork.setUpdateTime(DateUtils.getNowDate());
        return yunStoreWorkMapper.updateYunStoreWork(yunStoreWork);
    }

    /**
     * 批量删除门店作业管理
     * 
     * @param ids 需要删除的门店作业管理ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreWorkByIds(Long[] ids)
    {
        return yunStoreWorkMapper.deleteYunStoreWorkByIds(ids);
    }

    /**
     * 删除门店作业管理信息
     * 
     * @param id 门店作业管理ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreWorkById(Long id)
    {
        return yunStoreWorkMapper.deleteYunStoreWorkById(id);
    }
}
