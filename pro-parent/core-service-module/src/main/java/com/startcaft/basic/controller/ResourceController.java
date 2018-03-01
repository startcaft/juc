package com.startcaft.basic.controller;

import com.startcaft.basic.core.vo.ResourceVo;
import com.startcaft.basic.core.vo.ResponseBean;
import com.startcaft.basic.service.IResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Set;

/**
 *
 * @author startcaft
 * @date 2018/3/1
 */
@Api("系统资源相关服务API")
@RestController
@RequestMapping("/resources")
public class ResourceController extends BaseController {

    @Autowired
    private IResourceService resourceService;

    @ApiOperation(value = "获取所有顶层节点的系统资源",notes = "顶层节点的系统资源不受权限控制")
    @GetMapping(value = "/",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean getRoots(){
        {
            Set<ResourceVo> list = resourceService.getRootLevelMenus();
            return new ResponseBean<Set<ResourceVo>>(true,SUCCESS_MSG,list);
        }
    }
}
