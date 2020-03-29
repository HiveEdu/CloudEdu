package com.myedu.project.store.service;

import com.myedu.project.order.domain.vo.YunOrderVo;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.domain.vo.YunStoreVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 门店Service接口
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
public interface IYunStoreService 
{
    /**
     * 查询门店
     * 
     * @param id 门店ID
     * @return 门店
     */
    public YunStoreVo selectYunStoreById(Long id);

    /**
     * 查询门店列表
     * 
     * @param yunStore 门店
     * @return 门店集合
     */
    public List<YunStoreVo> selectYunStoreList(YunStoreVo yunStore);

    /**
     * 新增门店
     * 
     * @param yunStore 门店
     * @return 结果
     */
    public int insertYunStore(YunStore yunStore);

    /**
     * 修改门店
     * 
     * @param yunStore 门店
     * @return 结果
     */
    public int updateYunStore(YunStore yunStore);

    /**
     * 批量删除门店
     * 
     * @param ids 需要删除的门店ID
     * @return 结果
     */
    public int deleteYunStoreByIds(Long[] ids);

    /**
     * 删除门店信息
     * 
     * @param id 门店ID
     * @return 结果
     */
    public int deleteYunStoreById(Long id);

//    /**
//     * 门店充值
//     *
//     * @param yunStore 订单
//     * @return 结果
//     */
//    public String toPayAsPc(YunStore yunStore) throws Exception;
//
//    String synchronous(HttpServletRequest request);
//    /**
//     * 付款异步通知调用地址
//     * @param request 新增参数
//     * @return 新增返回值
//     */
//    void notify(HttpServletRequest request, HttpServletResponse response);
}
