package com.reflect;
/*
 * 反射
 * */

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectService {
	private String name;
	public ReflectService(String name) {
		this.name=name;
	}
	public ReflectService() {
		this.name="";
	}
	public void sayHello(String end) {
		System.out.println("hello "+ this.name+end);
	}
	public static void main(String[] args) {
		reflect0();
		reflect();
	}
	public static void reflect0() {
		ReflectService object = null;
		try {
			object =(ReflectService)Class.forName("com.reflect.ReflectService").getConstructor(String.class)
					.newInstance("ReflectInstance");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReflectService target = new ReflectService();
		try {
			Method method = ReflectService.class.getMethod("sayHello", String.class);//获得method实例
			method.invoke(target, "张三");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Object reflect() {
		ReflectService object = null;
		try {
			object =(ReflectService)Class.forName("com.reflect.ReflectService").newInstance();//通过类加载器创建实例
			Method method = object.getClass().getMethod("sayHello",String.class);//通过实例找到类加载器，然后通过类加载器创建 Method实例
			method.invoke(object, "章三");
		} catch (InstantiationException  | ClassNotFoundException|NoSuchMethodException 
				| SecurityException|IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//创建对象
		
		return object;
	}
}
