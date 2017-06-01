package com.ssm.framework.model.user;

import java.io.Serializable;

public class UserExt implements Serializable {
    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private String name;
    private String age;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
