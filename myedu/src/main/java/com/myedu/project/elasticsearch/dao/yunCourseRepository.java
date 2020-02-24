package com.myedu.project.elasticsearch.dao;

import com.myedu.project.elasticsearch.entity.yunCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface yunCourseRepository extends ElasticsearchRepository<yunCourse, String> {
}
