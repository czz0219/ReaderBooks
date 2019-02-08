package com.controller;

import com.utils.Utils1;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;

@Controller
public class testmvc {
    @RequestMapping(value = "/gt",method = RequestMethod.GET)
    @ResponseBody
    public  String getUtils(){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/META-INF/applicationContext.xml");
        /**
         * 面向接口编程
         */
        DataSource dataSource = (DataSource)context.getBean("dataSource");
        System.out.println(dataSource);
        return  Utils1.sayTools();
    }

}
