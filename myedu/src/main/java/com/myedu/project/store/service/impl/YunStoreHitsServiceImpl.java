package com.myedu.project.store.service.impl;

import java.math.BigInteger;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.DyRedisKeyUtils;
import com.myedu.common.utils.StoreHitsRedisKeyUtils;
import com.myedu.framework.redis.RedisCache;
import com.myedu.project.dynamic.domain.DyLikedCountDTO;
import com.myedu.project.dynamic.domain.DyLikedStatusEnum;
import com.myedu.project.dynamic.domain.YunDyLikes;
import com.myedu.project.store.domain.StoreHitsCountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;
import com.myedu.project.store.mapper.YunStoreHitsMapper;
import com.myedu.project.store.domain.YunStoreHits;
import com.myedu.project.store.service.IYunStoreHitsService;

/**
 * 门店日访问量统计Service业务层处理
 * 
 * @author LSP
 * @date 2020-04-18
 */
@Service
public class YunStoreHitsServiceImpl implements IYunStoreHitsService 
{
    @Autowired
    private YunStoreHitsMapper yunStoreHitsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisCache redisCache;
    /**
     * 查询门店日访问量统计
     * 
     * @param id 门店日访问量统计ID
     * @return 门店日访问量统计
     */
    @Override
    public YunStoreHits selectYunStoreHitsById(Long id)
    {
        return yunStoreHitsMapper.selectYunStoreHitsById(id);
    }

    /**
     * 查询门店日访问量统计列表
     * 
     * @param yunStoreHits 门店日访问量统计
     * @return 门店日访问量统计
     */
    @Override
    public List<YunStoreHits> selectYunStoreHitsList(YunStoreHits yunStoreHits)
    {
        return yunStoreHitsMapper.selectYunStoreHitsList(yunStoreHits);
    }

    /**
     * 新增门店日访问量统计
     * 
     * @param yunStoreHits 门店日访问量统计
     * @return 结果
     */
    @Override
    public int insertYunStoreHits(YunStoreHits yunStoreHits)
    {
        return yunStoreHitsMapper.insertYunStoreHits(yunStoreHits);
    }

    /**
     * 修改门店日访问量统计
     * 
     * @param yunStoreHits 门店日访问量统计
     * @return 结果
     */
    @Override
    public int updateYunStoreHits(YunStoreHits yunStoreHits)
    {
        return yunStoreHitsMapper.updateYunStoreHits(yunStoreHits);
    }

    /**
     * 批量删除门店日访问量统计
     * 
     * @param ids 需要删除的门店日访问量统计ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreHitsByIds(Long[] ids)
    {
        return yunStoreHitsMapper.deleteYunStoreHitsByIds(ids);
    }

    /**
     * 删除门店日访问量统计信息
     * 
     * @param id 门店日访问量统计ID
     * @return 结果
     */
    @Override
    public int deleteYunStoreHitsById(Long id)
    {
        return yunStoreHitsMapper.deleteYunStoreHitsById(id);
    }



    @Override
    public void savehitsRedis(Long storeId, Long userId) {
        redisTemplate.opsForHash().increment(StoreHitsRedisKeyUtils.MAP_DYNAMIC_STORE_HITS, storeId, 1);
    }

    @Override
    public void transHitsCountFromRedis2DB() {
        //从redis获取门店点击列表
        List<StoreHitsCountDTO> list = getStoreHitsCountFromRedis();
        for (StoreHitsCountDTO st :
                list) {
            YunStoreHits hits=new YunStoreHits();
            hits.setStoreId(st.getId());
            hits.setHits(Long.valueOf(st.getCount()));
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date date=new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            date = calendar.getTime();
            hits.setHitTime(sdf.format(date));
            if(yunStoreHitsMapper.selectYunStoreHitsList(hits).size()>0){
                YunStoreHits hits1= yunStoreHitsMapper.selectYunStoreHitsList(hits).get(0);
                hits1.setHits(hits1.getHits()+1);
                yunStoreHitsMapper.updateYunStoreHits(hits1);
            }else{
                yunStoreHitsMapper.insertYunStoreHits(hits);
            }
        }

    }



    public List<StoreHitsCountDTO> getStoreHitsCountFromRedis()  {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(StoreHitsRedisKeyUtils.MAP_DYNAMIC_STORE_HITS, ScanOptions.NONE);
        List<StoreHitsCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点击数量数量存储在 StoreHitsCountDTO
            System.out.println("map.getKey()____"+map.getKey());
            String key = String.valueOf(map.getKey());
            StoreHitsCountDTO dto = new StoreHitsCountDTO(Long.valueOf(key),(Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisTemplate.opsForHash().delete(StoreHitsRedisKeyUtils.MAP_DYNAMIC_STORE_HITS, map.getKey());
        }
        return list;
    }

    public int selectYunStoreHitsCountAll(Long storeId){
        return yunStoreHitsMapper.selectYunStoreHitsCountAll(storeId);
    };
}
