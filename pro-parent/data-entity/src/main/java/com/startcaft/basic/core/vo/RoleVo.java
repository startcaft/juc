/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/20 16:32
 * Description:
 */
package com.startcaft.basic.core.vo;

import com.startcaft.basic.core.entity.Role;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author StartCaft
 * @create 2018/3/20
 * @since 1.0.0
 */
public class RoleVo extends BaseVo<Role> {

    private String name;
    private String desc;
    private String alias;

    /**
     * 所包含的系统资源
     */
    private String[] resources;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String[] getResources() {
        return resources;
    }

    public void setResources(String[] resources) {
        this.resources = resources;
    }
}
