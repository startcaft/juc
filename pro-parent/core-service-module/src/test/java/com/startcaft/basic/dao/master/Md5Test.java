package com.startcaft.basic.dao.master;

import com.startcaft.basic.core.entity.Resource;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

public class Md5Test {

    @Test
    public void test() throws Exception {

        String username = "admin";
        String password = "admin";

        String md5Str = new Md5Hash(password,username).toString();
        System.out.println(md5Str);
    }
}
