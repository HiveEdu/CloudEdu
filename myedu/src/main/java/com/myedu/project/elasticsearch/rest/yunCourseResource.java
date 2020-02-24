package com.myedu.project.elasticsearch.rest;

import com.alipay.api.domain.Article;
import com.myedu.project.elasticsearch.dao.yunCourseRepository;
import com.myedu.project.elasticsearch.entity.yunCourse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/yunCourse")
public class yunCourseResource {

    @Autowired private yunCourseRepository CourseRepository;
    @PostMapping("/save")
    public yunCourse save(@RequestBody yunCourse yuncourse){
        return CourseRepository.save(yuncourse);
    }

    @GetMapping("/findAll")
    public Iterable<yunCourse> findAll(){
        return CourseRepository.findAll();
    }

    /**---------- QueryBuilder 查询 -----------**/
    /**分页查询*/
    @GetMapping("/page")
    public Page<yunCourse> range(String query,
                               @PageableDefault(page = 0, size = 5, sort = "time", direction = Sort.Direction.DESC) Pageable pageable){
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        if(query != null) {
            qb.must(QueryBuilders.matchQuery("name", query));
        }
        return CourseRepository.search(qb, pageable);
    }

    /**精确匹配*/
    @GetMapping("/term")
    public Page<yunCourse> term(String query){
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must(QueryBuilders.termQuery("classify", query));
        return (Page<yunCourse>)CourseRepository.search(qb);
    }

    /**模糊匹配*/
    @GetMapping("/match")
    public Page<yunCourse> match(String query){
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must(QueryBuilders.matchQuery("reclassifyColl", query));
        return (Page<yunCourse>)CourseRepository.search(qb);
    }

    /**短语模糊匹配*/
    @GetMapping("/matchPhrase")
    public Page<yunCourse> matchPhraseQuery(String query){
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must(QueryBuilders.matchPhraseQuery("isOneToOne", query));
        return (Page<yunCourse>)CourseRepository.search(qb);
    }

    /**范围查询*/
    @GetMapping("/range")
    public Page<yunCourse> range(long query){
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        qb.must(QueryBuilders.rangeQuery("time").gt(query));
        //qb.must(QueryBuilders.rangeQuery("time").from(query).to(System.currentTimeMillis()));//大于query，小于当前时间
        return (Page<yunCourse>)CourseRepository.search(qb);
    }
}
