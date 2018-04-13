/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/13 14:46
 * Description: 修改组织部门提交的数据
 */
package com.startcaft.basic.core.beans;

/**
 * 〈一句话功能简述〉<br> 
 * 〈修改组织部门提交的数据〉
 *
 * @author StartCaft
 * @create 2018/4/13
 * @since 1.0.0
 */
public class OrgModifyBean extends OrgBean {

    private boolean checkName;

    public boolean isCheckName() {
        return checkName;
    }

    public void setCheckName(boolean checkName) {
        this.checkName = checkName;
    }
}
