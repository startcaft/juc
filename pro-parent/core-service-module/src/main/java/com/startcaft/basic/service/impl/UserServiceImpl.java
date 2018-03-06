package com.startcaft.basic.service.impl;

import com.startcaft.basic.core.entity.User;
import com.startcaft.basic.core.enums.States;
import com.startcaft.basic.core.exceptions.BasicProException;
import com.startcaft.basic.core.exceptions.FieldNullException;
import com.startcaft.basic.core.exceptions.LoginException;
import com.startcaft.basic.core.vo.UserVo;
import com.startcaft.basic.dao.master.IUserDao;
import com.startcaft.basic.service.IUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;


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
}
