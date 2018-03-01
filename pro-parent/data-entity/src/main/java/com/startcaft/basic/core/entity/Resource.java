package com.startcaft.basic.core.entity;

import com.startcaft.basic.core.enums.ResourceType;
import com.startcaft.basic.core.enums.States;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author startcaft
 * Created by startcaft on 2018/3/1.
 */
public class Resource extends BaseEntity {

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
    private Set<Resource> resources = new HashSet<Resource>();

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
        this.description = description == null ? null : description.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.url = url == null ? null : url.trim();
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

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(HashSet<Resource> resources) {
        this.resources = resources;
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
}