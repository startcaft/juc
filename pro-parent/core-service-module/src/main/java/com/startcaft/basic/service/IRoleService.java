package com.startcaft.basic.service;

import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.vo.RoleVo;

import java.util.Set;

/**
 * @author startcaft
 * Created by startcaft on 2018/3/1.
 */
public interface IRoleService {

    /**
     * 获取一个指定排列的角色信息集合，可以根据 name 来过滤，name 可以为空
     * @param name
     * @return
     * @throws BasicProException
     */
    Set<RoleVo> getRoles(String name) throws BasicProException;

    /**
     * 为角色授权
     * @param roleId 角色id
     * @param resIds 资源id数组
     * @throws BasicProException
     */
    void grant(long roleId,long[] resIds) throws BasicProException;
}
