package com.ssm.framework.service.log;

import com.ssm.framework.model.log.LogOperator;

/**
 * 操作日志记录服务接口
 * LogOperatorService.
 *
 * @author zax
 */
public interface LogOperatorService {
    /**
     * 
     * 新增操作日志记录方法
     *
     * @param logOperator
     *
     * @author zax
     */
    public void insertLogOperator(LogOperator logOperator);
}
