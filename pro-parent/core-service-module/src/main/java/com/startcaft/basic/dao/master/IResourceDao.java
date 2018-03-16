package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author startcaft
 * Created by startcaft on 2018/3/1.
 */
public interface IResourceDao {

    /**
     * 新增系统资源
     * @param record Resource记录
     * @return
     */
    Integer insert(Resource record);

    /**
     * 查询系统资源相信信息
     * @param id 资源id
     * @return
     */
    Resource selectByPrimaryKey(Long id);

    /**
     * 获取登录用户的所有被授权使用的系统资源
     * @param loginName
     * @return
     */
    Set<Resource> selectByLoginName(String loginName);

    /**
     * 返回唯一的顶层节点，pid=0
     * @return
     */
    Set<Resource> selectRoot();

    /**
     * 根据pid查询下一级菜单
     * @return
     */
    Set<Resource> selectByPid(Long pid);

    /**
     * 查询指定节点，指定用户被授权的二级菜单
     * @param parentId 指定节点id，一般认为是一个父节点id
     * @param loginName 登录用户名
     * @return
     */
    Set<Resource> selectSecondLevelMenus(@Param("pid") Long parentId, @Param("loginName") String loginName);

    /**
     * 获取资源树
     * @return
     */
    Resource getTree();

    /**
     * 根据 资源名称 过滤
     * @param resName
     * @return
     */
    Set<Resource> selectByName(String resName);
}
