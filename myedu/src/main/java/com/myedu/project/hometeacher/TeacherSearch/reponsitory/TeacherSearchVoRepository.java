package com.myedu.project.hometeacher.TeacherSearch.reponsitory;

import com.myedu.project.hometeacher.TeacherSearch.entityVo.TeacherSearchVo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
public interface TeacherSearchVoRepository extends ElasticsearchRepository<TeacherSearchVo, Long>
{

}
