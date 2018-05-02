/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/10 15:42
 * Description: 数据字典服务
 */
package com.startcaft.basic.controller;

import com.startcaft.basic.core.beans.DicItemBean;
import com.startcaft.basic.core.beans.DicItemModifyBean;
import com.startcaft.basic.core.entity.DicItem;
import com.startcaft.basic.core.vo.DicItemVo;
import com.startcaft.basic.core.vo.ResponseBean;
import com.startcaft.basic.service.IDicItemService;
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
 * 〈一句话功能简述〉<br> 
 * 〈数据字典服务〉
 *
 * @author StartCaft
 * @create 2018/4/10
 * @since 1.0.0
 */
@Api(value = "/dics",description = "系统资源相关服务API")
@RestController
@RequestMapping("/dics")
public class DicItemController extends BaseController {

    @Autowired
    private IDicItemService dicItemService;

    @ApiOperation(value = "获取所有的顶层节点的数据字典，一般用作字典大类",notes = "无需用户登陆")
    @GetMapping(value = "/roots",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean<Set<DicItemVo>> getRootDics(){
        {
            Set<DicItemVo> voSet = dicItemService.getTopDicItems();
            return new ResponseBean<Set<DicItemVo>>(true,SUCCESS_MSG,voSet);
        }
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "itemId",required = false,dataType = "long",paramType = "path")
    })
    @ApiOperation(value = "获取指定数据字典的树状结构,参数itemId=0则获取整个数据字典的树状结构",notes = "无需用户登陆")
    @GetMapping(value = "/tree/{itemId}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public Set<DicItem> getTree(@PathVariable(value = "itemId",required = false) long id){
        {
            Set<DicItem> itemSet = new TreeSet<>(new Comparator<DicItem>() {
                @Override
                public int compare(DicItem o1, DicItem o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });

            itemSet = dicItemService.getTreeByPid(id);

            return itemSet;
        }
    }

    @ApiOperation(value = "新增数据字典",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bean",required = true,dataType = "DicItemBean",paramType = "body")
    })
    @PostMapping(value = "/save",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean<String> addResource(@Valid @RequestBody DicItemBean bean){
        {
            ResponseBean<String> result = new ResponseBean<>();

            dicItemService.saveDicItem(bean);
            result.setReqSuccess(true);
            result.setMsg(SUCCESS_MSG);
            result.setData("save DicItem[" + bean.getName() + "] success");

            return result;
        }
    }

    @ApiOperation(value = "修改数据字典",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bean",required = true,dataType = "DicItemModifyBean",paramType = "body")
    })
    @PostMapping(value = "/modify",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    @RequiresAuthentication
    public ResponseBean<String> modifyResource(@Valid @RequestBody DicItemModifyBean bean){
        {
            ResponseBean<String> result = new ResponseBean<>();

            dicItemService.modifyDicItem(bean);
            result.setReqSuccess(true);
            result.setMsg(SUCCESS_MSG);
            result.setData("modify DicItem[" + bean.getName() + "] success");

            return result;
        }
    }

    @ApiOperation(value = "获取一个数据字典详细信息，附带父节点数据",notes = "需要用户登陆")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",required = true,dataType = "long",paramType = "path")
    })
    @RequiresAuthentication
    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseBean<DicItemVo> getSingle(@PathVariable(value = "id",required = true) long id){
        {
            return new ResponseBean<>(true,SUCCESS_MSG,dicItemService.getDicItemDetail(id));
        }
    }
}
