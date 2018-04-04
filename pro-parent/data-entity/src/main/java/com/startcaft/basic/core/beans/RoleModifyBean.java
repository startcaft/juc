/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/4 11:17
 * Description: 修改角色信息时候提交的数据
 */
package com.startcaft.basic.core.beans;

/**
 * 〈一句话功能简述〉<br> 
 * 〈修改角色信息时候提交的数据〉
 *
 * @author StartCaft
 * @create 2018/4/4
 * @since 1.0.0
 */
public class RoleModifyBean extends RoleBean {

    /**
     * 是否要验证 name 属性
     */
    private boolean checkName;
    /**
     * 是否要验证 check 属性
     */
    private boolean checkAlias;

    public boolean isCheckAlias() {
        return checkAlias;
    }

    public void setCheckAlias(boolean checkAlias) {
        this.checkAlias = checkAlias;
    }

    public boolean isCheckName() {
        return checkName;
    }

    public void setCheckName(boolean checkName) {
        this.checkName = checkName;
    }
}
