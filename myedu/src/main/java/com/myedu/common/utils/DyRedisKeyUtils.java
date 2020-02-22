package com.myedu.common.utils;

public class DyRedisKeyUtils {

    //保存动态点赞数据的key
    public static final String MAP_KEY_DYNAMIC_LIKED = "MAP_DYNAMIC_LIKED";
    //保存动态被点赞数量的key
    public static final String MAP_KEY_DYNAMIC_LIKED_COUNT = "MAP_DYNAMIC_LIKED_COUNT";

    /**
     * 拼接被点赞的用户id和点赞的人的id作为key。格式 222222::333333
     * @param likedDynamicId 被点赞的动态id
     * @param likedUserId 点赞的人的id
     * @return
     */
    public static String getLikedKey(String  likedDynamicId, String  likedUserId){
        StringBuilder builder = new StringBuilder();
        builder.append(likedDynamicId);
        builder.append("::");
        builder.append(likedUserId);
        return builder.toString();
    }
}
