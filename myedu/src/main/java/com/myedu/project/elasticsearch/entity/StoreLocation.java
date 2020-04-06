package com.myedu.project.elasticsearch.entity;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.GeoPointField;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Field;
import java.util.Arrays;
import java.util.Date;

/**
 * 门店对象 yun_store
 * 
 * @author 梁少鹏
 * @date 2020-01-04
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(indexName = "storelocation",type = "doc")
public class StoreLocation extends BaseEntity
{

    /** 主键 */
    @Id
    private Long id;

    /** 门店名称 */
    @Field
    private String name;

    /** 门店logo */
    @Field
    private String logo;

    /** 门店负责人 */
    @Field
    private String manager;

    /** 负责人电话 */
    @Field
    private String managerPhone;

    /** 门店照片墙 */
    @Field
    private String photos;

    /** 健康证 */
    @Field
    private String healths;

    /** 门店宣传视频地址 */
    @Field
    private String video;

    /** 门店营业执照 */
    @Field
    private String license;

    /** 省 */
    @Excel(name = "省")
    private String province;

    /** 市 */
    @Excel(name = "市")
    private String city;

    /** 区 */
    @Field
    private String area;

    /** 门店详细地址 */
    @Field
    private String address;


    /** 删除标志（0代表存在 1代表删除） */
    @Field
    private String delFlag;

    @GeoPointField
    private GeoPoint location;//位置坐标 lon经度 lat纬度

    /** 创建人Id */
    @Field
    private Long createById;


    /** 状态（0待审核1审核通过2审核驳回） */
    @Field
    private String status;

    /** 驳回原因 */
    @Field
    private String rejectResion;

    /** 体验开始时间 */
    @Field
    private Date beginExperienceTime;

    /** 体验结束时间 */
    @Field
    private Date endExperienceTime;

    /** 门店等级关联id */
    @Field
    private Long vipLevelId;

    @Field
    private String distanceMeters;//距离多少米



}
