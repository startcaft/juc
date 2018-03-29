package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.RoleResource;

/**
 * @author Administrator
 */
public interface IRoleResourceDao {

    /**
     * 根据角色删除资源授权
     * @param roleId
     * @return
     */
    int deleteByRoleId(Long roleId);

    /**
     * 单一授权，一般该方法会配置一个批量的 sqlSession 来执行进行批量授权
     * @param record
     * @return
     */
    int insert(RoleResource record);
}