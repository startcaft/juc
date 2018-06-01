/**
 * Copyright (C), 2015-2018, 武汉东雅图有限公司
 * Author:   StartCaft
 * Date:     2018/6/1 14:55
 * Description: 用户
 */
package member.service.api;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户〉
 *
 * @author StartCaft
 * @create 2018/6/1
 * @since 1.0.0
 */
public class User {
    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(){
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "用户{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
