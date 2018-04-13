/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/4/12 16:45
 * Description: 提交的数据字典数据bean
 */
package com.startcaft.basic.core.beans;

import com.startcaft.basic.core.entity.DicItem;
import com.startcaft.basic.core.vo.BaseVo;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 〈一句话功能简述〉<br> 
 * 〈提交的数据字典数据bean〉
 *
 * @author StartCaft
 * @create 2018/4/12
 * @since 1.0.0
 */
public class DicItemBean extends BaseVo<DicItem> {

    private String code;
    @NotBlank(message = "资源名称不能为空")
    private String name;
    private String description;
    private Integer seq;
    private Long pid;

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
}
