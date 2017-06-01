package com.ssm.framework.property;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 获取加载配置文件
 * CustomizedPropertyPlaceholderConfigurer.
 *
 * @author zax
 */
public class CustomizedPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer{

    private static Map<String, String> ctx_props = new HashMap<String, String>();
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
        throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet())
        {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctx_props.put(keyStr, value);
        }
    }
    
    public static String getContextProperty(String name)
    {
        return ctx_props.get(name);
    }
}
