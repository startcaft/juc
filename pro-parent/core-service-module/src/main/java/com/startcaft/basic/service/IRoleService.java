package com.startcaft.basic.service;

import com.startcaft.basic.core.beans.RoleBean;
import com.startcaft.basic.core.beans.RoleModifyBean;
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

    /**
     * 添加角色，确保角色名唯一，确保角色别名唯一
     * @param roleBean
     * @throws BasicProException
     */
    void insertRole(RoleBean roleBean) throws BasicProException;

    /**
     * 修改角色信息，确保角色名唯一，确保角色别名唯一
     * @param bean
     * @throws BasicProException
     */
    void modifyRole(RoleModifyBean bean) throws BasicProException;

    /**
     * 获取角色的基本信息
     * @param roleId
     * @return
     * @throws BasicProException
     */
    RoleVo getRoleInfo(long roleId) throws BasicProException;
}
