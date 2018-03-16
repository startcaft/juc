package com.startcaft.basic.core.entity;

import com.startcaft.basic.core.enums.ResourceType;
import com.startcaft.basic.core.enums.States;
import com.startcaft.basic.core.vo.ResourceVo;

import java.util.*;

/**
 * 系统资源实体类
 * @author startcaft
 * Created by startcaft on 2018/3/1.
 */
public class Resource extends BaseEntity<ResourceVo> {

    private Date createDatetime;
    private String description;
    private String icon;
    private String name;
    private ResourceType resourceType;
    private Integer seq;
    private States states;
    private String url;
    private Long pid;

    /**
     * 父节点
     */
    private Resource resource;
    /**
     * 子节点
     */
    private Set<Resource> children = new TreeSet<>(new Comparator<Resource>() {
        @Override
        public int compare(Resource o1, Resource o2) {
            return o1.getId().compareTo(o2.getId());
        }
    });

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

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public States getStates() {
        return states;
    }

    public void setStates(States states) {
        this.states = states;
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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Set<Resource> getChildren() {
        return children;
    }

    public void setChildren(Set<Resource> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Resource)){
            return false;
        }
        Resource resource = (Resource) o;

        return id.equals(resource.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    @Override
    protected void copyOtherProperties(ResourceVo resourceVo) {
        resourceVo.setResTypeCode(this.getResourceType().getCode());
        resourceVo.setResTypeMsg(this.getResourceType().getDesc());
        resourceVo.setStatesCode(this.getStates().getCode());
        resourceVo.setStatesMsg(this.getStates().getMsg());

        if (this.getResource() != null){
            resourceVo.setPid(this.getResource().getId());
            resourceVo.setPname(this.getResource().getName());
        }
    }

    @Override
    protected boolean otherPropertiesHook() {
        return super.otherPropertiesHook();
    }
}