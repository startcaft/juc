/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/13 14:45
 * Description: 组织部门提交表单的数据
 */
package com.startcaft.basic.core.beans;

import com.startcaft.basic.core.entity.Organization;
import com.startcaft.basic.core.vo.BaseVo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 〈一句话功能简述〉<br> 
 * 〈组织部门提交表单的数据〉
 *
 * @author StartCaft
 * @create 2018/4/13
 * @since 1.0.0
 */
public class OrgBean extends BaseVo<Organization> {

    @NotBlank(message = "组织部门名称不能为空")
    private String orgName;

    private String orgBak;

    private Long pid;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgBak() {
        return orgBak;
    }

    public void setOrgBak(String orgBak) {
        this.orgBak = orgBak;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
