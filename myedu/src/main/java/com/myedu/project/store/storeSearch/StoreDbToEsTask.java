package com.myedu.project.store.storeSearch;

import com.myedu.project.dynamic.service.IYunDyLikesService;
import com.myedu.project.store.domain.YunStoreType;
import com.myedu.project.store.domain.vo.YunStoreVo;
import com.myedu.project.store.service.IYunStoreService;
import com.myedu.project.store.service.IYunStoreTypeService;
import com.myedu.project.store.storeSearch.entityVo.StoreSearchVo;
import com.myedu.project.store.storeSearch.reponsitory.StoreSearchVoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StoreDbToEsTask {
    @Autowired
    IYunDyLikesService yunDyLikesService;
    @Autowired
    private IYunStoreService yunStoreService;
    @Autowired
    private IYunStoreTypeService yunStoreTypeService;
    @Autowired
    private StoreSearchVoRepository storeSearchVoRepository;
    //0 0 12 * * ?
    @Scheduled(cron="0 37 18 * * ?") //每天18点执行
    protected void executedyInternal(){
        YunStoreVo yunStore=new YunStoreVo();
        List<YunStoreVo> yunStores=yunStoreService.selectYunStoreList(yunStore);
        for (YunStoreVo yun:
                yunStores) {
//            storeSearchVoRepository.deleteById(yun.getId());
            Boolean exists= storeSearchVoRepository.existsById(yun.getId());
            if(exists){
                //增加门店到es查询数据库中
                StoreSearchVo storeSearchVo=new StoreSearchVo();
                storeSearchVo.setId(yun.getId());
                storeSearchVo.setName(yun.getName());
                storeSearchVo.setLon(yun.getMapX());
                storeSearchVo.setLat(yun.getMapY());
                GeoPoint location=new GeoPoint(yun.getMapY(),yun.getMapX());
                storeSearchVo.setLocation(location);
                storeSearchVo.setAddress(yun.getAddress());
                storeSearchVo.setArea(yun.getArea());
                storeSearchVo.setBeginExperienceTime(yun.getBeginExperienceTime());
                storeSearchVo.setEndExperienceTime(yun.getEndExperienceTime());
                storeSearchVo.setCity(yun.getCity());
                storeSearchVo.setCreateById(yun.getCreateById());
                storeSearchVo.setHealths(yun.getHealths());
                storeSearchVo.setPhotos(yun.getPhotos());
                storeSearchVo.setLogo(yun.getLogo());
                storeSearchVo.setStatus(yun.getStatus());
                storeSearchVo.setManager(yun.getManager());
                storeSearchVo.setProvince(yun.getProvince());
                storeSearchVo.setManagerPhone(yun.getManagerPhone());
                storeSearchVo.setVideo(yun.getVideo());
                storeSearchVo.setRejectResion(yun.getRejectResion());
                storeSearchVo.setVipLevelId(yun.getVipLevelId());
                storeSearchVo.setIntroduction(yun.getIntroduction());
                List<YunStoreType>  yunStoreTypes=yunStoreTypeService.selectYunStoreTypeByStoreId(yun.getId());
                List<Long> typeIds=new ArrayList<>();
                for (YunStoreType storeType:
                        yunStoreTypes) {
                    typeIds.add(storeType.getTypeId());
                }
                storeSearchVo.setTypeIds(typeIds);
                storeSearchVoRepository.save(storeSearchVo);

            }
        }

    }
}
