package com.myedu.project.elasticsearch.entity;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @Describe:
 * @Auther: llf
 * @Date: 2020/2/24 13:44
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "yunCourse")
public class yunCourse {

    /** 主键 */
    @Id
    private Long id;

    /** 课程名称 */
    private String name;

    /** 课程类型（0托管班1辅导班2兴趣班） */
    private String classify;

    /** 托管类型（0午托1晚托2全托3早教4临托） */
    private String reclassifyColl;

    /** 是否一对一（0是1否） */
    private String isOneToOne;
}
