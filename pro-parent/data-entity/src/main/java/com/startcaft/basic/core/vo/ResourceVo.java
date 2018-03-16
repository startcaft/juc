package com.startcaft.basic.core.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author startcaft
 * Created by startcaft on 2018/3/1.
 */
public class ResourceVo extends BaseVo {

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
    private Set<ResourceVo> childs = new HashSet<>();


    // 下面的属性是jqgrid treegrid要用的
    /**
     * 父节点
     */
    private Long _parentId;
    /**
     * 层级
     */
    private Integer level;

    /**
     * 是否叶节点，childs 为null即是
     */
    private boolean isLeaf;

    /**
     * 是否展开
     */
    private boolean expanded;

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

    public Set<ResourceVo> getChilds() {
        return childs;
    }

    public void setChilds(Set<ResourceVo> childs) {
        this.childs = childs;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public Long get_parentId() {
        return _parentId;
    }

    public void set_parentId(Long _parentId) {
        this._parentId = _parentId;
    }

    @Override
    protected void copyOtherProperties(Object o) {
        // 不执行多余属性的拷贝工作
    }
}