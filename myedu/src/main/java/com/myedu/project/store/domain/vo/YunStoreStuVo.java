package com.myedu.project.store.domain.vo;

import com.myedu.project.store.domain.YunStoreStu;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/3/3
 * Time: 13:46
 * Description:
 */
@Data
public class YunStoreStuVo extends YunStoreStu {
    /*
     * @Description :门店名称
     */
    private String storeName;

    /*
     * @Description :学生名称
     */
    private String studentName;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
