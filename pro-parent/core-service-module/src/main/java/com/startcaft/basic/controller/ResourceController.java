package com.startcaft.basic.controller;

import com.startcaft.basic.core.beans.ResourceBean;
import com.startcaft.basic.core.entity.Resource;
import com.startcaft.basic.core.vo.ResourceVo;
import com.startcaft.basic.core.vo.ResponseBean;
import com.startcaft.basic.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author startcaft
 * @date 2018/3/1
 */
@Api(value = "/resources",description = "系统资源相关服务API")
@RestController
@RequestMapping("/resources")
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;

    @ApiOperation(value = "获取一个资源数据详细信息，附带父节点数据",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",required = true,dataType = "long",paramType = "path")
    })
    @RequiresAuthentication
    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean<ResourceVo> getSingle(@PathVariable(value = "id",required = true) long id){
        {
            return new ResponseBean<>(true,SUCCESS_MSG,resourceService.getSingeWithParent(id));
        }
    }

    @ApiOperation(value = "获取顶层节点，pid为0，有且只有一个，否则报错",notes = "不受权限控制,作为资源树的定点")
    @GetMapping(value = "/root",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean<ResourceVo> getRoot(){
        {
            ResourceVo resourceVo = resourceService.getTopParent();
            return new ResponseBean<ResourceVo>(Boolean.TRUE,SUCCESS_MSG,resourceVo);
        }
    }

    @ApiOperation(value = "获取一级菜单，pid=0的所有系统资源",notes = "一级菜单不受权限控制")
    @GetMapping(value = "/firstLevels",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean getFirstLevels(){
        {
            Set<ResourceVo> list = resourceService.getFirstLevelMenus();
            return new ResponseBean<Set<ResourceVo>>(true,SUCCESS_MSG,list);
        }
    }

    @ApiOperation(value = "获取指定一级菜单，指定用户的被授权的系统资源",notes = "一级菜单的下级菜单，这一级需要用户自身的授权以及需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",required = true,dataType = "string",paramType = "path"),
            @ApiImplicitParam(name = "pid",required = true,dataType = "string",paramType = "path")
    })
    @GetMapping(value ="/secondLevels/{username}/{pid}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean getSecondLevels(@PathVariable(value = "username",required = true) String username,
                                        @PathVariable(value = "pid",required = true) Long pid){
        {
            Set<ResourceVo> set = resourceService.getSecondLevelMenusByRoot(pid,username);
            return new ResponseBean<Set<ResourceVo>>(true,SUCCESS_MSG,set);
        }
    }

    @ApiOperation(value = "获取资源树",notes = "需要用户登陆")
    @GetMapping(value ="/tree",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public Set<Resource> tree(){
        {
            Set<Resource> resourceSet = new TreeSet<>(new Comparator<Resource>() {
                @Override
                public int compare(Resource o1, Resource o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });
            Resource node = resourceService.getResTree();
            resourceSet.add(node);
            return resourceSet;
        }
    }

    @ApiOperation(value = "获取用户被授权的所有系统资源，主要是二级菜单链接及其下属的功能按钮",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",required = true,dataType = "string",paramType = "path"),
    })
    @GetMapping(value ="/userRes/{username}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean getUserRoleResources(@PathVariable(value="username",required = true) String loginName){
        {
            Set<ResourceVo> resourceSet = resourceService.getUserRoleResources(loginName);
            return new ResponseBean(true,SUCCESS_MSG,resourceSet);
        }
    }

    @ApiOperation(value = "获取角色相关的系统资源",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",required = true,dataType = "long",paramType = "path"),
    })
    @GetMapping(value ="/roleRes/{roleId}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean getResourcesByRole(@PathVariable(value="roleId",required = true) long roleId){
        {
            Set<ResourceVo> resourceSet = resourceService.getResourcesByRole(roleId);
            return new ResponseBean(true,SUCCESS_MSG,resourceSet);
        }
    }

    @ApiOperation(value = "新增/修改系统资源",notes = "判断条件就是bean中的id是否为空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bean",required = true,dataType = "ResourceBean",paramType = "body")
    })
    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean<String> addResource(@Valid @RequestBody ResourceBean bean){
        {
            ResponseBean<String> result = new ResponseBean<>();

            if (bean.getId() == null || bean.getId().intValue() == 0){
                // 新增
                resourceService.AddResource(bean);

                result.setReqSuccess(true);
                result.setMsg(SUCCESS_MSG);
                result.setData("save resource[" + bean.getName() + "]---[" + bean.getUrl() + "] success");
            }
            else {
                // 修改
                resourceService.modifyResource(bean);
                result.setReqSuccess(true);
                result.setMsg(SUCCESS_MSG);
            }


            return result;
        }
    }
}
