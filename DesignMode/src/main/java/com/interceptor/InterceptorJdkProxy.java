package com.interceptor;

import com.jdk.proxy.HelloWorld;
import com.jdk.proxy.HelloWorldImpl;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/*拦截器=spring中的切面,就是在target方法 之前、值后、之中(异常或出错)执行的代码段
* proxy代理类 负责 将拦截器(spring中对应切面)插入target真实对象的前、后、中(spring中对应切点);
*
* */
public class InterceptorJdkProxy implements InvocationHandler {
    private Object target;
    private  String interceptorClass = null;//连接器全限定名

    public  InterceptorJdkProxy(Object target, String interceptorClass){
        this.target = target;
        this.interceptorClass =interceptorClass;
    }
    //绑定委托对象并返回一个代理
    public static Object bind(Object target,String interceptorClass){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),new InterceptorJdkProxy(target,interceptorClass));
    }
    public Object invoke(Object proxy, Method method,Object[] args)throws Throwable{
        if (interceptorClass == null){
            return method.invoke(target,args);
        }
        Object result = null;
        /*生成拦截器，
        拦截器:仅仅是一段普通代码而已，只是会通过代理方法invoke()/intercept() 插入到切点（target的原始方法之前，之后或中间）执行，*/
        Interceptor interceptor =(Interceptor)Class.forName(interceptorClass).newInstance();
        if (interceptor.before(proxy,target,method,args)){
            result = method.invoke(target,args);
        }else {
            interceptor.around(proxy,target,method,args);
        }
        interceptor.after(proxy,target,method,args);
        return result;
    }
    //为 HelloWorldImpl对象设置拦截器
    public static void  main(String[] args){
        /*通过 InterceptorJdkProxy 实现对HelloWorldImpl对象的拦截
            所以代理类是实现拦截器拦截的关键,他实现了拦截的步骤和细节
        */
        HelloWorld proxy = (HelloWorld) InterceptorJdkProxy.bind(new HelloWorldImpl(),"com.interceptor.MyInterceptor");
        proxy.sayHelloWorld();
    }
}
