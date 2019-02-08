package com.reflect;
/*
 * ����
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
			Method method = ReflectService.class.getMethod("sayHello", String.class);//���methodʵ��
			method.invoke(target, "����");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException |NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Object reflect() {
		ReflectService object = null;
		try {
			object =(ReflectService)Class.forName("com.reflect.ReflectService").newInstance();//ͨ�������������ʵ��
			Method method = object.getClass().getMethod("sayHello",String.class);//ͨ��ʵ���ҵ����������Ȼ��ͨ������������� Methodʵ��
			method.invoke(object, "����");
		} catch (InstantiationException  | ClassNotFoundException|NoSuchMethodException 
				| SecurityException|IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//��������
		
		return object;
	}
}
