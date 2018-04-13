package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.Organization;

import java.util.Map;
import java.util.Set;

/**
 * @author Administrator
 */
public interface IOrganizationDao {

    /**
     * insert 自动生成
     * @param record
     * @return
     */
    int insert(Organization record);

    /**
     * 主键查询 自动生成
     * @param id
     * @return
     */
    Organization selectByPrimaryKey(Long id);

    /**
     * update 自动生成
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Organization record);

    /**
     * 动态查询列表
     * @param params
     * @return
     */
    Set<Organization> selectListDynamic(Map<String,Object> params);

    /**
     * 组织部门树
     * @return
     */
    Organization getTree();
}