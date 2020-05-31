package com.myedu.app.parents.controller;

import com.myedu.common.utils.ServletUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.store.storeSearch.entityVo.StoreSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/4/11
 * Time: 16:10
 * Description:
 */
@Api("附近的门店")
@RestController
@RequestMapping("/app/storeSearch")
public class APPStoreSearchController extends BaseController {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private TokenService tokenService;


    @AutoIdempotent
    @ApiOperation("附近的门店列表")
    @GetMapping(value = "/findAll")
    public TableDataInfo listProducts(double lon, double lat, String distance,StoreSearchVo storeSearchVo)
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser!=null) {
            startPage();
            //设定搜索半径
            GeoDistanceQueryBuilder queryBuilder = QueryBuilders.geoDistanceQuery("location")
                    .point(lat, lon)
                    .distance(distance, DistanceUnit.KILOMETERS)
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
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("status", 3));
            Page<StoreSearchVo> page =
                    elasticsearchTemplate.queryForPage(
                            nativeSearchQueryBuilder.build(), StoreSearchVo.class);
            List<StoreSearchVo> storeSearchVosNew=new ArrayList<StoreSearchVo>();
            List<StoreSearchVo> storeSearchVos= page.getContent();
            storeSearchVos.forEach(storeSearchVo1 -> {
                double calculate = GeoDistance.ARC.calculate(storeSearchVo1.getLocation().getLat(), storeSearchVo1.getLocation().getLon(), lat, lon, DistanceUnit.KILOMETERS);
                storeSearchVo1.setDistanceMeters(String.valueOf(calculate));
                List<Long> typeIds=storeSearchVo1.getTypeIds();
                if(storeSearchVo.getTypeIds()!=null&&storeSearchVo.getTypeIds().size()>0){
                    Long typeId= storeSearchVo.getTypeIds().get(0);
                    Boolean iscontent=false;
                    for (Long typeIdsNew:
                            typeIds) {
                        if(typeIdsNew==typeId){
                            iscontent=true;
                            break;
                        }
                    }
                    if(iscontent){
                        storeSearchVosNew.add(storeSearchVo1);
                    }
                }else{
                    storeSearchVosNew.add(storeSearchVo1);
                }
                System.out.println("距离" + (int)calculate + "km");
            });
            return getDataTable(storeSearchVosNew);
        }else {
            return getDataTableLose(null);
        }
    }
}
