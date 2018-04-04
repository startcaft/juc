/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/3 10:43
 * Description: 角色表单数据
 */
package com.startcaft.basic.core.beans;

import com.startcaft.basic.core.entity.Role;
import com.startcaft.basic.core.vo.BaseVo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 〈一句话功能简述〉<br> 
 * 〈角色表单数据〉
 *
 * @author StartCaft
 * @create 2018/4/3
 * @since 1.0.0
 */
public class RoleBean extends BaseVo<Role> {

    @NotBlank(message = "角色名称不能为空")
    private String name;
    private String desc;
    @NotBlank(message = "角色别名不能为空")
    private String alias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
