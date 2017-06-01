package com.ssm.framework.model.user;

import java.io.Serializable;
import java.util.Date;

import com.ssm.framework.model.enu.common.IsValidEnum;
import com.ssm.framework.model.enu.user.UserSexEnum;
import com.ssm.framework.model.enu.user.UserStatusEnum;

/**
 * 用户
 * User.
 *
 * @author zax
 */
public class User implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    private int id;//唯一标识
    private String userNo; //业务编号
    private String name;// 名称
    private int age;// 年龄
    private UserSexEnum sex;// 性别
    private String loginName;// 登录名
    private String loginPwd;// 登录密码
    private Date registerTime;//注册时间
    private Date modifyTime;//修改时间
    private IsValidEnum isValid;//是否有效
    private UserStatusEnum status;//账号
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserNo() {
        return this.userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserSexEnum getSex() {
        return this.sex;
    }

    public void setSex(UserSexEnum sex) {
        this.sex = sex;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return this.loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Date getRegisterTime() {
        return this.registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getModifyTime() {
        return this.modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public IsValidEnum getIsValid() {
        return this.isValid;
    }

    public void setIsValid(IsValidEnum isValid) {
        this.isValid = isValid;
    }

    public UserStatusEnum getStatus() {
        return this.status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
    }
}
