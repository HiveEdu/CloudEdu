package com.myedu.project.system.service.impl;

import java.util.List;
import com.myedu.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myedu.project.system.mapper.YunBannerMapper;
import com.myedu.project.system.domain.YunBanner;
import com.myedu.project.system.service.IYunBannerService;

/**
 * 轮廓图设置Service业务层处理
 * 
 * @author lsp
 * @date 2020-05-06
 */
@Service
public class YunBannerServiceImpl implements IYunBannerService 
{
    @Autowired
    private YunBannerMapper yunBannerMapper;

    /**
     * 查询轮廓图设置
     * 
     * @param id 轮廓图设置ID
     * @return 轮廓图设置
     */
    @Override
    public YunBanner selectYunBannerById(Long id)
    {
        return yunBannerMapper.selectYunBannerById(id);
    }

    /**
     * 查询轮廓图设置列表
     * 
     * @param yunBanner 轮廓图设置
     * @return 轮廓图设置
     */
    @Override
    public List<YunBanner> selectYunBannerList(YunBanner yunBanner)
    {
        return yunBannerMapper.selectYunBannerList(yunBanner);
    }

    /**
     * 新增轮廓图设置
     * 
     * @param yunBanner 轮廓图设置
     * @return 结果
     */
    @Override
    public int insertYunBanner(YunBanner yunBanner)
    {
        yunBanner.setCreateTime(DateUtils.getNowDate());
        return yunBannerMapper.insertYunBanner(yunBanner);
    }

    /**
     * 修改轮廓图设置
     * 
     * @param yunBanner 轮廓图设置
     * @return 结果
     */
    @Override
    public int updateYunBanner(YunBanner yunBanner)
    {
        yunBanner.setUpdateTime(DateUtils.getNowDate());
        return yunBannerMapper.updateYunBanner(yunBanner);
    }

    /**
     * 批量删除轮廓图设置
     * 
     * @param ids 需要删除的轮廓图设置ID
     * @return 结果
     */
    @Override
    public int deleteYunBannerByIds(Long[] ids)
    {
        return yunBannerMapper.deleteYunBannerByIds(ids);
    }

    /**
     * 删除轮廓图设置信息
     * 
     * @param id 轮廓图设置ID
     * @return 结果
     */
    @Override
    public int deleteYunBannerById(Long id)
    {
        return yunBannerMapper.deleteYunBannerById(id);
    }
}
