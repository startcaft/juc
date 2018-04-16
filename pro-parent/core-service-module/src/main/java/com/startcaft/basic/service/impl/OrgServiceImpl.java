/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/13 14:32
 * Description: 组织部门服务接口实现类
 */
package com.startcaft.basic.service.impl;

import com.startcaft.basic.core.beans.OrgBean;
import com.startcaft.basic.core.beans.OrgModifyBean;
import com.startcaft.basic.core.entity.Organization;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.exceptions.SqlExecuteException;
import com.startcaft.basic.core.vo.OrganizationVo;
import com.startcaft.basic.dao.master.IOrganizationDao;
import com.startcaft.basic.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈组织部门服务接口实现类〉
 *
 * @author StartCaft
 * @create 2018/4/13
 * @since 1.0.0
 */
@Service
public class OrgServiceImpl implements IOrgService {

    @Autowired
    private IOrganizationDao orgDao;

    @Override
    public OrganizationVo getOrgWithParent(long id) throws BasicProException {
        {
            Organization organization = orgDao.selectByPrimaryKey(id);
            return  organization.copyPropertiesTemplate(new OrganizationVo());
        }
    }

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void saveOrg(OrgBean bean) throws BasicProException {
        {
            // 确保只有一个根节点
            if (bean.getPid() == null || bean.getPid().intValue() == 0){
                throw new BasicProException("组织部门只能有一个根节点");
            }

            Map<String,Object> params = new HashMap<>(1);
            params.put("name",bean.getOrgName());
            //确保组织部门名称不重复
            Set<Organization> orgSet = orgDao.selectListDynamic(params);
            if (orgSet != null && orgSet.size() > 0){
                throw new BasicProException("组织部门[" + bean.getOrgName() + "]，已经存在");
            }

            Organization organization = new Organization();
            bean.copyPropertiesTemplate(organization);

            int result = orgDao.insert(organization);
            if (result != 1){
                throw new SqlExecuteException("execute insert result is error");
            }
        }
    }

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void modifyOrg(OrgModifyBean bean) throws BasicProException {
        {
            if (bean.isCheckName()){
                Map<String,Object> params = new HashMap<>(1);
                params.put("name",bean.getOrgName());
                //确保组织部门名称不重复
                Set<Organization> orgSet = orgDao.selectListDynamic(params);
                if (orgSet != null && orgSet.size() > 0){
                    throw new BasicProException("组织部门[" + bean.getOrgName() + "]，已经存在");
                }
            }

            Organization organization = new Organization();
            bean.copyPropertiesTemplate(organization);

            int result = orgDao.updateByPrimaryKeySelective(organization);
            if (result != 1){
                throw new SqlExecuteException("execute insert result is error");
            }
        }
    }

    @Override
    public Set<Organization> getOrgTree() throws BasicProException {
        {
            Set<Organization> orgSet = new TreeSet<>(new Comparator<Organization>() {
                @Override
                public int compare(Organization o1, Organization o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });

            Organization organization = orgDao.getTree();
            orgSet.add(organization);
            return orgSet;
        }
    }
}
