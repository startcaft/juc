package com.startcaft.basic.controller;

import com.startcaft.basic.core.beans.*;
import com.startcaft.basic.core.vo.EasyuiGrid;
import com.startcaft.basic.core.vo.ResponseBean;
import com.startcaft.basic.core.vo.UserPageRequest;
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

import javax.validation.Valid;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

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

    @ApiOperation(value = "新增系统用户",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bean",required = true,dataType = "UserBean",paramType = "body")
    })
    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean<String> addUser(@Valid @RequestBody UserBean bean){
        {
            ResponseBean<String> result = new ResponseBean<>();

            userService.saveUser(bean);
            result.setReqSuccess(true);
            result.setMsg(SUCCESS_MSG);
            result.setData("save user：[" + bean.getLoginName() + "]，success");

            return result;
        }
    }

    @ApiOperation(value = "修改系统用户",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bean",required = true,dataType = "UserModifyBean",paramType = "body")
    })
    @PostMapping(value = "/modify",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean<String> modifyUser(@RequestBody UserModifyBean bean){
        {
            ResponseBean<String> result = new ResponseBean<>();

            userService.modifyUser(bean);
            result.setReqSuccess(true);
            result.setMsg(SUCCESS_MSG);
            result.setData("modify resource：[" + bean.getLoginName() + "]，success");

            return result;
        }
    }

    @ApiOperation(value = "获取一个用户详细信息",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",required = true,dataType = "long",paramType = "path")
    })
    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean<UserVo> getSingle(@PathVariable(value = "id",required = true) long id){
        {
            return new ResponseBean<UserVo>(true,SUCCESS_MSG,userService.searchSingleUser(id));
        }
    }

    @ApiOperation(value = "用户分页查询",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "rows",required = true,dataType = "int",paramType = "query"),
            @ApiImplicitParam(name = "orgId",required = false,dataType = "Long",paramType = "query"),
            @ApiImplicitParam(name = "loginName",required = false,dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "realName",required = false,dataType = "string",paramType = "query")
    })
    @GetMapping(value = "/page",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public EasyuiGrid<UserVo> getUserPage(@RequestParam(value = "page",required = true) int pageIndex,
                                          @RequestParam(value = "rows",required = true) int pageSize,
                                          @RequestParam(value = "orgId",required = false) Long orgId,
                                          @RequestParam(value = "loginName",required = false) String loginName,
                                          @RequestParam(value = "realName",required = false) String realName){
        {
            UserPageRequest request = new UserPageRequest();
            request.setPage(pageIndex);
            request.setRows(pageSize);
            request.setOrgId(orgId);
            request.setLoginName(loginName);
            request.setRealName(realName);
            return userService.getUserPage(request);
        }
    }

    @ApiOperation(value = "验证用户登陆信息",notes = "随便调用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token",required = true,dataType = "string",paramType = "query")
    })
    @GetMapping(value = "/check",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseData checkUserToken(@RequestParam(value = "token",required = true) String token){
        {
            AtomicBoolean result = new AtomicBoolean(false);

            // 解密获取 username，用于和数据库进行比对
            String username = JwtUtil.getUsername(token);
            if(username == null){
                return ResponseData.customerError().putDataValue("msg","无法解析的用户token");
            }
            else{
                Optional<UserVo> user = userService.searchUserByLoginName(username);
                user.ifPresent((userVo) -> {
                    if(JwtUtil.verify(token,userVo.getLoginName(),userVo.getPassword())){
                        result.set(true);
                    }
                });
            }

            if (result.get()){
                return ResponseData.ok().putDataValue("msg","用户认证成功");
            }
            else {
                return ResponseData.customerError().putDataValue("msg","认证失败或者token失效，请重新登陆");
            }
        }
    }
}
