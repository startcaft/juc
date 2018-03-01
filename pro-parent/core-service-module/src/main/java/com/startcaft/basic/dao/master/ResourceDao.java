package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author startcaft
 * Created by startcaft on 2018/3/1.
 */
public interface ResourceDao {

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
     * 查询所有顶层节点
     * @return
     */
    Set<Resource> selectRoots();

    /**
     * 查询指定顶层节点，指定用户被授权的二级菜单
     * @param rootId 顶层节点
     * @param loginName 登录用户名
     * @return
     */
    Set<Resource> selectSecondLevelMenus(@Param("pid") Long rootId, @Param("loginName") String loginName);
}
