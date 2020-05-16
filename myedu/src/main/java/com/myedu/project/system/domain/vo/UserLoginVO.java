package com.myedu.project.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created with IntelliJ IDEA.
 * User: ${梁少鹏}
 * Date: 2020/5/16
 * Time: 17:29
 * Description:
 */
@Setter
@Getter
public class UserLoginVO {
    private String userName;
    private String password;
    private String code;
    private String uuid;
    private Long[] roleIds;
}
