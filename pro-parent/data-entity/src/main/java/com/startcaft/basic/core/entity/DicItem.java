package com.startcaft.basic.core.entity;

import com.startcaft.basic.core.vo.DicItemVo;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 数据字典实体类
 * @author startcaft
 * @date 2018/3/2
 */
public class DicItem extends BaseEntity<DicItemVo> {

    private String code;
    private String name;
    private String description;
    private Integer seq;
    private Long pid;

    /**
     * 父节点
     */
    private DicItem parentType;

    /**
     * 子节点
     */
    private Set<DicItem> children = new TreeSet<>(new Comparator<DicItem>() {
        @Override
        public int compare(DicItem o1, DicItem o2) {
            return o1.getId().compareTo(o2.getId());
        }
    });

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

    public DicItem getParentType() {
        return parentType;
    }

    public void setParentType(DicItem parentType) {
        this.parentType = parentType;
    }

    public Set<DicItem> getChildren() {
        return children;
    }

    public void setChildren(Set<DicItem> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DicItem dicType = (DicItem) o;

        return id.equals(dicType.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
