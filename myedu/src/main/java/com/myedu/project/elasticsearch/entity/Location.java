package com.myedu.project.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

/**
 * @Describe:
 * @Auther: zhenlong.yang
 * @Date: 2018/12/7 13:44
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "location")
public class Location {
    @Id
    private String id;
    @GeoPointField
    private GeoPoint location;//位置坐标 lon经度 lat纬度
    private String address;//地址
    private String distanceMeters;//距离多少米
    private String storeid;//门店id
}
