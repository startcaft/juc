package com.startcaft.basic.core.entity;

import com.startcaft.basic.core.vo.OrganizationVo;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Administrator
 */
public class Organization extends BaseEntity<OrganizationVo> {

    private String orgName;

    private String orgBak;

    private Long pid;

    /**
     * 父节点
     */
    private Organization parentOrg;

    /**
     * 子节点
     */
    private Set<Organization> children = new TreeSet<>(new Comparator<Organization>() {
        @Override
        public int compare(Organization o1, Organization o2) {
            return o1.getId().compareTo(o2.getId());
        }
    });

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgBak() {
        return orgBak;
    }

    public void setOrgBak(String orgBak) {
        this.orgBak = orgBak == null ? null : orgBak.trim();
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Organization getParentOrg() {
        return parentOrg;
    }

    public void setParentOrg(Organization parentOrg) {
        this.parentOrg = parentOrg;
    }

    public Set<Organization> getChildren() {
        return children;
    }

    public void setChildren(Set<Organization> children) {
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
        Organization that = (Organization) o;
        return Objects.equals(orgName, that.orgName) &&
                Objects.equals(orgBak, that.orgBak) &&
                Objects.equals(pid, that.pid) &&
                Objects.equals(parentOrg, that.parentOrg);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orgName, orgBak, pid, parentOrg);
    }

    @Override
    protected void copyOtherProperties(OrganizationVo organizationVo) {
        if (this.parentOrg != null){
            organizationVo.setpName(this.parentOrg.getOrgName());
        }
    }

    @Override
    protected boolean otherPropertiesHook() {
        return true;
    }
}