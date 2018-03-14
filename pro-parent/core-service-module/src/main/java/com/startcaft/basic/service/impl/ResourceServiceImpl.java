package com.startcaft.basic.service.impl;

import com.startcaft.basic.core.entity.Resource;
import com.startcaft.basic.core.enums.States;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.exceptions.FieldNullException;
import com.startcaft.basic.core.exceptions.ParentNodeException;
import com.startcaft.basic.core.vo.ResourceVo;
import com.startcaft.basic.dao.master.IResourceDao;
import com.startcaft.basic.service.IResourceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
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
    private IResourceDao resourceDao;

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

    @Override
    public Set<ResourceVo> getFirstLevelMenus() throws BasicProException {
        {
            // 先获取顶层节点，
            ResourceVo rootVo = this.getTopParent();

            // 返回查询顶层节点下面的所有的一级菜单，这些菜单不受权限控制
            Set<Resource> resourceSet = resourceDao.selectByPid(rootVo.getId());
            Set<ResourceVo> voSet = new HashSet<>(resourceSet.size());
            resourceSet.forEach((entity) -> {
                voSet.add(this.cycleCopyProperties(entity));
            });
            return voSet;
        }
    }

    @Override
    public ResourceVo getTopParent() throws BasicProException {
        Set<Resource> set = resourceDao.selectRoot();
        if (set.isEmpty()){
            throw new ParentNodeException("root node is null");
        }
        if (!set.isEmpty() && set.size() > 1){
            throw new ParentNodeException("root is not only,and the root node's pid is only equals 0");
        }
        Resource[] resources = new Resource[1];
        Resource root = set.toArray(resources)[0];

        try {
            return root.copyPropertiesTemplate(new ResourceVo());
        } catch (IllegalAccessException e) {
            throw new BasicProException(e);
        } catch (NoSuchMethodException e) {
            throw new BasicProException(e);
        } catch (InvocationTargetException e) {
            throw new BasicProException(e);
        }
    }

    @Override
    public Set<ResourceVo> getSecondLevelMenusByRoot(Long parentId, String loginName) throws BasicProException {
        {
            if (parentId == null){
                throw new FieldNullException("rootId is null");
            }
            if (StringUtils.isEmpty(loginName)){
                throw new FieldNullException("loginName is null");
            }
            // 查询数据库
            Set<Resource> set = resourceDao.selectSecondLevelMenus(parentId,loginName);
            Set<ResourceVo> voSet = new HashSet<>(set.size());
            set.forEach((res) -> {
                voSet.add(this.cycleCopyProperties(res));
            });

            return voSet;
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
