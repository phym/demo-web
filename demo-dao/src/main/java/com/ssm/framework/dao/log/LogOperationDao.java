package com.ssm.framework.dao.log;

import com.ssm.framework.model.log.LogOperator;

/**
 * 日志操作数据类
 * LogOperationDao.
 *
 * @author zax
 */
public interface LogOperationDao {
    /**
     * 
     * 保存日志操作类
     *
     * @param logOperator
     *
     * @author zax
     */
    public void insertLogOperation(LogOperator logOperator);
}
