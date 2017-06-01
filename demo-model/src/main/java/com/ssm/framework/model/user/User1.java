package com.ssm.framework.model.user;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class User1 implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public UserExt getUserExt() {
        return this.userExt;
    }
    public void setUserExt(UserExt userExt) {
        this.userExt = userExt;
    }
    private String name;
    private String sex;
    private int age;
    private UserExt userExt;
}
