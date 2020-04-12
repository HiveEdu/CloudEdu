package com.myedu.project.store.storeSearch;

import com.myedu.project.dynamic.service.IYunDyLikesService;
import com.myedu.project.store.domain.YunStore;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.service.IYunStoreService;
import com.myedu.project.store.storeSearch.entityVo.StoreSearchVo;
import com.myedu.project.store.storeSearch.reponsitory.StoreSearchVoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StoreDbToEsTask {
    @Autowired
    IYunDyLikesService yunDyLikesService;
    @Autowired
    private IYunStoreService yunStoreService;
    @Autowired
    private StoreSearchVoRepository storeSearchVoRepository;
    @Scheduled(cron="0 0 12 * * ?") //每天12点执行
    protected void executedyInternal(){
        YunStoreVo yunStore=new YunStoreVo();
        List<YunStoreVo> yunStores=yunStoreService.selectYunStoreList(yunStore);
        for (YunStoreVo yun:
                yunStores) {
            Boolean exists= storeSearchVoRepository.existsById(yun.getId());
            if(!exists){
                //增加门店到es查询数据库中
                StoreSearchVo storeSearchVo=new StoreSearchVo();
                storeSearchVo.setId(yun.getId());
                storeSearchVo.setName(yun.getName());
                storeSearchVo.setLon(yun.getMapX());
                storeSearchVo.setLat(yun.getMapY());
                GeoPoint location=new GeoPoint(yun.getMapY(),yun.getMapX());
                storeSearchVo.setLocation(location);
                storeSearchVoRepository.save(storeSearchVo);
            }
        }

    }
}
