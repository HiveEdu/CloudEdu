package com.myedu.project.store.mapper;

import com.myedu.project.store.domain.YunStoreLabel;
import com.myedu.project.store.domain.YunStoreType;

import java.util.List;

public interface YunStoreLabelMapper {

    /**
     * 查询门店标签关联
     *
     * @param storeId 门店标签关联ID
     * @return 门店标签关联
     */
    public YunStoreLabel selectYunStoreLabelById(Long storeId);

    /**
     * 查询门店标签关联列表
     *
     * @param yunStoreLabel 门店标签关联
     * @return 门店标签关联集合
     */
    public List<YunStoreLabel> selectYunStoreLabelList(YunStoreLabel yunStoreLabel);

    /**
     * 新增门店标签关联
     *
     * @param yunStoreLabel 门店标签关联
     * @return 结果
     */
    public int insertYunStoreLabel(YunStoreLabel yunStoreLabel);

    /**
     * 修改门店标签关联
     *
     * @param yunStoreLabel 门店标签关联
     * @return 结果
     */
    public int updateYunStoreLabel(YunStoreLabel yunStoreLabel);

    /**
     * 删除门店标签关联
     *
     * @param storeId 门店标签关联ID
     * @return 结果
     */
    public int deleteYunStoreLabelById(Long storeId);

    /**
     * 批量删除门店标签关联
     *
     * @param storeIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteYunStoreLabelByIds(Long[] storeIds);


    /**
     * 批量新增标签关联
     *
     * @param yunStoreLabels 门店标签列表
     * @return 结果
     */
    public int batchStoreType(List<YunStoreLabel> yunStoreLabels);

    /**
     * 通过门店ID删除门店与门店标签关联
     *
     * @param storeId 门店Id
     * @return 结果
     */
    public int deleteStoreLabelByStoreId(Long storeId);
}
