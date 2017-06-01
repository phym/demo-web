package com.ssm.framework.dao.user;

import com.ssm.framework.model.user.User;

/**
 * 用户Dao
 * UserDao.
 *
 * @author zax
 */
public interface UserDao {
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
     * 通过登录名获取用户对象
     *
     * @param loginName 登录名称
     * @return User
     *
     * @author zax
     */
    public User queryUserByLoginName(String loginName);
    
    /**
     * 
     * 新增用户
     *
     * @param user 用户对象
     * @return String
     *
     * @author zax
     */
    public void addUser(User user);
}
