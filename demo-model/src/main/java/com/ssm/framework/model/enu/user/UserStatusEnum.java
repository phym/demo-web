package com.ssm.framework.model.enu.user;

/**
 * 用户状态
 * 
 * UserStatusEnum.
 *
 * @author zax
 */
public enum UserStatusEnum {
    NEW(0, "新增"),AUTHORIZATION_SUCCESS(1,"认证通过"),AUTHORIZATION_FAIL(0,"认证失败");
    private int id;
    private String name;
    
    public static UserStatusEnum getEnum(Integer id) {
        for (UserStatusEnum c : UserStatusEnum.values()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
    private UserStatusEnum(int id, String name) {
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
