package com.startcaft.basic.core.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色信息实体类
 * @author startcaft
 * @date 2018/3/5
 */
public class Role extends BaseEntity {

    private String description;
    private String name;
    private Integer seq;

    /**
     * 所包含的系统资源
     */
    private Set<Resource> resources = new HashSet<>(0);
    /**
     * 所包含的用户
     */
    private Set<User> users = new HashSet<>(0);

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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