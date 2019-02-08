package com.life.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class BeanPostProcessorImpl implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("["+o.getClass().getSimpleName()+"]类的实例"+s+"开始实例化");
        return o;
    }

    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("["+o.getClass().getSimpleName()+"]类的实例"+s+"实例化完成");
        return o;
    }
}
