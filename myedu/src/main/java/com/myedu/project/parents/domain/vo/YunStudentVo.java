package com.myedu.project.parents.domain.vo;

import com.myedu.project.parents.domain.YunStudent;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2019/12/28
 * Time: 21:41
 * Description:学生关联实体
 */
public class YunStudentVo extends YunStudent {

    @ApiModelProperty("年级名称")
    private String gradeName;


    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("gradeName",getGradeName())
                .toString();
    }
}