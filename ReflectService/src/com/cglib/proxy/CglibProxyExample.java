package com.cglib.proxy;
import java.lang.reflect.Method;

import org.junit.Test;

import com.reflect.ReflectService;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyExample implements MethodInterceptor{
	/*生成CGLIB代理对象*/
	public Object getProxy(Class cls) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	/*
	 * 代理方法 
	 * 等效于 jdk代理的invoke方法，
	 * */
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("调用真是对象前");
						//方法实例，		代理对象，参数列表       
		Object result = arg3.invokeSuper(arg0, arg2);
		System.out.println("调用真实对象后");
		return result;
	}
	@Test
	public void testCGLIBProxy() {
		CglibProxyExample cpe = new CglibProxyExample();
		ReflectService obj = (ReflectService)cpe.getProxy(ReflectService.class);
		obj.sayHello("张三");
	}
	
}