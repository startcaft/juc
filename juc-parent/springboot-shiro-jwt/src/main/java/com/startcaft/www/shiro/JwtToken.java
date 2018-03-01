package com.startcaft.www.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author startcaft
 * Created by startcaft on 2018/2/28.
 * JWT Token
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
