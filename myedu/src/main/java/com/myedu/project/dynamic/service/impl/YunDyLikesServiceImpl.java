package com.myedu.project.dynamic.service.impl;

import com.myedu.common.utils.DateUtils;
import com.myedu.common.utils.DyRedisKeyUtils;
import com.myedu.common.utils.RedisKeyUtils;
import com.myedu.framework.aspectj.lang.annotation.Log;
import com.myedu.framework.redis.RedisCache;
import com.myedu.project.dynamic.domain.DyLikedCountDTO;
import com.myedu.project.dynamic.domain.DyLikedStatusEnum;
import com.myedu.project.dynamic.domain.YunDyLikes;
import com.myedu.project.dynamic.domain.YunDynamic;
import com.myedu.project.dynamic.mapper.YunDyLikesMapper;
import com.myedu.project.dynamic.service.IYunDyLikesService;
import com.myedu.project.system.domain.SysUser;
import com.myedu.project.thumbsup.domain.LikedCountDTO;
import com.myedu.project.thumbsup.domain.UserLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 动态点赞Service业务层处理
 * 
 * @author 梁少鹏
 * @date 2020-01-01
 */
@Service
public class YunDyLikesServiceImpl implements IYunDyLikesService
{
    @Autowired
    private YunDyLikesMapper yunDyLikesMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private YunDynamicServiceImpl yunDynamicService;
    /**
     * 查询动态点赞
     * 
     * @param id 动态点赞ID
     * @return 动态点赞
     */
    @Override
    public YunDyLikes selectYunDyLikesById(Long id)
    {
        return yunDyLikesMapper.selectYunDyLikesById(id);
    }

    /**
     * 查询动态点赞列表
     * 
     * @param yunDyLikes 动态点赞
     * @return 动态点赞
     */
    @Override
    public List<YunDyLikes> selectYunDyLikesList(YunDyLikes yunDyLikes)
    {
        return yunDyLikesMapper.selectYunDyLikesList(yunDyLikes);
    }

    /**
     * 新增动态点赞
     * 
     * @param yunDyLikes 动态点赞
     * @return 结果
     */
    @Override
    public int insertYunDyLikes(YunDyLikes yunDyLikes)
    {
        yunDyLikes.setCreateTime(DateUtils.getNowDate());
        return yunDyLikesMapper.insertYunDyLikes(yunDyLikes);
    }

    /**
     * 修改动态点赞
     * 
     * @param yunDyLikes 动态点赞
     * @return 结果
     */
    @Override
    public int updateYunDyLikes(YunDyLikes yunDyLikes)
    {
        yunDyLikes.setUpdateTime(DateUtils.getNowDate());
        return yunDyLikesMapper.updateYunDyLikes(yunDyLikes);
    }

    /**
     * 批量删除动态点赞
     * 
     * @param ids 需要删除的动态点赞ID
     * @return 结果
     */
    @Override
    public int deleteYunDyLikesByIds(Long[] ids)
    {
        return yunDyLikesMapper.deleteYunDyLikesByIds(ids);
    }

    /**
     * 删除动态点赞信息
     * 
     * @param id 动态点赞ID
     * @return 结果
     */
    @Override
    public int deleteYunDyLikesById(Long id)
    {
        return yunDyLikesMapper.deleteYunDyLikesById(id);
    }



    @Override
    public void saveLiked2Redis(Long likedDynamicId, Long likedUserId) {
        String key = DyRedisKeyUtils.getLikedKey(String.valueOf(likedDynamicId), String.valueOf(likedUserId));
        redisTemplate.opsForHash().put(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED, key, DyLikedStatusEnum.LIKE.getCode());
    }

    @Override
    public void unlikeFromRedis(Long likedDynamicId, Long likedUserId) {
        String key = DyRedisKeyUtils.getLikedKey(String.valueOf(likedDynamicId), String.valueOf(likedUserId));
        redisTemplate.opsForHash().put(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED, key, DyLikedStatusEnum.UNLIKE.getCode());
    }

    @Override
    public void deleteLikedFromRedis(Long likedDynamicId, Long likedUserId) {
        String key = DyRedisKeyUtils.getLikedKey(String.valueOf(likedDynamicId), String.valueOf(likedUserId));
        redisTemplate.opsForHash().delete(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED, key);
    }

    @Override
    public void incrementLikedCount(Long likedDynamicId) {
        redisTemplate.opsForHash().increment(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED_COUNT, likedDynamicId, 1);
    }

    @Override
    public void decrementLikedCount(Long likedDynamicId) {
        redisTemplate.opsForHash().increment(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED_COUNT, likedDynamicId, -1);
    }

