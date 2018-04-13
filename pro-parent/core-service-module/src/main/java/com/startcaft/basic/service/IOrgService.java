/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/13 14:31
 * Description: 组织部门服务接口
 */
package com.startcaft.basic.service;

import com.startcaft.basic.core.beans.OrgBean;
import com.startcaft.basic.core.beans.OrgModifyBean;
import com.startcaft.basic.core.entity.Organization;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.vo.OrganizationVo;

import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈组织部门服务接口〉
 *
 * @author StartCaft
 * @create 2018/4/13
 * @since 1.0.0
 */
public interface IOrgService {

    /**
     * 获取组织部门详细信息，并获取父节点数据
     * @param id
     * @return
     * @throws BasicProException
     */
    OrganizationVo getOrgWithParent(long id) throws BasicProException;

    /**
     * 添加组织部门
     * @param bean
     * @throws BasicProException
     */
    void saveOrg(OrgBean bean) throws BasicProException;

    /**
     * 修改组织部门
     * @param bean
     * @throws BasicProException
     */
    void modifyOrg(OrgModifyBean bean) throws BasicProException;

    /**
     * 获取组织部门树状数据
     * @return
     * @throws BasicProException
     */
    Set<Organization> getOrgTree() throws BasicProException;
}
