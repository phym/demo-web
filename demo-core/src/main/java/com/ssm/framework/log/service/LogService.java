package com.ssm.framework.log.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 
 * 日志记录操作
 * 
 * LogService.
 *
 * @author zax
 */
@Component
@Aspect
public class LogService {
    private static Logger logger = LoggerFactory.getLogger(LogService.class);

    @Around("execution(* com.ssm.framework.service.*.*.*(..))")
    public Object aroundLogAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        logger.debug(
            "Beginning method : " + joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName() + "()");
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

        logger.debug("Paramter(s) : " + parameter, args);

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
            if (null != result) {
                logger.debug("Result : [" + result.toString() + "]");
            }
            logger.debug("Ending method : " + joinPoint.getTarget().getClass() + "."
                    + joinPoint.getSignature().getName() + "()");

            long time = System.currentTimeMillis() - startTime;
            logger.debug("Method invocation time : " + time + " ms.");
        }
    }

    @AfterThrowing("execution(* com.ssm.framework.service.*.*.*(..)) && args(ex)")
    public void afterThrowsAdvice(JoinPoint joinPoint, Exception ex) {
        String errorInfo = joinPoint.getTarget().getClass() + "." + joinPoint.getSignature().getName() + "()" + "有误";
        System.out.println("======error====================");
        logger.error(errorInfo);
        StackTraceElement[] st = ex.getStackTrace();
        for (int i = 0; i < st.length; i++) {
            logger.error(i == 0 ? st[i].toString() : "            at " + st[i].toString());
        }
        logger.error(ex.getLocalizedMessage());
    }
}
