package com.ssm.framework.service.impl.user;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssm.framework.dao.user.UserDao;
import com.ssm.framework.model.user.User;
import com.ssm.framework.service.user.UserService;
import com.ssm.framework.utils.ConstantDef;
import com.ssm.framework.utils.ConstantDef.UUID_LENGTH;
import com.ssm.framework.utils.CryptUtils;
import com.ssm.framework.utils.Identities;

/**
 * 用户实现类
 * UserServiceImpl.
 *
 * @author zax
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;
    
//    @Autowired
//    private TestBatchDao testBatchDao;
    
    /**
     * 
     * 用户登录
     *
     * @param user
     * @return
     * @see com.ssm.framework.service.user.UserService#login(com.ssm.framework.model.user.User)
     *
     * @author zax
     */
    @Override
    public User login(User user) {
//        testBatchDao.batchSave();
        String newLoginPwd = CryptUtils.getInstance(CryptUtils.MD5).encrypt(user.getLoginPwd(), user.getLoginName(),5);
        user.setLoginPwd(newLoginPwd);
        return userDao.login(user);
    }

    @Override
    public User queryUserByLoginName(String loginName) {
        return userDao.queryUserByLoginName(loginName);
    }
    
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    @Override
    public String registerUser(User user) {
        String registerFlag = ConstantDef.SUCCESS_FAIL.FAIL;
        String loginName = user.getLoginName();
        if(StringUtils.isNotBlank(loginName)){
            User extUser = this.queryUserByLoginName(loginName);
            if(null == extUser){
                String loginPwd = user.getLoginPwd();
                String newLoginPwd = CryptUtils.getInstance(CryptUtils.MD5).encrypt(loginPwd,loginName,5);
                user.setLoginPwd(newLoginPwd);
                user.setUserNo(Identities.randomBase62(UUID_LENGTH.THIRTY_TWO));
                this.addUser(user);
                registerFlag = ConstantDef.SUCCESS_FAIL.SUCCESS;
            }
        }
        return registerFlag;
    }
}
