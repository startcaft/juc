package com.startcaft.basic.core.entity;

/**
 * 字典类型---树状结构
 * @author startcaft
 * @date 2018/3/2
 */
public class DicType extends BaseEntity {

    private String code;
    private String name;
    private String description;
    private Integer seq;
    private Long pid;

    /**
     * 父节点
     */
    private DicType parentType;

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

    public DicType getParentType() {
        return parentType;
    }

    public void setParentType(DicType parentType) {
        this.parentType = parentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DicType dicType = (DicType) o;

        return id.equals(dicType.id);
    }

    @Override
    public int hashCode() {
        return pid.hashCode();
    }
}
