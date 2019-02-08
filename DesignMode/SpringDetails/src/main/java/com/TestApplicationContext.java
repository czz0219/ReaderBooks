package com;

import com.setter.colection.ComplexAssembly;
import com.constructor.Role;
import com.life.cycle.JuiceMaker2;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApplicationContext {
    public static void main(String[] args){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app.xml");
        System.out.println("getResource");
        JuiceMaker2 juiceMaker2 =(JuiceMaker2)ctx.getBean("juiceMaker2");
        Role role1 = (Role)ctx.getBean("role1");
        Role role2 = (Role)ctx.getBean("role2");
        ComplexAssembly complexAssembly = (ComplexAssembly)ctx.getBean("complexAssembly");
        System.out.println("complexAssembly:"+complexAssembly);
        System.out.println("Juice:"+juiceMaker2.makeJuice()+" Role:"+role1+"\n:"+role2);
        ctx.close();
    }
}
