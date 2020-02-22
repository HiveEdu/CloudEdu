package com.myedu.project.dynamic.DyTask;

import com.myedu.project.dynamic.service.IYunDyLikesService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DyLikeTask {
    @Autowired
    IYunDyLikesService yunDyLikesService;

    @Scheduled(cron="*/5 * * * * ?") //每隔5秒执行一次
    protected void executedyInternal(){
        System.out.println("将动态数据存入到数据库中"+new Date());
        //将 Redis 里的点赞信息同步到数据库里
        yunDyLikesService.transLikedFromRedis2DB();
        yunDyLikesService.transLikedCountFromRedis2DB();
    }
}
