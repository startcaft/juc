/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/13 14:32
 * Description: 用户修改密码时使用的bean
 */
package com.startcaft.basic.core.vo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户修改密码时使用的bean〉
 *
 * @author StartCaft
 * @create 2018/3/13
 * @since 1.0.0
 */
public class UserPwdVo extends BaseVo {

    private String oldPwd;
    private String loginName;
    private String newPwd;

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    @Override
    protected void copyOtherProperties(Object o) {

    }
}
