package com.myedu.project.dongtai.service.Impl;

import com.myedu.project.dongtai.domain.CityImg;
import com.myedu.project.dongtai.mapper.UrdiMapper;
import com.myedu.project.dongtai.service.UrdiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrdiServiceImpl implements UrdiService {


    @Autowired
    private UrdiMapper urdiMapper;
    @Override
    public CityImg findCityImgByUrl(String url) {
        return urdiMapper.findCityImgByUrl(url);
    }

    @Override
    public Long DeleteCityImg(Long id) {
        return urdiMapper.DeleteCityImg(id);
    }
}
