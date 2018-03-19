/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/13 15:37
 * Description: 修改密码时的bean
 */
package com.startcaft.basic.core.beans;

/**
 * 〈一句话功能简述〉<br> 
 * 〈修改密码时的bean〉
 *
 * @author StartCaft
 * @create 2018/3/13
 * @since 1.0.0
 */
public class PwdBean extends LoginBean {

    private String newPwd;

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
