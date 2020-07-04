package com.dxp.netty.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

/**
 * springContext工具包
 *
 * @author carzy
 * @date 2020/6/29
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> t){
        return applicationContext.getBean(t);
    }

    public static <T> T getBean(String name, Class<T> t){
        return applicationContext.getBean(name, t);
    }
}
