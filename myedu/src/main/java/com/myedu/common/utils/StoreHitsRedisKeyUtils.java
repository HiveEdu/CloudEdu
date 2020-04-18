package com.myedu.common.utils;

public class StoreHitsRedisKeyUtils {

    //保存门店点击量数据的key
    public static final String MAP_DYNAMIC_STORE_HITS = "MAP_DYNAMIC_STORE_HITS";


    /**
     * 拼接被点赞的用户id和点赞的人的id作为key。格式 222222::333333
     * @param storeId 被点赞的动态id
     * @param userId 点赞的人的id
     * @return
     */
    public static String getStoreHitsKey(String  storeId, String  userId){
        StringBuilder builder = new StringBuilder();
        builder.append(storeId);
        builder.append("::");
        builder.append(userId);
        return builder.toString();
    }
}
