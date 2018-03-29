package com.startcaft.basic.core.entity;

public class RoleResource extends BaseEntity {

    private Long roleId;

    private Long resoruceId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getResoruceId() {
        return resoruceId;
    }

    public void setResoruceId(Long resoruceId) {
        this.resoruceId = resoruceId;
    }

    public RoleResource(Long roleId, Long resoruceId) {
        this.roleId = roleId;
        this.resoruceId = resoruceId;
    }

    public RoleResource() {
    }
}