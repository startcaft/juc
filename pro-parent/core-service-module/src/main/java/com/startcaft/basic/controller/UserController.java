package com.startcaft.basic.controller;

import com.startcaft.basic.core.beans.LoginBean;
import com.startcaft.basic.core.beans.PwdBean;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户相关服务
 * @author startcaft
 * @date 2018/3/5
 */
@Api(value = "/users",description = "系统用户相关服务API")
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value="/用户登录，返回经过签发的token",notes = "采用JWT的认证方式，token过期时间为120分钟")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginBean",required = true,dataType = "LoginBean")
    })
    @PostMapping(value="/login",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean<String> userLogin(@RequestBody LoginBean loginBean){
        {
            UserVo vo = userService.userLogin(loginBean.getLoginName(),loginBean.getPassword());
            return new ResponseBean(true,SUCCESS_MSG + "，the data is access token", JwtUtil.sign(vo.getLoginName(),vo.getPassword()));
        }
    }

    @ApiOperation(value = "/修改用户密码",notes = "需要用户登陆,请求必须在header中添加Authorization字段，例如Authorization: token，token为密钥")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pwdBean",required = true,dataType = "PwdBean"),
    })
    @PostMapping(value = "/pwd",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean UpdatePwd(@RequestBody PwdBean pwdBean){
        {
            ResponseBean responseBean = new ResponseBean();
            userService.editUserPwd(pwdBean.getLoginName(),pwdBean.getPassword(),pwdBean.getNewPwd());
            responseBean.setReqSuccess(true);
            responseBean.setMsg(SUCCESS_MSG);
            responseBean.setData("update pwd success");
            return responseBean;
        }
    }
}
