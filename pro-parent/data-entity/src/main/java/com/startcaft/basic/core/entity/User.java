package com.startcaft.basic.core.entity;

import com.startcaft.basic.core.enums.Gender;
import com.startcaft.basic.core.enums.States;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户信息实体类
 * @author startcaft
 * @date 2018/3/5
 */
public class User extends BaseEntity {

    private Integer age;
    private Date createDatetime;
    private String loginName;
    private String name;
    private String password;
    private Gender gender;
    private States states;
    private Long organizationId;

    /**
     * 所属组织
     */
    private Organization organization;
    /**
     * 包含的所有权限
     */
    private Set<Role> roles = new HashSet<Role>();

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public States getStates() {
        return states;
    }

    public void setStates(States states) {
        this.states = states;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
