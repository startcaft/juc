/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/5/16 14:09
 * Description: 资源列表
 */
package com.startcaft.basic.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.startcaft.basic.core.entity.Resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈资源列表〉
 *
 * @author StartCaft
 * @create 2018/5/16
 * @since 1.0.0
 */
public class ResourceChild extends BaseVo<Resource> {

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDatetime;
    private String description;
    private String icon;
    private String name;
    private Integer resTypeCode;
    private String resTypeMsg;
    private Integer seq;
    private Integer statesCode;
    private String statesMsg;
    private String url;
    private Long pid;

    /**
     * 父节点名称
     */
    private String pname;

    /**
     * 子节点
     */
    private List<ResourceChild> children = new ArrayList<>();



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

    public Integer getResTypeCode() {
        return resTypeCode;
    }

    public void setResTypeCode(Integer resTypeCode) {
        this.resTypeCode = resTypeCode;
    }

    public String getResTypeMsg() {
        return resTypeMsg;
    }

    public void setResTypeMsg(String resTypeMsg) {
        this.resTypeMsg = resTypeMsg;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getStatesCode() {
        return statesCode;
    }

    public void setStatesCode(Integer statesCode) {
        this.statesCode = statesCode;
    }

    public String getStatesMsg() {
        return statesMsg;
    }

    public void setStatesMsg(String statesMsg) {
        this.statesMsg = statesMsg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public List<ResourceChild> getChildren() {
        return children;
    }

    public void setChildren(List<ResourceChild> children) {
        this.children = children;
    }
}
