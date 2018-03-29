/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/21 10:35
 * Description: 角色管理相关服务
 */
package com.startcaft.basic.controller;

import com.startcaft.basic.core.beans.DataGridBean;
import com.startcaft.basic.core.vo.RoleVo;
import com.startcaft.basic.service.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
