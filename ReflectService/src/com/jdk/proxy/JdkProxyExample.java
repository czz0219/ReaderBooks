package com.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class JdkProxyExample implements InvocationHandler {
	//Ŀ�����
	private Object target = null;
	//���ɴ������ʵ��.�������target
	public Object bind(Object target) {
		this.target = target;
		/*
		 * @param1 �������
		 * @param2 �����ɵĶ�̬������������Щ�ӿ���.
		 * @param3 �����࣬����ʵ��InvocationHandler
		 * */
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this );
	}
	/*
	 * @param arg0 �������
	 * @param arg1 ��ǰ���ȷ���
	 * @param Object[] ��ǰ��������
	 * @param Object ����������
	 * */
	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("��������߼�����,�ڵ�����ʵ����֮ǰ�ķ���");
		Object obj = arg1.invoke(target, arg2);//���� target�ķ���
		System.out.println("������ʵ����ֵ�����");
		return obj;
	}
	@Test
	public void testJdkProxy() {
		JdkProxyExample jdk = new JdkProxyExample();
		//��������ʵ���� ������� interface��HelloWorld
		HelloWorld proxy =(HelloWorld)jdk.bind(new HelloWorldImpl());
		/*
		 * һ��target �� proxy(�������)��Ӧ���й�ͬ��һ���ӿ�;
		 * ����proxy���� interface�ķ���ʱ�����Զ��� proxy.invoke()����Ҫ�Ĳ����������������Զ����ݸ�
		 * proxy.invoke()Ȼ�� ����proxy.invoke();
		 * */
		proxy.sayHelloWorld();
	}

}
