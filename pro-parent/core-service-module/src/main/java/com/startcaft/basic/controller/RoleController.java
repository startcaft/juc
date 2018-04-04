/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/21 10:35
 * Description: 角色管理相关服务
 */
package com.startcaft.basic.controller;

import com.startcaft.basic.core.beans.DataGridBean;
import com.startcaft.basic.core.beans.RoleBean;
import com.startcaft.basic.core.beans.RoleModifyBean;
import com.startcaft.basic.core.vo.ResponseBean;
import com.startcaft.basic.core.vo.RoleVo;
import com.startcaft.basic.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;


/**
 * 〈一句话功能简述〉<br> 
 * 〈角色管理相关服务〉
 *
 * @author StartCaft
 * @create 2018/3/21
 * @since 1.0.0
 */
@Api(value = "/roles",description = "角色相关服务API")
@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "角色信息动态查询",notes = "参数可以为空，需要用户登陆")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "name",required = false,dataType = "string",paramType = "query")
    )
    @GetMapping(value = "/list",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public DataGridBean<RoleVo> getRoles(@RequestParam(value = "name",required = false) String name){
        {
            Set<RoleVo> voSet = roleService.getRoles(name);
            return new DataGridBean<RoleVo>(voSet.size(),voSet);
        }
    }

    @ApiOperation(value = "为角色授权",notes = "需要用户登陆")
    @ApiImplicitParams(
            {
                @ApiImplicitParam(name = "roleId",required = true,dataType = "long",paramType = "query"),
                @ApiImplicitParam(name = "resIds[]",required = true,dataType = "long[]",paramType = "query")
            }
    )
    @GetMapping(value = "/grant",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean grant(@RequestParam(value = "roleId")  long roleId,
                              @RequestParam(value = "resIds[]")  long[]  resIds) {
        {
            roleService.grant(roleId,resIds);
            return new ResponseBean(true,SUCCESS_MSG,"授权成功");
        }
    }

    @ApiOperation(value = "获取角色的基本信息",notes = "需要用户登陆")
    @ApiImplicitParams(
                @ApiImplicitParam(name = "roleId",required = true,dataType = "long",paramType = "path")
    )
    @GetMapping(value = "/{roleId}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean getRole(@PathVariable(value = "roleId",required = true)long roleId){
        {
            RoleVo vo = roleService.getRoleInfo(roleId);
            return new ResponseBean(true,SUCCESS_MSG,vo);
        }
    }

    @ApiOperation(value = "新增角色",notes = "判断条件就是bean中的id是否为空")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "bean",required = true,dataType = "RoleBean",paramType = "body")
    )
    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean save(@Valid @RequestBody RoleBean bean) {
        {
            ResponseBean<String> result = new ResponseBean<>();
            roleService.insertRole(bean);

            result.setReqSuccess(true);
            result.setMsg(SUCCESS_MSG);
            result.setData("save role[" + bean.getName() + "] success");

            return result;
        }
    }

    @ApiOperation(value = "修改角色",notes = "判断条件就是bean中的id是否为空")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "bean",required = true,dataType = "RoleModifyBean",paramType = "body")
    )
    @PostMapping(value = "/modify",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean modify(@Valid @RequestBody RoleModifyBean bean) {
        {
            ResponseBean<String> result = new ResponseBean<>();
            roleService.modifyRole(bean);

            result.setReqSuccess(true);
            result.setMsg(SUCCESS_MSG);
            result.setData("modify role[" + bean.getName() + "] success");

            return result;
        }
    }
}
