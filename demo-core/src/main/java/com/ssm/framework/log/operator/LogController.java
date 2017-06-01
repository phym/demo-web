package com.ssm.framework.log.operator;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ssm.framework.log.annotation.LogDescription;
import com.ssm.framework.utils.file.FileUtils;

/**
 * 
 * 用户操作记录
 * 
 * LogController.
 *
 * @author zax
 */
@Component
@Aspect
public class LogController {
    /** 日志文件 **/
    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    @Around("execution(*  com.ssm.framework.controller.*.*.*(..))")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        StringBuffer parameter = new StringBuffer();
        for (int i = 0; i < args.length; i++) {
            if (0 < i) {
                if (null != args[i]) {
                    parameter.append(" ; parameter(").append(i).append(")={}").append(args[i].toString());
                } else {
                    parameter.append(" ; parameter(").append(i).append(")=null");
                }
            } else {
                if (null != args[i]) {
                    parameter.append("parameter(").append(i).append(")={}").append(args[i].toString());
                } else {
                    parameter.append("parameter(").append(i).append(")=null");
                }
            }
        }
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = joinPoint.proceed();
            return result;
        } catch (Exception e) {
            logger
                .error(joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName() + "() invoke error");
            logger.error("error info [" + e.getMessage() + "]");
            throw e;
        } finally {
//            SystemCustomerPrincipal systemCustomerPrincipal = SystemSessionManager.getContextInstance().getLoginInfo();// 登录用户信息对象
            long time = System.currentTimeMillis() - startTime;
            String logCentent = "Beginning method : " + joinPoint.getTarget().getClass() + "."
                    + joinPoint.getSignature().getName() + "(). parameters:" + parameter.toString() + " processTime :"
                    + time;
//            LogOperator logOperator = new LogOperator();
            String name = "其他系统用户，需要填该系统用户对象信息";
//            if (null != systemCustomerPrincipal.getAccout()) {
//                name = systemCustomerPrincipal.getAccout().getName();
//            }
            FileUtils.writeStringToFile(new File("E://aa"), "stringgggggg");
//            logOperator.setName(name);
//            logOperator.setOperationCentent(getServiceMthodDescription(joinPoint)+ "====" + logCentent);
//            logOperator.setOperationIP(SystemManager.getIP());
//            System.out.println(logOperator.toString());
        }
    }

    public static String getServiceMthodDescription(ProceedingJoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class<?> targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class<?>[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(LogDescription.class).value();
                    break;
                }
            }
        }
        return description;
    }

    @AfterThrowing("execution(* com.ssm.framework.controller.*.*(..)) && args(ex)")
    public void afterThrows(JoinPoint joinPoint, Exception ex) {
        String errorInfo = joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName() + "()" + "有误";
        logger.error(errorInfo);
        StackTraceElement[] st = ex.getStackTrace();
        for (int i = 0; i < st.length; i++) {
            logger.error(i == 0 ? st[i].toString() : "            at " + st[i].toString());
        }
        logger.error(ex.getLocalizedMessage());
    }

    public static void main(String[] args) {
        try {
            FileUtils.writeStringToFile(new File("E://aa.txt"), "stringgggggg111");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            logger.error(e.getLocalizedMessage());
        }
    }
}
