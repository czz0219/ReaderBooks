package com.life.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


public class JuiceMaker2 implements BeanNameAware ,BeanFactoryAware,ApplicationContextAware,InitializingBean {
    private String beverageShop = null;
    private Source source = null;
    public String getBeverageShop() {
        return beverageShop;
    }

    public void setBeverageShop(String beverageShop) {
        this.beverageShop = beverageShop;
    }
    public Source getSource() {
        return this.source;
    }
    public void setSource(Source source) {
        this.source = source;
    }

    public void init(){
        System.out.println("["+this.getClass().getSimpleName()+"]执行自定义初始化方法");
    }
    public void destroy(){
        System.out.println("["+this.getClass().getSimpleName()+"]执行自定义销毁方法");
    }
    public String makeJuice(){
        String juice ="这是一杯由"+beverageShop+"饮品店，提供的"+source.getSize()+source.getSugar()+source.getFruit();
        return juice;
    }

    public void setBeanName(String s) {
        System.out.println("["+this.getClass().getSimpleName()+"]调用BeanNameAware接口的setBeanName方法");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("["+this.getClass().getSimpleName()+"]调用BeanFactoryAware接口的 setBeanFactory方法");
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("["+this.getClass().getSimpleName()+"]调用ApplicationContextAware的setApplicationContext方法");
    }

    public void afterPropertiesSet() {
        System.out.println("["+this.getClass().getSimpleName()+"]调用InitializingBean接口的afterPropertiesSet方法");
    }
}