package com.ssm.framework.model.log;

import java.sql.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 日志操作记录类
 * LogOperation.
 *
 * @author zax
 */
public class LogOperator {
    private int id;// 主键
    @NotBlank(message="{validator.message.notNull}")
    private String name;// 操作人名称
    @NotBlank(message="{validator.message.notNull}")
    private String operationIP;// 操作人IP地址
    @NotBlank(message="{validator.message.notNull}")
    private String operationCentent;// 操作内容
    @NotBlank(message="{validator.message.notNull}")
    private String operation;// 操作信息
    private Date createTime;// 操作时间

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

    public String getOperationIP() {
        return this.operationIP;
    }

    public void setOperationIP(String operationIP) {
        this.operationIP = operationIP;
    }

    public String getOperationCentent() {
        return this.operationCentent;
    }

    public void setOperationCentent(String operationCentent) {
        this.operationCentent = operationCentent;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    @Override
    public String toString() {
        return "name:" +getName() + "===== operationIp:" + getOperationIP() + "-===== operationCentent:" + getOperationCentent()+"====== operation:" + getOperation();
    }
}
