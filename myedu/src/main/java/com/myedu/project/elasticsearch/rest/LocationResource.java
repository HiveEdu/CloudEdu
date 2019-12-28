package com.myedu.project.elasticsearch.rest;

import com.myedu.project.elasticsearch.dao.LocationRepository;
import com.myedu.project.elasticsearch.entity.Location;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Describe:
 * @Auther: zhenlong.yang
 * @Date: 2018/12/7 15:53
 */
@RestController
@RequestMapping("/location")
public class LocationResource {
    @Autowired
    private LocationRepository locationRepository;

    @PostMapping("")
    public Location save(@RequestBody Location location){
        return locationRepository.save(location);
    }

    /**
     * 搜索附近
     * @param lon 当前位置 经度
     * @param lat 当前位置 纬度
     * @param distance 搜索多少范围
     * @param pageable 分页参数
     * @return
     */
    @GetMapping("/searchNear")
    public List<Location> searchNear(double lon, double lat, String distance, @PageableDefault Pageable pageable){
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        //搜索字段为 location
        GeoDistanceQueryBuilder geoBuilder = new GeoDistanceQueryBuilder("location");
        geoBuilder.point(lat, lon);//指定从哪个位置搜索
        geoBuilder.distance(distance, DistanceUnit.KILOMETERS);//指定搜索多少km
        qb.filter(geoBuilder);

        //可添加其他查询条件
        //qb.must(QueryBuilders.matchQuery("address", address));
        Page<Location> page = locationRepository.search(qb, pageable);
        List<Location> list = page.getContent();
        list.forEach(l -> {
            double calculate = GeoDistance.ARC.calculate(l.getLocation().getLat(), l.getLocation().getLon(), lat, lon, DistanceUnit.METERS);
            l.setDistanceMeters("距离" + (int)calculate + "m");
            });
        return list;
    }

    @GetMapping("/searchNearWithOrder")
    public List<Location> searchNearWithOrder(double lon, double lat, String distance, @PageableDefault Pageable pageable){

        //搜索字段为 location
        GeoDistanceQueryBuilder geoBuilder = new GeoDistanceQueryBuilder("location");
        geoBuilder.point(lat, lon);//指定从哪个位置搜索
        geoBuilder.distance(distance, DistanceUnit.KILOMETERS);//指定搜索多少km

        //距离排序
        GeoDistanceSortBuilder sortBuilder = new GeoDistanceSortBuilder("location", lat, lon);
        sortBuilder.order(SortOrder.ASC);//升序
        sortBuilder.unit(DistanceUnit.METERS);

        //构造查询器
        NativeSearchQueryBuilder qb = new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withFilter(geoBuilder)
                .withSort(sortBuilder);

        //可添加其他查询条件
        //qb.must(QueryBuilders.matchQuery("address", address));
        Page<Location> page = locationRepository.search(qb.build());
        List<Location> list = page.getContent();
        list.forEach(l -> {
            double calculate = GeoDistance.PLANE.calculate(l.getLocation().getLat(), l.getLocation().getLon(), lat, lon, DistanceUnit.METERS);
            l.setDistanceMeters("距离" + (int)calculate + "m");
            });
        return list;
    }


}
