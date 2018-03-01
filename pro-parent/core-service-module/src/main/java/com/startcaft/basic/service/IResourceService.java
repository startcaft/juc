package com.startcaft.basic.service;


import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.vo.ResourceVo;

import java.util.Set;

/**
 * @author startcaft
 * Created by startcaft on 2018/3/1.
 */
public interface IResourceService {

    /**
     * 获取登录用户的被授权的所有系统资源
     * @param loginName 用户名
     * @return
     * @throws BasicProException
     */
    Set<ResourceVo> getUserRoleResrouces(String loginName) throws BasicProException;

    /**
     * 获取第一级的菜单，是不需要被授权的
     * @return
     * @throws BasicProException
     */
    Set<ResourceVo> getRootLevelMenus() throws BasicProException;

    /**
     * 获取指定顶层节点和指定用户被授权的二级菜单
     * @param rootId 顶层节点id
     * @param loginName 登录用户名
     * @return
     * @throws BasicProException
     */
    Set<ResourceVo> getSecondLevelMenusByRoot(Long rootId,String loginName) throws BasicProException;
}
