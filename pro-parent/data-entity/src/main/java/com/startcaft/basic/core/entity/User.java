package com.startcaft.basic.core.entity;

import com.startcaft.basic.core.enums.Gender;
import com.startcaft.basic.core.enums.States;
import com.startcaft.basic.core.vo.UserVo;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Administrator
 */
public class User extends BaseEntity<UserVo> {

    private String loginName;

    private String realName;

    private String password;

    private Gender gender;

    private States states;

    private Long organizationId;

    private Date createDatetime;

    /**
     * 所属组织
     */
    private Organization organization;

    /**
     * 包含的角色
     */
    private Set<Role> roles = new HashSet<>();

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
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

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        User user = (User) o;
        return Objects.equals(loginName, user.loginName) &&
                Objects.equals(realName, user.realName) &&
                Objects.equals(password, user.password) &&
                gender == user.gender &&
                states == user.states &&
                Objects.equals(organizationId, user.organizationId) &&
                Objects.equals(createDatetime, user.createDatetime) &&
                Objects.equals(organization, user.organization) &&
                Objects.equals(roles, user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginName, realName, password, gender, states, organizationId, createDatetime, organization, roles);
    }

    @Override
    protected void copyOtherProperties(UserVo userVo) {
        if (this.states != null){
            userVo.setStatesCode(this.states.getCode());
            userVo.setStatesMsg(this.states.getMsg());
        }
        if (this.gender != null){
            userVo.setGenderCode(this.gender.getCode());
            userVo.setGenderDesc(this.gender.getDesc());
        }
        if (this.organization != null){
            userVo.setOrganizationName(this.organization.getOrgName());
        }
        if (this.roles != null && this.roles.size() > 0){
            Set<String> roleNames = new HashSet<>();
            Set<Long> roleIds = new HashSet<>();
            this.roles.forEach((role) -> {
                roleNames.add(role.getAlias());
                roleIds.add(role.getId());
            });

            userVo.setRoleIds(roleIds);
            userVo.setRoleNames(roleNames);
        }
    }

    @Override
    protected boolean otherPropertiesHook() {
        return true;
    }
}