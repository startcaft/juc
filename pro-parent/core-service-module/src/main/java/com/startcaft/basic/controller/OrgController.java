/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/13 15:45
 * Description: 组织部门服务控制器
 */
package com.startcaft.basic.controller;

import com.startcaft.basic.core.beans.OrgBean;
import com.startcaft.basic.core.beans.OrgModifyBean;
import com.startcaft.basic.core.entity.Organization;
import com.startcaft.basic.core.vo.OrganizationVo;
import com.startcaft.basic.core.vo.ResponseBean;
import com.startcaft.basic.service.IOrgService;
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
 * 〈组织部门服务控制器〉
 *
 * @author StartCaft
 * @create 2018/4/13
 * @since 1.0.0
 */
@Api(value = "/orgs",description = "组织部门相关服务API")
@RestController
@RequestMapping("/orgs")
public class OrgController extends BaseController {

    @Autowired
    private IOrgService orgService;

    @ApiOperation(value = "获取组织部门树",notes = "需要用户登陆")
    @GetMapping(value ="/tree",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public Set<Organization> tree(){
        {
            return orgService.getOrgTree();
        }
    }

    @ApiOperation(value = "获取一个组织部门详细信息，附带父节点数据",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",required = true,dataType = "long",paramType = "path")
    })
    @RequiresAuthentication
    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean<OrganizationVo> getSingle(@PathVariable(value = "id",required = true) long id){
        {
            return new ResponseBean<OrganizationVo>(true,SUCCESS_MSG,orgService.getOrgWithParent(id));
        }
    }

    @ApiOperation(value = "新增组织部门",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bean",required = true,dataType = "OrgBean",paramType = "body")
    })
    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean<String> addResource(@Valid @RequestBody OrgBean bean){
        {
            ResponseBean<String> result = new ResponseBean<>();

            orgService.saveOrg(bean);
            result.setReqSuccess(true);
            result.setMsg(SUCCESS_MSG);
            result.setData("save org[" + bean.getOrgName() + "] success");

            return result;
        }
    }

    @ApiOperation(value = "修改组织部门",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bean",required = true,dataType = "OrgModifyBean",paramType = "body")
    })
    @PostMapping(value = "/modify",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean<String> modifyResource(@Valid @RequestBody OrgModifyBean bean){
        {
            ResponseBean<String> result = new ResponseBean<>();

            orgService.modifyOrg(bean);
            result.setReqSuccess(true);
            result.setMsg(SUCCESS_MSG);
            result.setData("modify resource[" + bean.getOrgBak()+ "] success");

            return result;
        }
    }
}
