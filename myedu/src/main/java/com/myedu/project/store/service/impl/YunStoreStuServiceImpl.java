package com.myedu.project.store.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import com.myedu.project.store.domain.vo.YunStoreStuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreStuMapper;
import com.myedu.project.store.domain.YunStoreStu;
import com.myedu.project.store.service.IYunStoreStuService;

/**
 * 门店学生管理Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-03-03
 */
@Service
public class YunStoreStuServiceImpl implements IYunStoreStuService 
{
    @Autowired
    private YunStoreStuMapper yunStoreStuMapper;

    /**
     * 查询门店学生管理
     * 
     * @param id 门店学生管理ID
     * @return 门店学生管理
     */
    @Override
    public YunStoreStuVo selectYunStoreStuById(Long id)
    {
        return yunStoreStuMapper.selectYunStoreStuById(id);
    }

    /**
     * 查询门店学生管理列表
     * 
     * @param yunStoreStu 门店学生管理
     * @return 门店学生管理
     */
    @Override
    public List<YunStoreStuVo> selectYunStoreStuList(YunStoreStuVo yunStoreStu)
    {
        return yunStoreStuMapper.selectYunStoreStuList(yunStoreStu);
    }

    /**
     * 新增门店学生管理
     * 
     * @param yunStoreStu 门店学生管理
     * @return 结果
     */
    @Override
    public int insertYunStoreStu(YunStoreStu yunStoreStu)
    {
        yunStoreStu.setCreateTime(DateUtils.getNowDate());
        return yunStoreStuMapper.insertYunStoreStu(yunStoreStu);
    }

    /**
     * 修改门店学生管理
     * 
     * @param yunStoreStu 门店学生管理
     * @return 结果
     */
    @Override
    public int updateYunStoreStu(YunStoreStu yunStoreStu)
    {
        yunStoreStu.setUpdateTime(DateUtils.getNowDate());
        return yunStoreStuMapper.updateYunStoreStu(yunStoreStu);
    }

    /**
     * 批量删除门店学生管理
     * 
     * @param ids 需要删除的门店学生管理ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreStuByIds(Long[] ids)
    {
        return yunStoreStuMapper.deleteYunStoreStuByIds(ids);
    }

    /**
     * 删除门店学生管理信息
     * 
     * @param id 门店学生管理ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreStuById(Long id)
    {
        return yunStoreStuMapper.deleteYunStoreStuById(id);
    }
}
