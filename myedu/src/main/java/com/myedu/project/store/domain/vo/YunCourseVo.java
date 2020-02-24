package com.myedu.project.store.domain.vo;

import com.myedu.project.store.domain.YunCourse;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/2/5
 * Time: 11:36
 * Description:
 */
@Data
public class YunCourseVo extends YunCourse {

    /*
     * @Description :门店名称
     */
    private String storeName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
