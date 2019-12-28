package com.myedu.app.common.controller;

import com.myedu.common.constant.Constants;
import com.myedu.common.constant.UserConstants;
import com.myedu.common.utils.SecurityUtils;
import com.myedu.framework.security.service.SysLoginService;
import com.myedu.framework.web.controller.BaseController;
import com.myedu.framework.web.domain.AjaxResult;
import com.myedu.project.system.domain.SysUser;
import com.myedu.project.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    private SysLoginService loginService;
  /*
   * @Description :
   * @Author : 梁少鹏
   * @Date : 2019/12/21 7:48
   */
    @ApiOperation("用户注册")
    @ApiImplicitParam(name = "SysUser", value = "用户注册", dataType = "SysUser")
    @PostMapping("/userRegister")
    public AjaxResult saveUser(SysUser user)
    {
        if(UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        user.setUserName(user.getPhonenumber());
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
    @PostMapping("/editUser")
    public AjaxResult editUser(SysUser user)
    {
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        user.setUserName(user.getPhonenumber());
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUser(user));
    }

   /*
    * @Description :App登录方法
    * @Author : 梁少鹏
    * @Date : 2019/12/28 18:42
    */
    @ApiOperation("APP用户登录")
    @ApiImplicitParam(name = "SysUser", value = "APP用户登录", dataType = "String")
    @PostMapping("/appLogin")
    public AjaxResult appLogin(String username, String password, String code, String uuid)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(username, password, code, uuid);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

}