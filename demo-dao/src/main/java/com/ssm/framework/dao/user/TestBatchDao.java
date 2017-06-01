package com.ssm.framework.dao.user;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssm.framework.model.user.User;

public class TestBatchDao{
    
    private SqlSessionTemplate sqlSessionTemplate;
    
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }



    public void batchSave(){
       SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
       User account = new User();
       Long bengin = System.currentTimeMillis();
       System.out.println("执行批量插入开始时间：" + bengin);
       for(int i = 9999; i < 300000; i++){
           account.setLoginName("loginName"+i);
           account.setLoginPwd("loginPwd"+i);
           session.insert("com.ssm.framework.dao.user.UserDao.addUser",account);
       }
       Long end = System.currentTimeMillis();
       session.commit();
       session.close();
       System.out.println("执行批量插入结束时间：" + end);
       System.out.println("执行时间差：" + (end - bengin));
    };
}
