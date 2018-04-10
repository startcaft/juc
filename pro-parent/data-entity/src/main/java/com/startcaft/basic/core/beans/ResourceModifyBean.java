/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/10 14:25
 * Description: 修改系统资源时需要的bean
 */
package com.startcaft.basic.core.beans;

/**
 * 〈一句话功能简述〉<br> 
 * 〈修改系统资源时需要的bean〉
 *
 * @author StartCaft
 * @create 2018/4/10
 * @since 1.0.0
 */
public class ResourceModifyBean extends ResourceBean {

    /**
     * 是否要验证 name 属性
     */
    private boolean checkName;

    public boolean isCheckName() {
        return checkName;
    }

    public void setCheckName(boolean checkName) {
        this.checkName = checkName;
    }
}
