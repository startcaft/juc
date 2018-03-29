/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/3/22 14:48
 * Description: easyui 树状结构
 */
package com.startcaft.basic.core.beans;

import java.util.Set;

/**
 * 〈一句话功能简述〉<br> 
 * 〈easyui 树状结构〉
 *
 * @author StartCaft
 * @create 2018/3/22
 * @since 1.0.0
 */
public class TreeBean {

    private String id;
    private String text;
    /**
     * open/close
     */
    private String state = "open";
    private boolean checked = false;
    private Object attributes;
    private Set<TreeBean> children;
    private String iconCls;
    private String pid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    public Set<TreeBean> getChildren() {
        return children;
    }

    public void setChildren(Set<TreeBean> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
