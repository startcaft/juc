package com.startcaft.basic.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.startcaft.basic.core.beans.UserBean;
import com.startcaft.basic.core.beans.UserModifyBean;
import com.startcaft.basic.core.entity.User;
import com.startcaft.basic.core.enums.States;
import com.startcaft.basic.core.exceptions.*;
import com.startcaft.basic.core.vo.EasyuiGrid;
import com.startcaft.basic.core.vo.UserPageRequest;
import com.startcaft.basic.core.vo.UserVo;
import com.startcaft.basic.dao.master.IUserDao;
import com.startcaft.basic.service.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 *
 * @author startcaft
 * @date 2018/3/5
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Transactional(value="masterTransactionManager",readOnly = true,rollbackFor = Exception.class)
    @Override
    public UserVo userLogin(String loginName, String password) throws BasicProException {
        if (StringUtils.isEmpty(loginName)){
            throw new FieldNullException("loginname is null");
        }
        if (StringUtils.isEmpty(password)){
            throw new FieldNullException("password is null");
        }

        // 用户不存在
        User user = userDao.selectByLoginName(loginName);
        if (user == null){
            throw new LoginException("user is not exists");
        }

        // 密码错误
        String md5Pwd = new Md5Hash(password,loginName).toString();
        if (!user.getPassword().equalsIgnoreCase(md5Pwd)){
            throw new LoginException("password is wrong");
        }

        // 账号锁定
        if (user.getStates() == States.LOCKED){
            throw new LoginException("user is locked");
        }

        UserVo vo = new UserVo();
        BeanUtils.copyProperties(user,vo);

        return vo;
    }

    @Override
    public Optional<UserVo> searchUserByLoginName(String loginName) throws BasicProException {
        if (StringUtils.isEmpty(loginName)){
            throw new FieldNullException("loginname is null");
        }

        User user = userDao.selectByLoginName(loginName);
        UserVo vo = new UserVo();
        BeanUtils.copyProperties(user,vo);
        return Optional.ofNullable(vo);
    }

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void editUserPwd(String loginName, String oldPwd, String pwd) throws BasicProException {
        if (StringUtils.isEmpty(loginName)){
            throw new FieldNullException("loginname is null");
        }
        if (StringUtils.isEmpty(oldPwd)){
            throw new FieldNullException("user oldpwd is null");
        }
        if (StringUtils.isEmpty(pwd)){
            throw new FieldNullException("user newpwd is null");
        }

        // 查询用户，以及验证用户提供的原始密码是否正确
        Optional<UserVo> optionalUserVo = this.searchUserByLoginName(loginName);
        UserVo userVo = optionalUserVo.orElseThrow(UserNotFoundException::new);
        String md5Pwd = new Md5Hash(oldPwd,loginName).toString();
        if (!userVo.getPassword().equalsIgnoreCase(md5Pwd)){
            throw new LoginException("password is wrong");
        }

        // 执行更新
        md5Pwd = new Md5Hash(pwd,loginName).toString();
        User user = new User();
        user.setId(userVo.getId());
        user.setLoginName(userVo.getLoginName());
        user.setPassword(md5Pwd);

        Integer result = userDao.updateByPrimaryKeySelective(user);
        if (result != 1){
            throw new SqlExecuteException();
        }
    }

    @Transactional(value="masterTransactionManager",rollbackFor = Exception.class)
    @Override
    public void saveUser(UserBean bean) throws BasicProException {
        {
            // 确保登录名唯一
            User user = userDao.selectByLoginName(bean.getLoginName());
            if (user != null){
                throw new BasicProException("账号：[" + bean.getLoginName() + "]，已经存在");
            }

            String md5Pwd = new Md5Hash(bean.getPassword(),bean.getLoginName()).toString();
            bean.setPassword(md5Pwd);

            Integer result = userDao.insert(bean.copyPropertiesTemplate(new User()));
            if (result != 1){
                throw new SqlExecuteException();
            }
        }
    }

    @Override
    public void modifyUser(UserModifyBean bean) throws BasicProException {
        {
            if (bean.isCheckName()){
                // 确保登录名唯一
                User user = userDao.selectByLoginName(userDao.selectByPrimaryKey(bean.getId()).getLoginName());
                if (user != null){
                    throw new BasicProException("账号：[" + user.getLoginName() + "]，已经存在");
                }
            }

            User entity = new User();
            bean.copyPropertiesTemplate(entity);
            Integer result = userDao.updateByPrimaryKeySelective(entity);
            if (result != 1){
                throw new SqlExecuteException();
            }
        }
    }

    @Override
    public EasyuiGrid<UserVo> getUserPage(UserPageRequest pageRequest) throws BasicProException {
        {
            Map<String,Object> params = new HashMap<>();
            if (pageRequest.getOrgId() != null){
                params.put("orgId",pageRequest.getOrgId());
            }
            if (!StringUtils.isEmpty(pageRequest.getLoginName())){
                params.put("loginName",pageRequest.getLoginName());
            }
            if (!StringUtils.isEmpty(pageRequest.getRealName())){
                params.put("realName",pageRequest.getRealName());
            }

            Page<Object> page = PageHelper.startPage(pageRequest.getPage(),pageRequest.getRows());
            List<User> userSet = userDao.selectUserPage(params);

            List<UserVo> voList = new ArrayList<>(userSet.size());
            if (userSet != null && userSet.size() > 0){
                userSet.forEach((entity) -> {
                    // 将密码置空
                    entity.setPassword(null);
                    voList.add(entity.copyPropertiesTemplate(new UserVo()));
                });
            }

            EasyuiGrid<UserVo> grid = new EasyuiGrid<>(page.getTotal(),voList);
            return grid;
        }
    }

    @Override
    public UserVo searchSingleUser(Long id) throws BasicProException {
        {
            User user = userDao.selectByPrimaryKey(id);
            if (user == null){
                throw new UserNotFoundException("找不到指定id的用户");
            }

            return user.copyPropertiesTemplate(new UserVo());
        }
    }
}
