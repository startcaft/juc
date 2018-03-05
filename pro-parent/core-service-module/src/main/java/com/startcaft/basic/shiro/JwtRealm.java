package com.startcaft.basic.shiro;

import com.startcaft.basic.core.vo.UserVo;
import com.startcaft.basic.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 *
 * @author startcaft
 * @date 2018/3/5
 */
public class JwtRealm extends AuthorizingRealm  {

    @Autowired
    private IUserService userService;

    /**
     * 注意：要重写该方法，用于判断 Token 的类型，一定要重写该方法
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        {
            String jwtToken = (String) token.getCredentials();

            // 解密获取 username，用于和数据库进行比对
            String username = JwtUtil.getUsername(jwtToken);

            Optional<UserVo> user = userService.searchUserByLoginName(username);
            user.orElseThrow(DisabledAccountException::new);

            if (!JwtUtil.verify(jwtToken,username,user.get().getPassword())){
                throw new AuthenticationException("Username or password error!");
            }

            return new SimpleAuthenticationInfo(jwtToken,jwtToken,this.getName());
        }
    }

    /**
     * 授权，只有当检测用户权限时才会调用此方法，例如 checkRole 之类的方法
     * 最好使用缓存，每次授权都到缓存中查找
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
