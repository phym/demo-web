package com.ssm.framework.sys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * 
 * 只是为了区分（BeanFactoryPostProcessor(比BeanPostProcessor先执行，可以修改bean内容)、BeanPostProcessor(前后通知，不能改变Bean内容)）
 * 
 * ShiroEventListener.
 *
 * @author zax
 */
public class ShiroEventListener implements BeanFactoryPostProcessor, BeanPostProcessor{

    /** logger */
    private static Logger logger = LoggerFactory.getLogger(ShiroEventListener.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.info("==================BeanFactoryPostProcessor:BeanFactoryPostProcessor======================");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("==================BeanPostProcessor:postProcessBeforeInitialization=====================");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        logger.info("==================BeanPostProcessor:postProcessAfterInitialization=====================");
        return bean;
    }
}
