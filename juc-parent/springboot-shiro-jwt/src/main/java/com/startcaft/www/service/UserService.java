package com.startcaft.www.service;

import com.startcaft.www.model.User;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author startcaft
 * Created by startcaft on 2018/2/28.
 */
@Service
public class UserService {

    private Map<String,User> users;

    public UserService() {
        users = new HashMap<>();
        User user = new User();
        user.setUsername("smith");
        user.setPassword("smith123");
        Set<String> roles = new HashSet<>();
        roles.add("user");
        user.setRoles(roles);
        Set<String> permissions = new HashSet<>();
        permissions.add("view");
        user.setPermissions(permissions);
        users.put("smith",user);


        user = new User();
        user.setUsername("danny");
        user.setPassword("danny123");
        roles = new HashSet<>();
        roles.add("admin");
        user.setRoles(roles);
        permissions = new HashSet<>();
        permissions.add("view");
        permissions.add("edit");
        user.setPermissions(permissions);
        users.put("danny",user);
    }

    public User getUser(String username){
        if (!this.users.containsKey(username)){
            return null;
        }

        User user = this.users.get(username);
        return user;
    }
}
