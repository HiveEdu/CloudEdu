package com.myedu.project.store.service;

import com.myedu.project.store.domain.YunStoreStu;
import com.myedu.project.store.domain.vo.YunStoreStuVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 门店学生管理Service接口
 * 
 * @author 梁少鹏
 * @date 2020-03-03
 */
@CacheConfig(cacheNames = "yunStoreStus")
public interface IYunStoreStuService 
{
    /**
     * 查询门店学生管理
     * 
     * @param id 门店学生管理ID
     * @return 门店学生管理
     */
    @Cacheable
    public YunStoreStuVo selectYunStoreStuById(Long id);

    /**
     * 查询门店学生管理列表
     * 
     * @param yunStoreStu 门店学生管理
     * @return 门店学生管理集合
     */
    @Cacheable
    public List<YunStoreStuVo> selectYunStoreStuList(YunStoreStuVo yunStoreStu);

    /**
     * 新增门店学生管理
     * 
     * @param yunStoreStu 门店学生管理
     * @return 结果
     */
    public int insertYunStoreStu(YunStoreStu yunStoreStu);

    /**
     * 修改门店学生管理
     * 
     * @param yunStoreStu 门店学生管理
     * @return 结果
     */
    public int updateYunStoreStu(YunStoreStu yunStoreStu);

    /**
     * 批量删除门店学生管理
     * 
     * @param ids 需要删除的门店学生管理ID
     * @return 结果
     */
    @CacheEvict
    public int deleteYunStoreStuByIds(Long[] ids);

    /**
     * 删除门店学生管理信息
     * 
     * @param id 门店学生管理ID
     * @return 结果
     */
    public int deleteYunStoreStuById(Long id);
}
