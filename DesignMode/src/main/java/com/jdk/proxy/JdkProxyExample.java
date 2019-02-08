package com.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class JdkProxyExample implements InvocationHandler {
	//目标对象
	private Object target = null;
	//生成代理对象实例.它会关联target
	public Object bind(Object target) {
		this.target = target;
		/*
		 * @param1 类加载器
		 * @param2 把生成的动态代理对象挂在那些接口下.
		 * @param3 代理类，必须实现InvocationHandler
		 * */
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this );
	}
	/*
	 * @param arg0 代理对象
	 * @param arg1 当前调度方法
	 * @param Object[] 当前方法参数
	 * @param Object 代理结果返回
	 * */
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("进入代理逻辑方法,在调度真实对象之前的服务");
		Object obj = arg1.invoke(target, arg2);//调用 target的方法
		System.out.println("调用真实对象值后服务");
		return obj;
	}
	@Test
	public void testJdkProxy() {
		JdkProxyExample jdk = new JdkProxyExample();
		//声明、并实例化 代理对象 interface是HelloWorld
		HelloWorld proxy =(HelloWorld)jdk.bind(new HelloWorldImpl());
		/*
		 * 一、target 和 proxy(代理对象)都应该有共同的一个接口;
		 * 二、proxy调用 interface的方法时，会自动将 proxy.invoke()所需要的参数分析出来，并自动传递给
		 * proxy.invoke()然后 调用proxy.invoke();
		 * */
		proxy.sayHelloWorld();
	}

}
