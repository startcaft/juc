package com.startcaft.basic.service;

import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.vo.UserVo;
import org.apache.shiro.authc.AuthenticationException;

import java.util.Optional;
import java.util.Set;

/**
 *
 * @author startcaft
 * @date 2018/3/5
 */
public interface IUserService {

//    /**获取一个用户分页列表**/
//    Set<UserVo> searchUserPage(UserQuery query) throws Exception;

//    /**保存一个用户**/
//    void saveUser(UserVo user) throws Exception;

//    /**删除一个用户，假删除，将状态改为停用**/
//    void lockUser(Long id) throws Exception;

//    /**启用一个用户**/
//    void enableUser(Long id) throws Exception;

//    /**更新一个用户**/
//    void editUser(UserVo user) throws Exception;

//    /**根据id获取用户信息**/
//    Optional<UserVo>  searchSingleUser(Long id) throws Exception;

    /**
     * 用户登录
     * @param loginName 登录名
     * @param password 密码(MD5加密，用户名当作slat)
     * @return
     * @throws BasicProException
     */
    UserVo  userLogin(String loginName, String password) throws BasicProException;

    /**
     * 根据用户名查询
     * @param loginName 用户名，具有唯一性
     * @return
     * @throws BasicProException
     */
    Optional<UserVo> searchUserByLoginName(String loginName) throws BasicProException;

//    /**根据用户id获取对应的所有资源列表**/
//    Set<String> resourceList(Long id) throws Exception;

//    /**更新用户密码**/
//    void editUserPwd(Long id, String oldPwd, String pwd) throws Exception;


}