    @Override
    public List<YunDyLikes> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED, ScanOptions.NONE);
        List<YunDyLikes> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println(entry.getKey());
            String key = String.valueOf(entry.getKey());
            //分离出 likedDynamicId ，likedUserId
            String[] split = key.split("::");
            System.out.println("split.length"+split.length);
            if(split.length==2){
                Long likedDynamicId = Long.valueOf(split[0]);
                Long likedUserId = Long.valueOf(split[1]);
                Integer value = (Integer) entry.getValue();
                //组装成 YunDyLikes 对象
                YunDyLikes yunDyLikes = new YunDyLikes(likedDynamicId, likedUserId, String.valueOf(value));
                list.add(yunDyLikes);
                //存到 list 后从 Redis 中删除
                redisTemplate.opsForHash().delete(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED, key);
            }
        }
        return list;
    }
    @Override
    public List<YunDyLikes> getLikedDataFromRedisList() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED, ScanOptions.NONE);
        List<YunDyLikes> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println(entry.getKey());
            String key = String.valueOf(entry.getKey());
            //分离出 likedDynamicId ，likedUserId
            String[] split = key.split("::");
            System.out.println("split.length"+split.length);
            if(split.length==2){
                Long likedDynamicId = Long.valueOf(split[0]);
                Long likedUserId = Long.valueOf(split[1]);
                Integer value = (Integer) entry.getValue();
                //组装成 YunDyLikes 对象
                YunDyLikes yunDyLikes = new YunDyLikes(likedDynamicId, likedUserId, String.valueOf(value));
                list.add(yunDyLikes);
//                //存到 list 后从 Redis 中删除
//                redisTemplate.opsForHash().delete(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED, key);
            }
        }
        return list;
    }
    @Override
    public List<DyLikedCountDTO> getLikedCountFromRedis()  {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED_COUNT, ScanOptions.NONE);
        List<DyLikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 DyLikedCountDTO
            System.out.println("map.getKey()____"+map.getKey());
            String key = String.valueOf(map.getKey());
            DyLikedCountDTO dto = new DyLikedCountDTO(Long.valueOf(key),(Integer) map.getValue());
            list.add(dto);
            //从Redis中删除这条记录
            redisTemplate.opsForHash().delete(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED_COUNT, map.getKey());
        }
        return list;
    }

    @Override
    public List<DyLikedCountDTO> getLikedCountFromRedisList()  {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED_COUNT, ScanOptions.NONE);
        List<DyLikedCountDTO> list = new ArrayList<>();
        while (cursor.hasNext()){
            Map.Entry<Object, Object> map = cursor.next();
            //将点赞数量存储在 DyLikedCountDTO
            System.out.println("map.getKey()____"+map.getKey());
            String key = String.valueOf(map.getKey());
            DyLikedCountDTO dto = new DyLikedCountDTO(Long.valueOf(key),(Integer) map.getValue());
            list.add(dto);
//            //从Redis中删除这条记录
//            redisTemplate.opsForHash().delete(DyRedisKeyUtils.MAP_KEY_DYNAMIC_LIKED_COUNT, map.getKey());
        }
        return list;
    }
    public  YunDyLikes getByDyIdAndUserId(Long dyId,Long createById){
       return  yunDyLikesMapper.getByDyIdAndUserId(dyId,createById);
    }

    @Override
    public int deleteYunDyLikesByDyId(Long dyId) {
        return yunDyLikesMapper.deleteYunDyLikesByDyId(dyId);
    }

    @Override
    public void transLikedFromRedis2DB() {
        List<YunDyLikes> list = this.getLikedDataFromRedis();
        for (YunDyLikes like : list) {
            YunDyLikes ul = getByDyIdAndUserId(like.getDyId(), like.getCreateById());
            if (ul == null) {
                //没有记录，直接存入
                ul=new YunDyLikes();
                ul.setDyId(like.getDyId());
                ul.setCreateById(like.getCreateById());
                ul.setCreateTime(DateUtils.getNowDate());
                ul.setStatus(like.getStatus());
                yunDyLikesMapper.insertYunDyLikes(ul);
            } else {
                //有记录，需要更新
                ul.setStatus(like.getStatus());
                yunDyLikesMapper.updateYunDyLikes(ul);
            }
        }
    }

    @Override
    public void transLikedCountFromRedis2DB() {
        List<DyLikedCountDTO> list = this.getLikedCountFromRedis();
        for (DyLikedCountDTO dto : list) {
            YunDynamic yunDynamic = yunDynamicService.selectYunDynamicById(dto.getId());
            //点赞数量属于无关紧要的操作，出错无需抛异常
            if (yunDynamic != null) {
                if(yunDynamic.getLikes()==null){
                    yunDynamic.setLikes((long) 0);
                }
                Long likeNum = yunDynamic.getLikes() + dto.getCount();
                yunDynamic.setLikes(likeNum);
                //更新点赞数量
                yunDynamicService.updateYunDynamic(yunDynamic);
            }
        }
    }
}
