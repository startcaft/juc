package com.startcaft.basic.dao.master;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class Md5Test {

    @Test
    public void test() throws Exception {

        String username = "admin";
        String password = "123456";

        String md5Str = new Md5Hash(password).toString();
        System.out.println(md5Str);

        String pwd = new Md5Hash(md5Str,username).toString();
        System.out.println(pwd);

    }
}
