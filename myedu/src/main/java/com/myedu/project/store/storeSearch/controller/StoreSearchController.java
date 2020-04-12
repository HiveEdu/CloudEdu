package com.myedu.project.store.storeSearch.controller;

import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.store.storeSearch.entityVo.StoreSearchVo;
import com.myedu.project.store.storeSearch.reponsitory.StoreSearchVoRepository;
import org.elasticsearch.common.geo.GeoDistance;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.GeoDistanceQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/4/11
 * Time: 16:10
 * Description:
 */
@RestController
@RequestMapping("/storeSearch")
public class StoreSearchController extends BaseController {
    @Autowired
    private StoreSearchVoRepository storeSearchVoRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @GetMapping(value = "/findAll")
    public TableDataInfo listProducts(double lon, double lat, String distance)
    {
        startPage();
        //设定搜索半径
        GeoDistanceQueryBuilder queryBuilder = QueryBuilders.geoDistanceQuery("location")
                .point(lat, lon)
                .distance(distance, DistanceUnit.METERS.KILOMETERS)
                .geoDistance(GeoDistance.PLANE);
        //按距离排序
        GeoDistanceSortBuilder sortBuilder = SortBuilders.geoDistanceSort("location", lat, lon);
        sortBuilder.order(SortOrder.ASC);
        sortBuilder.point(lat, lon);
        //构建检索
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .from(0)
                .size(20)
                .query(queryBuilder)
                .sort(sortBuilder);
        //构造查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder =
                new NativeSearchQueryBuilder()
                        .withFilter(queryBuilder)
                        .withSort(sortBuilder);
        Page<StoreSearchVo> page =
                elasticsearchTemplate.queryForPage(
                        nativeSearchQueryBuilder.build(), StoreSearchVo.class);
        List<StoreSearchVo> storeSearchVos= page.getContent();
        storeSearchVos.forEach(storeSearchVo -> {
            double calculate = GeoDistance.ARC.calculate(storeSearchVo.getLocation().getLat(), storeSearchVo.getLocation().getLon(), lat, lon, DistanceUnit.METERS);
            storeSearchVo.setDistanceMeters(String.valueOf(calculate));
            System.out.println("距离" + (int)calculate + "m");
        });
        return getDataTable(storeSearchVos);


    }

}
