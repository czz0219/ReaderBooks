package com.cglib.proxy;
import java.lang.reflect.Method;

import org.junit.Test;

import com.reflect.ReflectService;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxyExample implements MethodInterceptor{
	/*����CGLIB�������*/
	public Object getProxy(Class cls) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(cls);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	/*
	 * ������ 
	 * ��Ч�� jdk�����invoke������
	 * */
	@Override
	public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("�������Ƕ���ǰ");
						//����ʵ����		������󣬲����б�       
		Object result = arg3.invokeSuper(arg0, arg2);
		System.out.println("������ʵ�����");
		return result;
	}
	@Test
	public void testCGLIBProxy() {
		CglibProxyExample cpe = new CglibProxyExample();
		ReflectService obj = (ReflectService)cpe.getProxy(ReflectService.class);
		obj.sayHello("����");
	}
	
}