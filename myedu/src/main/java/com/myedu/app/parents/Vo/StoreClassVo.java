package com.myedu.app.parents.Vo;


import com.myedu.project.dataBasic.domain.SysCourse;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/7/5
 * Time: 10:18
 * Description:
 */
public class StoreClassVo  {
    private String storeTypeName;
    private Long storeTypeId;
    private List<SysCourse>  sysCourses;

    public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    public Long getStoreTypeId() {
        return storeTypeId;
    }

    public void setStoreTypeId(Long storeTypeId) {
        this.storeTypeId = storeTypeId;
    }

    public List<SysCourse> getSysCourses() {
        return sysCourses;
    }

    public void setSysCourses(List<SysCourse> sysCourses) {
        this.sysCourses = sysCourses;
    }
}
