/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/20 16:39
 * Description:
 */
package com.startcaft.basic.service.impl;

import com.startcaft.basic.core.entity.Role;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.vo.RoleVo;
import com.startcaft.basic.dao.master.IRoleDao;
import com.startcaft.basic.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
}
