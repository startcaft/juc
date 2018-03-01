package com.startcaft.www.shiro;

import com.startcaft.www.model.User;
import com.startcaft.www.service.UserService;
import com.startcaft.www.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author startcaft
 * Created by startcaft on 2018/2/28.
 */
public class JwtRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRealm.class);

    @Autowired
    private UserService userService;

    /**
     * 注意：要重写该方法，用于判断 Token 的类型
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 授权，只有当检测用户权限时才会调用此方法，例如 checkRole 之类的方法
     * 最好使用缓存，每次授权都到缓存中查找
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 用户认证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String jwts = (String) token.getCredentials();

        // 解密获取 username，用于和数据库进行比对
        String username = JwtUtil.getUsername(jwts);
        if (username == null){
            throw new AuthenticationException("token invalid");
        }

        User user = userService.getUser(username);
        if (user == null){
            throw new AuthenticationException("User didn't existed!");
        }

        if (!JwtUtil.verify(jwts,username,user.getPassword())){
            throw new AuthenticationException("Username or password error!");
        }

        return new SimpleAuthenticationInfo(jwts,jwts,getName());
    }
}
