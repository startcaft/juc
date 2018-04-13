/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/12 16:54
 * Description: 修改数据字典时的bean
 */
package com.startcaft.basic.core.beans;

/**
 * 〈一句话功能简述〉<br> 
 * 〈修改数据字典时的bean〉
 *
 * @author StartCaft
 * @create 2018/4/12
 * @since 1.0.0
 */
public class DicItemModifyBean extends DicItemBean {

    private boolean checkName;

    public boolean isCheckName() {
        return checkName;
    }

    public void setCheckName(boolean checkName) {
        this.checkName = checkName;
    }
}
