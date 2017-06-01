package com.ssm.framework.model.enu.user;

/**
 * 用户性别
 * UserSexEnum.
 *
 * @author zax
 */
public enum UserSexEnum {
        MALE(0, "男"), FEMALE(1, "女");
    private int id;
    private String name;

    public static UserSexEnum getEnum(Integer id) {
        for (UserSexEnum c : UserSexEnum.values()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    
    private UserSexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
