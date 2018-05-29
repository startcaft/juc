package com.startcaft.basic.core.vo;

import com.startcaft.basic.core.entity.DicItem;

/**
 *
 * @author startcaft
 * @date 2018/3/2
 */
public class DicItemVo extends BaseVo<DicItem> {

    private String code;
    private String name;
    private String description;
    private Integer seq;
    private Long pid;

    /**
     * 所有父节点id，以逗号分隔，包括0
     */
    private String parents;

    /**
     * 父节点名称
     */
    private String pname;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
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

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }
}
