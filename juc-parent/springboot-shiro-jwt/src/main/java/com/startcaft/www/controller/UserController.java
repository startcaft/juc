package com.startcaft.www.controller;

import com.startcaft.www.exception.UnauthorizedException;
import com.startcaft.www.model.User;
import com.startcaft.www.service.UserService;
import com.startcaft.www.util.JwtUtil;
import com.startcaft.www.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.parameters.BodyParameter;
import io.swagger.models.parameters.Parameter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * @author startcaft
 * Created by startcaft on 2018/2/28.
 */
@Api(value="认证相关API")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    // /login 所有人可以访问

    @ApiOperation("用户登录API")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username",value = "用户名",required = true,paramType = "body",dataType = "String"),
            @ApiImplicitParam(name="username",value = "登录密码",required = true,paramType = "body",dataType = "String")
    })
    @PostMapping(name="/login",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean<String> login(@RequestParam("username") String username,
                                      @RequestParam("password") String password){

        User user = userService.getUser(username);
        if(user.getPassword().equals(password)){
            ResponseBean<String> result = new ResponseBean<>();
            result.setCode(200);
            result.setMsg("Login Success");
            result.setData(JwtUtil.sign(username,password));
            return result;
        }
        else{
            throw  new UnauthorizedException();
        }
    }

//    // /article 所有人都可以访问，但是游客和登陆用户看到的内容不同
//    @GetMapping(name="/article",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public ResponseBean<String> article() {
//        Subject subject = SecurityUtils.getSubject();
//        if (subject.isAuthenticated()) {
//            return new ResponseBean(200, "You are already logged in", "正常内容");
//        } else {
//            return new ResponseBean(200, "You are guest", "游客内容");
//        }
//    }
//
//    // /require_auth 需要登陆才能访问
//    @GetMapping(name="/auth",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    @RequiresAuthentication
//    public ResponseBean<String>  requireAuth() {
//        return new ResponseBean(200, "You are authenticated", "需要登陆才能看见的内容");
//    }
//
//    // /require_role 需要 admin 角色才能访问
//    @GetMapping(name="/role",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    @RequiresRoles("admin")
//    public ResponseBean<String>  requireRole() {
//        return new ResponseBean(200, "You are visiting require_role", "管理员才能看见内容");
//    }
//
//    // /require_permission 同时需要 view 和 edit 的权限才能访问
//    @GetMapping(name="/permission",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
//    public ResponseBean<String> requirePermission() {
//        return new ResponseBean(200, "You are visiting permission require edit,view", "view,edit权限才能看见的内容");
//    }
//
    @GetMapping(name="/401",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean<String>  unauthorized() {
        return new ResponseBean(401, "Unauthorized", "没有权限查看该内容");
    }
}
