package com.startcaft.basic.service;


import com.startcaft.basic.core.beans.ResourceBean;
import com.startcaft.basic.core.beans.ResourceModifyBean;
import com.startcaft.basic.core.entity.Resource;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.vo.ResourceVo;

import java.util.Set;

/**
 * @author startcaft
 * Created by startcaft on 2018/3/1.
 */
public interface IResourceService {

    /**
     * 查询详细信息，并查询父节点
     * @param id 主键id
     * @return
     * @throws BasicProException
     */
    ResourceVo getSingeWithParent(long id) throws BasicProException;

    /**
     * 获取登录用户的被授权的所有系统资源
     * @param loginName 用户名
     * @return
     * @throws BasicProException
     */
    Set<ResourceVo> getUserRoleResources(String loginName) throws BasicProException;

    /**
     * 根据用户角色获取资源数据
     * @param roleId 角色id
     * @return
     * @throws BasicProException
     */
    Set<ResourceVo> getResourcesByRole(long roleId) throws BasicProException;

    /**
     * 获取第一级的菜单，是不需要被授权的
     * @return
     * @throws BasicProException
     */
    Set<ResourceVo> getFirstLevelMenus() throws BasicProException;

    /**
     * 获取唯一的顶层节点，规定顶层节点的pid为0，否则视为异常
     * @return
     * @throws BasicProException
     */
    public ResourceVo getTopParent() throws BasicProException;

    /**
     * 获取指定节点和指定用户被授权的二级菜单
     * @param parentId 指定节点id。一般认为是一个父节点id
     * @param loginName 登录用户名
     * @return
     * @throws BasicProException
     */
    Set<ResourceVo> getSecondLevelMenusByRoot(Long parentId,String loginName) throws BasicProException;


    /**
     * 获取所有菜单，树状结构，因为Resource只有一个根节点，所以之返回 Resource
     * @return
     * @throws BasicProException
     */
    Resource getResTree() throws BasicProException;

    /**
     * 添加一个系统资源数据
     * @param bean ResourceBean
     * @throws BasicProException
     */
    void AddResource(ResourceBean bean) throws BasicProException;

    /**
     * 修改一给系统资源数据
     * @param bean ResourceModifyBean
     * @throws BasicProException
     */
    void modifyResource(ResourceModifyBean bean) throws BasicProException;
}
