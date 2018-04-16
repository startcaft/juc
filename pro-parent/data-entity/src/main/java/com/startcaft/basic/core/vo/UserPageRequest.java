/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/16 16:45
 * Description: 分页查询用户数据
 */
package com.startcaft.basic.core.vo;

/**
 * 〈一句话功能简述〉<br> 
 * 〈分页查询用户数据〉
 *
 * @author StartCaft
 * @create 2018/4/16
 * @since 1.0.0
 */
public class UserPageRequest extends AbstractEasyuiPageRequest {

    private long orgId;

    private String loginName;

    private String realName;

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
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
}
