package com.startcaft.basic.service.impl;

import com.startcaft.basic.core.beans.ResourceBean;
import com.startcaft.basic.core.entity.Resource;
import com.startcaft.basic.core.enums.States;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.exceptions.FieldNullException;
import com.startcaft.basic.core.exceptions.ParentNodeException;
import com.startcaft.basic.core.exceptions.SqlExecuteException;
import com.startcaft.basic.core.sorts.ResourceVoComparator;
import com.startcaft.basic.core.vo.ResourceVo;
import com.startcaft.basic.dao.master.IResourceDao;
import com.startcaft.basic.service.IResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * @author startcaft
 * @date 2018/3/1
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private IResourceDao resourceDao;

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
                voSet.add(r.copyPropertiesTemplate(new ResourceVo()));
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
            Set<ResourceVo> voSet = new TreeSet<>(new ResourceVoComparator());
            resourceSet.forEach((entity) -> {
                voSet.add(entity.copyPropertiesTemplate(new ResourceVo()));
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

        return root.copyPropertiesTemplate(new ResourceVo());
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
            Set<ResourceVo> voSet = new TreeSet<>(new ResourceVoComparator());
            set.forEach((res) -> {
                voSet.add(res.copyPropertiesTemplate(new ResourceVo()));
            });

            return voSet;
        }
    }

    @Override
    public Resource getResTree() throws BasicProException {
        {
            Resource node = resourceDao.getTree();
            return node;
        }
    }

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void AddResource(ResourceBean bean) throws BasicProException {
        {
            if (bean == null){
                throw new BasicProException(new NullPointerException("ResourceVo is null"));
            }
            // 资源名称不能为空
            if (StringUtils.isEmpty(bean.getName())){
                throw new FieldNullException("resource name is null");
            }
            // 资源类型不能为空，通过code可以成一个States枚举对象
            if (StringUtils.isEmpty(bean.getStatesCode())){
                throw new FieldNullException("resource type is null");
            }
            // 无需确保资源状态的正确性，States枚举在转换时，如果没有无法转换，则转换为States.NORMAL
            // 确保资源名称不重复
            Set<Resource> resourceSet = resourceDao.selectByName(bean.getName());
            if (resourceSet != null && resourceSet.size() > 0){
                throw new BasicProException("资源名[" + bean.getName() + "]已经存在");
            }
            // 确保只有一个根节点
            if (bean.getPid() == 0){
                throw new BasicProException("系统资源只能有一个pid=0的根节点");
            }

            Resource resource = new Resource();
            bean.copyPropertiesTemplate(resource);
            // 默认的状态和默认的时间
            if (bean.getCreateDatetime() == null){
                resource.setCreateDatetime(new Date());
            }
            if (bean.getStatesCode() == 0){
                resource.setStates(States.NORMAL);
            }

            int result = resourceDao.insert(resource);
            if (result != 1){
                throw new SqlExecuteException("execute insert result is error");
            }
        }
    }
}
