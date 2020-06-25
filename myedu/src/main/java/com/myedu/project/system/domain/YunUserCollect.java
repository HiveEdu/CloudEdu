package com.myedu.project.system.domain;

import com.myedu.framework.aspectj.lang.annotation.Excel;
import com.myedu.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 用户收藏对象 yun_user_collect
 * 
 * @author myedu
 * @date 2020-06-25
 */
public class YunUserCollect extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long userId;

    /** 用户昵称 */
    @Excel(name = "用户昵称")
    private String userNickname;

    /** 用户头像 */
    @Excel(name = "用户头像")
    private String userImage;

    /** $column.columnComment */
    @Excel(name = "用户头像")
    private Long storeId;

    /** 家教教师id */
    @Excel(name = "家教教师id")
    private Long teacherId;

    /** 门店名称 */
    @Excel(name = "门店名称")
    private String storeName;

    /** 教师姓名 */
    @Excel(name = "教师姓名")
    private String teacherName;

    /** 删除标志（0代表存在 1代表删除） */
    private String delFlag;

    /** 门店logo */
    @Excel(name = "门店logo")
    private String storeLogo;

    /** 老师头像 */
    @Excel(name = "老师头像")
    private String teachePic;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserNickname(String userNickname) 
    {
        this.userNickname = userNickname;
    }

    public String getUserNickname() 
    {
        return userNickname;
    }
    public void setUserImage(String userImage) 
    {
        this.userImage = userImage;
    }

    public String getUserImage() 
    {
        return userImage;
    }
    public void setStoreId(Long storeId) 
    {
        this.storeId = storeId;
    }

    public Long getStoreId() 
    {
        return storeId;
    }
    public void setTeacherId(Long teacherId) 
    {
        this.teacherId = teacherId;
    }

    public Long getTeacherId() 
    {
        return teacherId;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }
    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setStoreLogo(String storeLogo) 
    {
        this.storeLogo = storeLogo;
    }

    public String getStoreLogo() 
    {
        return storeLogo;
    }
    public void setTeachePic(String teachePic) 
    {
        this.teachePic = teachePic;
    }

    public String getTeachePic() 
    {
        return teachePic;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("userNickname", getUserNickname())
            .append("userImage", getUserImage())
            .append("storeId", getStoreId())
            .append("teacherId", getTeacherId())
            .append("storeName", getStoreName())
            .append("teacherName", getTeacherName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .append("storeLogo", getStoreLogo())
            .append("teachePic", getTeachePic())
            .toString();
    }
}
