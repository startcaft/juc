package com.startcaft.basic.core.entity;

import com.startcaft.basic.core.vo.RoleVo;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息实体类
 * @author startcaft
 * @date 2018/3/5
 */
public class Role extends BaseEntity<RoleVo> {

    private String name;
    private String desc;
    private String alias;

    /**
     * 所包含的系统资源
     */
    private Set<Resource> resources = new HashSet<>(0);
    /**
     * 所包含的用户
     */
    private Set<User> users = new HashSet<>(0);

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

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}