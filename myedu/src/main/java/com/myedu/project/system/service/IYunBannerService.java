package com.myedu.project.system.service;

import com.myedu.project.system.domain.YunBanner;
import java.util.List;

/**
 * 轮廓图设置Service接口
 * 
 * @author lsp
 * @date 2020-05-06
 */
public interface IYunBannerService 
{
    /**
     * 查询轮廓图设置
     * 
     * @param id 轮廓图设置ID
     * @return 轮廓图设置
     */
    public YunBanner selectYunBannerById(Long id);

    /**
     * 查询轮廓图设置列表
     * 
     * @param yunBanner 轮廓图设置
     * @return 轮廓图设置集合
     */
    public List<YunBanner> selectYunBannerList(YunBanner yunBanner);

    /**
     * 新增轮廓图设置
     * 
     * @param yunBanner 轮廓图设置
     * @return 结果
     */
    public int insertYunBanner(YunBanner yunBanner);

    /**
     * 修改轮廓图设置
     * 
     * @param yunBanner 轮廓图设置
     * @return 结果
     */
    public int updateYunBanner(YunBanner yunBanner);

    /**
     * 批量删除轮廓图设置
     * 
     * @param ids 需要删除的轮廓图设置ID
     * @return 结果
     */
    public int deleteYunBannerByIds(Long[] ids);

    /**
     * 删除轮廓图设置信息
     * 
     * @param id 轮廓图设置ID
     * @return 结果
     */
    public int deleteYunBannerById(Long id);
}
