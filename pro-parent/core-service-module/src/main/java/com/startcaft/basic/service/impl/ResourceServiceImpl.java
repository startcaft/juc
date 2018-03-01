package com.startcaft.basic.service.impl;

import com.startcaft.basic.core.entity.Resource;
import com.startcaft.basic.core.enums.States;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.exceptions.FieldNullException;
import com.startcaft.basic.core.vo.ResourceVo;
import com.startcaft.basic.dao.master.ResourceDao;
import com.startcaft.basic.service.IResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author startcaft
 * @date 2018/3/1
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceDao resourceDao;

    @Transactional(value="masterTransactionManager",readOnly = true,rollbackFor = Exception.class)
    @Override
    public Set<ResourceVo> getUserRoleResrouces(String loginName) throws BasicProException {
        {
            Set<Resource> set = resourceDao.selectByLoginName(loginName);
            Set<ResourceVo> voSet = new HashSet<>(set.size());

            //过滤被停用的系统资源和url为空的资源
            Stream<Resource> resourceStream = set.stream()
                    .filter((r) -> r.getStates() != States.LOCKED)
                    .filter((r) -> !StringUtils.isEmpty(r.getUrl()));

            resourceStream.forEach((r) -> {
                voSet.add(this.cycleCopyProperties(r));
            });
            return voSet;
        }
    }

    @Transactional(value="masterTransactionManager",readOnly = true,rollbackFor = Exception.class)
    @Override
    public Set<ResourceVo> getRootLevelMenus() throws BasicProException {
        {
            Set<Resource> set = resourceDao.selectRoots();
            Set<ResourceVo> voSet = new HashSet<>(set.size());
            set.forEach((entity) -> {
                voSet.add(this.cycleCopyProperties(entity));
            });
            return voSet;
        }
    }

    @Transactional(value="masterTransactionManager",readOnly = true,rollbackFor = Exception.class)
    @Override
    public Set<ResourceVo> getSecondLevelMenusByRoot(Long rootId, String loginName) throws BasicProException {
        {
            if (rootId == null){
                throw new FieldNullException("rootId is null");
            }
            if (StringUtils.isEmpty(loginName)){
                throw new FieldNullException("loginName is null");
            }
            // 查询数据库
            Set<Resource> set = resourceDao.selectSecondLevelMenus(rootId,loginName);
            Set<ResourceVo> voSet = new HashSet<>(set.size());
            set.forEach((res) -> {
                voSet.add(this.cycleCopyProperties(res));
            });

            return null;
        }
    }

    /**
     * 从模型对象到vo对象的属性拷贝
     * @param entity Resource 模型对象
     * @return ResourceVo vo对象
     */
    private ResourceVo cycleCopyProperties(Resource entity){
        {
            ResourceVo vo = new ResourceVo();
            if (entity != null){
                BeanUtils.copyProperties(entity, vo);
                vo.setResTypeCode(entity.getResourceType().getCode());
                vo.setResTypeMsg(entity.getResourceType().getDesc());
                vo.setStatesCode(entity.getStates().getCode());
                vo.setStatesMsg(entity.getStates().getMsg());

                if (entity.getResource() != null){
                    vo.setPid(entity.getResource().getId());
                    vo.setPname(entity.getResource().getName());
                }
            }
            return vo;
        }
    }
}
