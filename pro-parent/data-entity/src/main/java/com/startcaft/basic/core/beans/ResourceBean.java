/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/16 16:20
 * Description: 客户端向服务端提交的系统资源数据
 */
package com.startcaft.basic.core.beans;

import com.startcaft.basic.core.entity.Resource;
import com.startcaft.basic.core.vo.BaseVo;

import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈客户端向服务端提交的系统资源数据〉
 *
 * @author StartCaft
 * @create 2018/3/16
 * @since 1.0.0
 */
public class ResourceBean extends BaseVo<Resource> {

    /**
     * 默认当前时间
     */
    private Date createDatetime = new Date();
    private String description;
    private String icon;
    private String name;
    private int resTypeCode;
    private int seq;
    private int statesCode;
    private String url;
    private int pid;

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResTypeCode() {
        return resTypeCode;
    }

    public void setResTypeCode(int resTypeCode) {
        this.resTypeCode = resTypeCode;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getStatesCode() {
        return statesCode;
    }

    public void setStatesCode(int statesCode) {
        this.statesCode = statesCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    @Override
    protected void copyOtherProperties(Resource resource) {

    }
}
