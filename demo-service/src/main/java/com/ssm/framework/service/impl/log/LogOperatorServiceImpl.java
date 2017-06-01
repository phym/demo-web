package com.ssm.framework.service.impl.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssm.framework.dao.log.LogOperationDao;
import com.ssm.framework.model.log.LogOperator;
import com.ssm.framework.service.log.LogOperatorService;

/**
 * 操作日志记录服务接口
 * LogOperatorService.
 *
 * @author zax
 */
@Service
public class LogOperatorServiceImpl implements LogOperatorService {
    
    @Autowired
    private LogOperationDao logOperationDao;

    @Override
    public void insertLogOperator(LogOperator logOperator) {
        logOperationDao.insertLogOperation(logOperator);
    }
}
