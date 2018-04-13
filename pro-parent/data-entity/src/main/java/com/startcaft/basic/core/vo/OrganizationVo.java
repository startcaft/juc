/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/13 14:24
 * Description: 组织部门vo对象
 */
package com.startcaft.basic.core.vo;

import com.startcaft.basic.core.entity.Organization;

/**
 * 〈一句话功能简述〉<br> 
 * 〈组织部门vo对象〉
 *
 * @author StartCaft
 * @create 2018/4/13
 * @since 1.0.0
 */
public class OrganizationVo extends BaseVo<Organization> {

    private String orgName;

    private String orgBak;

    private Long pid;

    private String pName;

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

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
