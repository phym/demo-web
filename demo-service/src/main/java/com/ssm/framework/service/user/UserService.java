package com.ssm.framework.service.user;

import com.ssm.framework.model.user.User;

/**
 * 用户Service
 * UserService.
 *
 * @author zax
 */
public interface UserService {
    /**
     * 
     * 用户登录
     *
     * @param user
     * @return User
     *
     * @author zax
     */
    public User login(User user);
    
    /**
     * 
     * 通过登录用户名获取用户对象
     *
     * @param loginName 登录用户名
     * @return User
     *
     * @author zax
     */
    public User queryUserByLoginName(String loginName);
    
    /**
     * 
     * 用户注册
     *
     * @param user 用户对象
     * @return String(FAIL、SUCCESS)
     *
     * @author zax
     */
    public String registerUser(User user);
    
    /**
     * 
     * 新增用户
     *
     * @param user 用户对象
     * @return String 唯一标识
     *
     * @author zax
     */
    public void addUser(User user);
}
