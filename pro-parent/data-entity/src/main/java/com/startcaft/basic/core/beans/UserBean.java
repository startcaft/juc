/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/16 16:13
 * Description: 新增用户时提交的bean
 */
package com.startcaft.basic.core.beans;

import com.startcaft.basic.core.entity.User;
import com.startcaft.basic.core.enums.Gender;
import com.startcaft.basic.core.enums.States;
import com.startcaft.basic.core.vo.BaseVo;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈新增用户时提交的bean〉
 *
 * @author StartCaft
 * @create 2018/4/16
 * @since 1.0.0
 */
public class UserBean extends BaseVo<User> {

    @NotBlank(message = "密码不能为空")
    private String password;
    private Date createDatetime = new Date();

    @NotBlank(message = "账号不能为空")
    private String loginName;
    private String realName;

    @NotBlank(message = "性别不能为空")
    private Integer gender;
    /**
     * 默认状态：正常
     */
    private Integer statesCode = States.NORMAL.getCode();

    @NotBlank(message = "必须选择一个组织部门")
    private Long organizationId;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        this.loginName = loginName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getStatesCode() {
        return statesCode;
    }

    public void setStatesCode(Integer statesCode) {
        this.statesCode = statesCode;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    protected void copyOtherProperties(User user) {
        user.setStates(States.getStates(this.statesCode));
        user.setGender(Gender.getGender(this.gender));
    }

    @Override
    protected boolean otherPropertiesHook() {
        return true;
    }
}
