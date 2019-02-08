package com.dao;
import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConnectJDBC {
    @Test
    public void testDataSource(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        /**
         * 面向接口编程
         */
        DataSource dataSource = (DataSource)context.getBean("dataSource");
        System.out.println(dataSource);
    }
}
