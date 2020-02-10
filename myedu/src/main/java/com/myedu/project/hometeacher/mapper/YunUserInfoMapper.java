package com.myedu.project.hometeacher.mapper;

import com.myedu.project.hometeacher.domain.YunUserInfo;
import java.util.List;

/**
 * 家教老师表Mapper接口
 * 
 * @author 梁龙飞
 * @date 2020-02-10
 */
public interface YunUserInfoMapper 
{
    /**
     * 查询家教老师表
     * 
     * @param id 家教老师表ID
     * @return 家教老师表
     */
    public YunUserInfo selectYunUserInfoById(Long id);

    /**
     * 查询家教老师表列表
     * 
     * @param yunUserInfo 家教老师表
     * @return 家教老师表集合
     */
    public List<YunUserInfo> selectYunUserInfoList(YunUserInfo yunUserInfo);

    /**
     * 新增家教老师表
     * 
     * @param yunUserInfo 家教老师表
     * @return 结果
     */
    public int insertYunUserInfo(YunUserInfo yunUserInfo);

    /**
     * 修改家教老师表
     * 
     * @param yunUserInfo 家教老师表
     * @return 结果
     */
    public int updateYunUserInfo(YunUserInfo yunUserInfo);

    /**
     * 删除家教老师表
     * 
     * @param id 家教老师表ID
     * @return 结果
     */
    public int deleteYunUserInfoById(Long id);

    /**
     * 批量删除家教老师表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunUserInfoByIds(Long[] ids);
}
