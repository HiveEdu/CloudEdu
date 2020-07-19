package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunStoreInterestClass;
import com.myedu.project.store.domain.vo.YunStoreInterestClassVo;

import java.util.List;

/**
 * 门店兴趣班Mapper接口
 * 
 * @author myedu
 * @date 2020-07-19
 */
public interface YunStoreInterestClassMapper 
{
    /**
     * 查询门店兴趣班
     * 
     * @param id 门店兴趣班ID
     * @return 门店兴趣班
     */
    public YunStoreInterestClassVo selectYunStoreInterestClassById(Long id);

    /**
     * 查询门店兴趣班列表
     * 
     * @param yunStoreInterestClass 门店兴趣班
     * @return 门店兴趣班集合
     */
    public List<YunStoreInterestClassVo> selectYunStoreInterestClassList(YunStoreInterestClass yunStoreInterestClass);

    /**
     * 新增门店兴趣班
     * 
     * @param yunStoreInterestClass 门店兴趣班
     * @return 结果
     */
    public int insertYunStoreInterestClass(YunStoreInterestClass yunStoreInterestClass);

    /**
     * 修改门店兴趣班
     * 
     * @param yunStoreInterestClass 门店兴趣班
     * @return 结果
     */
    public int updateYunStoreInterestClass(YunStoreInterestClass yunStoreInterestClass);

    /**
     * 删除门店兴趣班
     * 
     * @param id 门店兴趣班ID
     * @return 结果
     */
    public int deleteYunStoreInterestClassById(Long id);

    /**
     * 批量删除门店兴趣班
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStoreInterestClassByIds(Long[] ids);
}
