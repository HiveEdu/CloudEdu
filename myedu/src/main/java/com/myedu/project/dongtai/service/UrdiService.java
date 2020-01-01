package com.myedu.project.dongtai.service;

import com.myedu.project.dongtai.domain.CityImg;
import com.myedu.project.parents.domain.vo.YunStudentVo;

public interface UrdiService {

    public CityImg findCityImgByUrl(String  url);
    public int DeleteCityImg(Long id);
}
