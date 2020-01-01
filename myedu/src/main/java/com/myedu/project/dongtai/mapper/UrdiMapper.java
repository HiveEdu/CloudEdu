package com.myedu.project.dongtai.mapper;

import com.myedu.project.dongtai.domain.CityImg;


public interface UrdiMapper {
    public CityImg findCityImgByUrl(String url);

    public int DeleteCityImg(Long id);
}
