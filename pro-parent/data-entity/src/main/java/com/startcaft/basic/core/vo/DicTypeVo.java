package com.startcaft.basic.core.vo;

/**
 *
 * @author startcaft
 * @date 2018/3/2
 */
public class DicTypeVo extends BaseVo {

    private String code;
    private String name;
    private String description;
    private Integer seq;
    private Long pid;

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
}
