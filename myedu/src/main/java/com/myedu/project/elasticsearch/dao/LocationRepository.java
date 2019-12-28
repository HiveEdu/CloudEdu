package com.myedu.project.elasticsearch.dao;

import com.myedu.project.elasticsearch.entity.Location;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @Describe:
 * @Auther: zhenlong.yang
 * @Date: 2018/12/4 13:19
 */
@Component
public interface LocationRepository extends ElasticsearchRepository<Location, String> {

}
