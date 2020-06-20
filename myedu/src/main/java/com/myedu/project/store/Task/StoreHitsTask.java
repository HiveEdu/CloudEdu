package com.myedu.project.store.Task;

import com.myedu.project.store.service.IYunStoreHitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("StoreHitsTask")
public class StoreHitsTask {
    @Autowired
    IYunStoreHitsService yunStoreHitsService;
    //0 0 12 * * ?
//    @Scheduled(cron="0 0 12 * * ?") //每天12点执行
     public void executedyInternal(){
        System.out.println("将动态数据存入到数据库中"+new Date());
        //将 Redis 里的门店点击数据同步到数据库里
        yunStoreHitsService.transHitsCountFromRedis2DB();
    }
}
