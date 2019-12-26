package com.myedu.project.app.parents.controller;

import com.myedu.common.constant.UserConstants;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.project.system.domain.SysUser;
import com.myedu.project.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: 梁少鹏
 * Date: 2019/12/20
 * Time: 22:30
 * Description:
 */
@Api("APP用户信息管理")
@RestController
@RequestMapping("/app/user")
public class UserController extends BaseController {

    @Autowired
    private ISysUserService userService;
  /*
   * @Description :
   * @Author : 梁少鹏
   * @Date : 2019/12/21 7:48
   */
    @ApiOperation("新增用户")
    @ApiImplicitParam(name = "SysUser", value = "新增用户信息", dataType = "SysUser")
    @PostMapping("/saveUser")
    public AjaxResult saveUser(SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if(UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
//        else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
//        {
//            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在");
//        }
        user.setCreateBy(SecurityUtils.getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        return toAjax(userService.insertUser(user));
    }

    /*
     * @Description :修改用户
     * @Author : 梁少鹏
     * @Date : 2019/12/21 7:48
     */
    @ApiOperation("修改用户")
    @ApiImplicitParam(name = "SysUser", value = "修改用户信息", dataType = "SysUser")
    @PutMapping("/editUser")
    public AjaxResult editUser(SysUser user)
    {
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
//        else if (UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
//        {
//            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
//        }
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

}