package com.startcaft.basic.service;

import com.startcaft.basic.core.beans.UserBean;
import com.startcaft.basic.core.beans.UserModifyBean;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.vo.EasyuiGrid;
import com.startcaft.basic.core.vo.UserPageRequest;
import com.startcaft.basic.core.vo.UserVo;

import java.util.Optional;

/**
 *
 * @author startcaft
 * @date 2018/3/5
 */
public interface IUserService {

//    /**获取一个用户分页列表**/
//    Set<UserVo> searchUserPage(UserQuery query) throws Exception;



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

    /**
     * 修改用户密码
     * @param loginName 登录名
     * @param oldPwd    旧密码
     * @param pwd       新密码
     * @throws BasicProException
     */
    void editUserPwd(String loginName, String oldPwd, String pwd) throws BasicProException;

    /**
     * 新增用户
     * @param bean
     * @throws BasicProException
     */
    void saveUser(UserBean bean) throws BasicProException;

    /**
     * 修改用户
     * @param bean
     * @throws BasicProException
     */
    void modifyUser(UserModifyBean bean) throws BasicProException;

    /**
     * 分页查询用户列表
     * @param pageRequest
     * @return
     * @throws BasicProException
     */
    EasyuiGrid<UserVo> getUserPage(UserPageRequest pageRequest) throws BasicProException;
}
