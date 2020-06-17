package com.myedu.project.hometeacher.TeacherSearch;

import com.myedu.project.hometeacher.TeacherSearch.entityVo.TeacherSearchVo;
import com.myedu.project.hometeacher.TeacherSearch.reponsitory.TeacherSearchVoRepository;
import com.myedu.project.hometeacher.domain.vo.YunUserInfoVo;
import com.myedu.project.hometeacher.service.IYunUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("teacherDbToEsTask")
public class TeacherDbToEsTask {
    @Autowired
    private IYunUserInfoService yunUserInfoService;

    @Autowired
    private TeacherSearchVoRepository teacherSearchVoRepository;
    //0 0 12 * * ?
    //@Scheduled(cron="0 0 18 * * ?") //每天18点执行
    public void executedyInternal(){
        YunUserInfoVo yunuser=new YunUserInfoVo();
        List<YunUserInfoVo> yunUsers=yunUserInfoService.selectYunUserInfoList(yunuser);
        for (YunUserInfoVo yun:
                yunUsers) {
//            storeSearchVoRepository.deleteById(yun.getId());
            Boolean exists= teacherSearchVoRepository.existsById(yun.getId());
            if(!exists){
                //增加门店到es查询数据库中
                TeacherSearchVo teacherSearchVo=new TeacherSearchVo();
                teacherSearchVo.setId(yun.getId());
                teacherSearchVo.setLon(yun.getMapX());
                teacherSearchVo.setLat(yun.getMapY());
                GeoPoint location=new GeoPoint(yun.getMapY(),yun.getMapX());
                teacherSearchVo.setLocation(location);
                teacherSearchVo.setAddress(yun.getAddress());
                teacherSearchVo.setArea(yun.getArea());
                teacherSearchVo.setCity(yun.getCity());
                teacherSearchVo.setPhotos(yun.getPhotos());
                teacherSearchVo.setStatus(yun.getStatus());
                teacherSearchVo.setProvince(yun.getProvince());
                teacherSearchVoRepository.save(teacherSearchVo);

            }
        }

    }
}
