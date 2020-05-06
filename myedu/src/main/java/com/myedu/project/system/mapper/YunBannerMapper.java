package com.myedu.project.system.mapper;

import com.myedu.project.system.domain.YunBanner;
import java.util.List;

/**
 * 轮廓图设置Mapper接口
 * 
 * @author lsp
 * @date 2020-05-06
 */
public interface YunBannerMapper 
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
     * 删除轮廓图设置
     * 
     * @param id 轮廓图设置ID
     * @return 结果
     */
    public int deleteYunBannerById(Long id);

    /**
     * 批量删除轮廓图设置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunBannerByIds(Long[] ids);
}
