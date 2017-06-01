package com.ssm.framework.model.enu.common;

/**
 * 是否有效
 * 
 * IsValidEnum.
 *
 * @author zax
 */
public enum IsValidEnum {
        YES(0, "无效"), NO(1, "有效");
    private int id;
    private String name;

    private IsValidEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static IsValidEnum getEnum(Integer id) {
        for (IsValidEnum c : IsValidEnum.values()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
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
