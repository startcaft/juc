package com.startcaft.basic.controller;

import com.startcaft.basic.core.vo.ResponseBean;
import com.startcaft.basic.core.vo.UserVo;
import com.startcaft.basic.service.IUserService;
import com.startcaft.basic.shiro.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户相关服务
 * @author startcaft
 * @date 2018/3/5
 */
@Api("系统用户相关服务API")
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value="/用户登录，返回经过签发的token",notes = "采用JWT的认证方式，token过期时间为120分钟")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userVo",required = true,dataType = "UserVo")
    })
    @PostMapping(value="/login",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean<String> userLogin(@RequestBody UserVo userVo){
        {
            UserVo vo = userService.userLogin(userVo.getLoginName(),userVo.getPassword());
            return new ResponseBean(true,SUCCESS_MSG + "，the data is access token", JwtUtil.sign(vo.getLoginName(),vo.getPassword()));
        }
    }

    @ApiOperation(value="/测试jwt token认证",notes = "请求必须在header中添加Authorization字段，例如Authorization: token，token为密钥")
    @GetMapping(value="/require_auth")
    @RequiresAuthentication
    public ResponseBean requireAuth() {
        return new ResponseBean(true,SUCCESS_MSG,null);
    }
}
