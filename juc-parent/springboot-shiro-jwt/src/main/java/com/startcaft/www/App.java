package com.startcaft.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author startcaft
 *
 * /login	登入(所有人都可以访问)
 * /article	所有人都可以访问，但是用户与游客看到的内容不同
 * /require_auth	登入的用户才可以进行访问
 * /require_role	admin的角色用户才可以登入
 * /require_permission	拥有view和edit权限的用户才可以访问
 */
@SpringBootApplication
public class App {

    public static void main(String[] args){
        new SpringApplication(App.class).run(args);
    }
}
