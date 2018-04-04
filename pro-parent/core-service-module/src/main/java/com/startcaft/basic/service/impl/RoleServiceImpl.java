/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/20 16:39
 * Description:
 */
package com.startcaft.basic.service.impl;

import com.startcaft.basic.core.beans.RoleBean;
import com.startcaft.basic.core.beans.RoleModifyBean;
import com.startcaft.basic.core.entity.Role;
import com.startcaft.basic.core.entity.RoleResource;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.exceptions.SqlExecuteException;
import com.startcaft.basic.core.exceptions.UniqueException;
import com.startcaft.basic.core.vo.RoleVo;
import com.startcaft.basic.dao.master.IRoleDao;
import com.startcaft.basic.dao.master.IRoleResourceDao;
import com.startcaft.basic.service.IRoleService;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author StartCaft
 * @create 2018/3/20
 * @since 1.0.0
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Autowired
    private IRoleResourceDao roleResourceDao;

    @Resource(name="masterBatchSqlSession")
    private SqlSession batchSqlSession;

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public Set<RoleVo> getRoles(String name) throws BasicProException {
        {
            Map<String,Object> params = new HashMap<>();
            Set<RoleVo> voSet = new TreeSet<>(new Comparator<RoleVo>() {
                @Override
                public int compare(RoleVo o1, RoleVo o2) {
                    return o1.getId().compareTo(o2.getId());
                }
            });

            // name 是可以为空的
            if (!StringUtils.isEmpty(name)){
                params.put("name",name);
            }
            Set<Role> roleSet = roleDao.selectListDynamic(params);

            roleSet.forEach((r) -> {
                voSet.add(r.copyPropertiesTemplate(new RoleVo()));
            });

            return voSet;
        }
    }

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void grant(long roleId, long[] resIds) throws BasicProException {
        {
            long startTime = System.currentTimeMillis();

            // 先进行删除
            batchSqlSession.getMapper(IRoleResourceDao.class).deleteByRoleId(roleId);

            // 再进行批量插入
            for(int i=0; i<resIds.length; i++){
                try {
                    batchSqlSession.getMapper(IRoleResourceDao.class).insert(new RoleResource(roleId,resIds[i]));
                }
                catch (Exception e){
                    throw new BasicProException(e);
                }
            }

            long endTime = System.currentTimeMillis();

            LOGGER.info("批量授权执行时长:" + (endTime - startTime) + " ms");
        }
    }

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void insertRole(RoleBean roleBean) throws BasicProException {
        {
            Map<String,Object> params = new HashMap<>();
            Set<Role> roleSet;

            // 先验证角色名是否唯一
            params.put("name",roleBean.getName());
            roleSet = roleDao.selectListDynamic(params);
            if (roleSet != null && roleSet.size() > 0){
                throw new UniqueException("role_name:[" + roleBean.getName() + "] is exist!");
            }

            // 再验证角色别名是否唯一
            params.clear();
            params.put("alias", roleBean.getAlias());
            roleSet = roleDao.selectListDynamic(params);
            if (roleSet != null && roleSet.size() > 0){
                throw new UniqueException("role_alias:[" + roleBean.getAlias() + "] is exist!");
            }

            Role role = new Role();
            roleBean.copyPropertiesTemplate(role);

            int result = roleDao.insert(role);
            if (result != 1){
                throw new SqlExecuteException("execute insert result is error");
            }
        }
    }

    @Override
    public RoleVo getRoleInfo(long roleId) throws BasicProException {
        {
            RoleVo vo = new RoleVo();
            Role role = roleDao.selectByPrimaryKey(roleId);

            Optional<Role> optionalRole = Optional.ofNullable(role);
            optionalRole.ifPresent((r) -> {
                r.copyPropertiesTemplate(vo);
            });

            return vo;
        }
    }

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void modifyRole(RoleModifyBean bean) throws BasicProException {
        {
            Map<String,Object> params = new HashMap<>();
            Set<Role> roleSet;

            if (bean.isCheckName()){
                // 先验证角色名是否唯一
                params.put("name",bean.getName());
                roleSet = roleDao.selectListDynamic(params);
                if (roleSet != null && roleSet.size() > 0){
                    throw new UniqueException("role_name:[" + bean.getName() + "] is exist!");
                }
            }

            if (bean.isCheckAlias()){
                // 再验证角色别名是否唯一
                params.clear();
                params.put("alias", bean.getAlias());
                roleSet = roleDao.selectListDynamic(params);
                if (roleSet != null && roleSet.size() > 0){
                    throw new UniqueException("role_alias:[" + bean.getAlias() + "] is exist!");
                }
            }


            Role role = new Role();
            bean.copyPropertiesTemplate(role);

            int result = roleDao.updateByPrimaryKeySelective(role);
            if (result != 1){
                throw new SqlExecuteException("execute insert result is error");
            }
        }
    }
}
