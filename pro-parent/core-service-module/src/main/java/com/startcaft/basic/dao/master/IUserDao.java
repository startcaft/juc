package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.User;
import javafx.scene.effect.SepiaTone;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author startcaft
 * @date 2018/3/5
 */
public interface IUserDao {

    /**
     * 添加系统用户
     * @param record User实例
     * @return
     */
    Integer insert(User record);

    /**
     * 更新用户信息
     * @param record User实例，只更新不为空的属性
     * @return
     */
    Integer updateByPrimaryKeySelective(User record);

    /**
     * 查询用户详细
     * @param id User主键ID
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 根据登录名查询唯一用户
     * @param loginName 唯一用户名
     * @return
     */
    User selectByLoginName(String loginName);

    /**
     * 分页查询，两个必须的参数pageNo和pageSize
     * @param map 参数Map
     * @return
     */
    Set<User> selectUserPage(Map<String, Object> map);

    /**
     * 获取指定用户ID的所有权限列表
     * @param userId User主键ID
     * @return
     */
    User selectUserByIdWithRoles(Long userId);
}
