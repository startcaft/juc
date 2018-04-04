/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/20 16:26
 * Description:
 */
package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.Role;

import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author StartCaft
 * @create 2018/3/20
 * @since 1.0.0
 */
public interface IRoleDao {

    /**
     * 查询详细
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Long id);

    /**
     * 动态查询
     * @param params
     * @return
     */
    Set<Role> selectListDynamic(Map<String,Object> params);

    /**
     * 插入
     * @param role
     * @return
     */
    Integer insert(Role role);

    /**
     * 更新
     * @param role
     * @return
     */
    Integer updateByPrimaryKeySelective(Role role);
}
