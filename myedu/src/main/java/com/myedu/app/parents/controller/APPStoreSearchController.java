package com.myedu.app.parents.controller;

import com.myedu.common.api.CommonPage;
import com.myedu.common.api.CommonResult;
import com.myedu.common.utils.ServletUtils;
import com.myedu.framework.aspectj.lang.annotation.AutoIdempotent;
import com.myedu.framework.security.LoginUser;
import com.myedu.framework.security.service.TokenService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.page.TableDataInfo;
import com.myedu.project.store.service.IYunStoreHitsService;
import com.myedu.project.store.storeSearch.entityVo.StoreSearchVo;
import com.myedu.project.store.storeSearch.service.StoreService;
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
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private IYunStoreHitsService yunStoreHitsService;
    @Autowired
    private StoreService storeService;

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
            Set<StoreSearchVo> storeSearchVosNew=new HashSet<>();
            List<StoreSearchVo> storeSearchVos= page.getContent();
            for (StoreSearchVo storeSearchVo1:storeSearchVos){
                double calculate = GeoDistance.ARC.calculate(storeSearchVo1.getLocation().getLat(), storeSearchVo1.getLocation().getLon(), lat, lon, DistanceUnit.KILOMETERS);
                storeSearchVo1.setDistanceMeters(String.valueOf(calculate));
                List<Long> typeIds=storeSearchVo1.getTypeIds();
                //如果有门店类型根据门点类型搜索
                if(storeSearchVo.getTypeIds()!=null&&storeSearchVo.getTypeIds().size()>0&&storeSearchVo.getCoureseNames()==null){
                    Long typeId= storeSearchVo.getTypeIds().get(0);
                    Boolean iscontent=false;
                    for (Long typeIdsNew:typeIds) {
                        if(typeIdsNew==typeId){
                            iscontent=true;
                            break;
                        }
                    }
                    if(iscontent){
                        //storeSearchVo1.setHitsAll(getHitsAll(storeSearchVo1.getId()));
                        storeSearchVosNew.add(storeSearchVo1);
                    }
                }else if(storeSearchVo.getTypeIds()!=null&&storeSearchVo.getCoureseNames()!=null&&storeSearchVo.getTypeIds().size()>0&&storeSearchVo.getCoureseNames().size()>0){
                    Long typeId= storeSearchVo.getTypeIds().get(0);
                    Boolean iscontent=false;
                    for (Long typeIdsNew:typeIds) {
                        if(typeIdsNew==typeId){
                            iscontent=true;
                            break;
                        }
                    }
                    if(iscontent){
                        List<String>  courseNames1= storeSearchVo1.getCoureseNames();
                        List<String>  courseNames2= storeSearchVo.getCoureseNames();
                        for(String courseName1:courseNames1){
                            for(String courseName2:courseNames2){
                                if(courseName1.contains(courseName2)){
                                    storeSearchVosNew.add(storeSearchVo1);
                                }
                            }
                        }
                    }
                }else{
                    storeSearchVosNew.add(storeSearchVo1);
                }
                System.out.println("距离" + (int)calculate + "km");
            }
            return getDataTable(new ArrayList<>(storeSearchVosNew));
        }else {
            return getDataTableLose(null);
        }
    }

    //获取门店总点击量
    public int getHitsAll(Long id){
        System.out.println(yunStoreHitsService.selectYunStoreHitsCountAll(id));
       return yunStoreHitsService.selectYunStoreHitsCountAll(id);
    }


    @ApiOperation(value = "门店搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<StoreSearchVo>> search(@RequestParam(required = false) String keyword,
                                                          @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                          @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<StoreSearchVo> esProductPage = storeService.search(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }


    @AutoIdempotent
    @ApiOperation("首页搜索门店列表")
    @GetMapping(value = "/searchlist")
    public TableDataInfo searchlist(double lon, double lat, String distance,String keywords,StoreSearchVo storeSearchVo)
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
            nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("name", keywords));
            Page<StoreSearchVo> page =
                    elasticsearchTemplate.queryForPage(
                            nativeSearchQueryBuilder.build(), StoreSearchVo.class);
            Set<StoreSearchVo> storeSearchVosNew=new HashSet<>();
            List<StoreSearchVo> storeSearchVos= page.getContent();
            for (StoreSearchVo storeSearchVo1:storeSearchVos){
                double calculate = GeoDistance.ARC.calculate(storeSearchVo1.getLocation().getLat(), storeSearchVo1.getLocation().getLon(), lat, lon, DistanceUnit.KILOMETERS);
                storeSearchVo1.setDistanceMeters(String.valueOf(calculate));
                List<Long> typeIds=storeSearchVo1.getTypeIds();
                //如果有门店类型根据门点类型搜索
                if(storeSearchVo.getTypeIds().size()>0&&storeSearchVo.getCoureseNames().size()==0){
                    Long typeId= storeSearchVo.getTypeIds().get(0);
                    Boolean iscontent=false;
                    for (Long typeIdsNew:typeIds) {
                        if(typeIdsNew==typeId){
                            iscontent=true;
                            break;
                        }
                    }
                    if(iscontent){
                        //storeSearchVo1.setHitsAll(getHitsAll(storeSearchVo1.getId()));
                        storeSearchVosNew.add(storeSearchVo1);
                    }
                }else if(storeSearchVo.getTypeIds().size()>0&&storeSearchVo.getCoureseNames().size()>0){
                    Long typeId= storeSearchVo.getTypeIds().get(0);
                    Boolean iscontent=false;
                    for (Long typeIdsNew:typeIds) {
                        if(typeIdsNew==typeId){
                            iscontent=true;
                            break;
                        }
                    }
                    if(iscontent){
                        List<String>  courseNames1= storeSearchVo1.getCoureseNames();
                        List<String>  courseNames2= storeSearchVo.getCoureseNames();
                        for(String courseName1:courseNames1){
                            for(String courseName2:courseNames2){
                               if(courseName1.contains(courseName2)){
                                   storeSearchVosNew.add(storeSearchVo1);
                               }
                            }
                        }
                    }
                }else{
                    storeSearchVosNew.add(storeSearchVo1);
                }
                System.out.println("距离" + (int)calculate + "km");
            }

            return getDataTable(new ArrayList<>(storeSearchVosNew));
        }else {
            return getDataTableLose(null);
        }
    }



}
