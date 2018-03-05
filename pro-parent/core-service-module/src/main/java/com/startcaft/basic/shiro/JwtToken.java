package com.startcaft.basic.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Jwt版本的 AuthenticationToken
 * @author startcaft
 * @date 2018/3/5
 */
public class JwtToken implements AuthenticationToken {

    /**
     * 密钥
     */
    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
