package com.daym;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy <T>implements InvocationHandler
{
    //　这个就是我们要代理的真实对象
    private T subject;

    //    构造方法，给我们要代理的真实对象赋初值
    public DynamicProxy(T subject)
    {
        this.subject = subject;
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable
    {
        //　　在代理真实对象前我们可以添加一些自己的操作
        System.out.println("|method:"+method+"|args:"+args);
   //     System.out.println("proxy:"+proxy);
        //    当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(subject, args);
        //相比静态代理方式 subject和method、args都是动态的匹配的

        //　　在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("subject"+subject);

        return null;
    }

}