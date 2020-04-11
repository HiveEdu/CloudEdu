package com.myedu.project.store.storeSearch.entityVo;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/4/11
 * Time: 15:51
 * Description:
 */
@Document(indexName = "store", type = "t_store", shards = 1, replicas = 0, refreshInterval = "-1")
public class StoreSearchVo {
    /**
     * 主键
     */
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 经度
     */
    private Double lon;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 到我的距离
     */
    private String distanceMeters;

    @GeoPointField
    private GeoPoint location;

    public StoreSearchVo(){

    }

    public StoreSearchVo(Long id, String name, Double lon, Double lat, GeoPoint location) {
        this.id = id;
        this.name = name;
        this.lon = lon;
        this.lat=lat;
        this.location=location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public String getDistanceMeters() {
        return distanceMeters;
    }

    public void setDistanceMeters(String distanceMeters) {
        this.distanceMeters = distanceMeters;
    }

    @Override
    public String toString() {
        return "StoreSearchVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lon=" + lon +
                ", lat=" + lat +
                ", location=" + location +
                ", distanceMeters=" + distanceMeters +
                '}';
    }
}
