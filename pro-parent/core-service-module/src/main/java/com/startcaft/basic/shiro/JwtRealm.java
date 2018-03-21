package com.startcaft.basic.shiro;

import com.startcaft.basic.core.vo.ResourceVo;
import com.startcaft.basic.core.vo.UserVo;
import com.startcaft.basic.service.IResourceService;
import com.startcaft.basic.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author startcaft
 * @date 2018/3/5
 */
public class JwtRealm extends AuthorizingRealm  {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtRealm.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IResourceService resourceService;

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
            UserVo vo = user.orElseThrow(DisabledAccountException::new);

            if (!JwtUtil.verify(jwtToken,vo.getLoginName(),vo.getPassword())){
                throw new AuthenticationException("password error!");
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
        {
            LOGGER.info(principals.toString());
            LOGGER.info(principals.getPrimaryPrincipal().toString());

            String username = JwtUtil.getUsername(principals.getPrimaryPrincipal().toString());
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

            try {
                //添加权限，注意这里的权限不能有空值或者null，不然在验证权限的时候会报错
                Set<ResourceVo> voSet = resourceService.getUserRoleResources(username);

                // 把 可用资源的 url 当成权限字符串
                Set<String> permissions = new HashSet<>();
                voSet.forEach((r) -> {
                    permissions.add(r.getUrl());
                });
                authorizationInfo.addStringPermissions(permissions);

                //添加角色
                //authorizationInfo.setRoles(roleService.getResourcesByUser(loginname));
            } catch (Exception e) {
                LOGGER.error("用户授权失败",e);
            }

            return authorizationInfo;
        }
    }
}
