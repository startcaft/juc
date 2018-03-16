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

    @ApiOperation(value = "新增系统资源",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vo",required = true,dataType = "ResourceVo")
    })
    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseBean<String> addResource(@RequestBody ResourceBean bean){
        {
            ResponseBean<String> result = new ResponseBean<>();

            resourceService.AddResource(bean);

            result.setReqSuccess(true);
            result.setMsg(SUCCESS_MSG);
            result.setData("save resource[" + bean.getName() + "]---[" + bean.getUrl() + "] success");
            return result;
        }
    }
}
