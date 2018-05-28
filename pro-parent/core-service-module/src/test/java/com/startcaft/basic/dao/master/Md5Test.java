package com.startcaft.basic.dao.master;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class Md5Test {

    @Test
    public void test() throws Exception {

        String username = "admin";
        String password = "123456";

        String pwdMd5 = new Md5Hash(password).toString();
        System.out.println("密码MD5加密：" + pwdMd5);

        String pwdMd5Salt = new Md5Hash(password,username).toString();
        System.out.println("密码MD5加密加盐(username)：" + pwdMd5Salt);

        String pwd = new Md5Hash(pwdMd5Salt,username).toString();
        System.out.println("密码MD5加密加盐(username)再加盐(username)：" + pwd);

    }
}
