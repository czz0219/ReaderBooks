package com.itheima;

import java.lang.reflect.Method;

public class AnnotationParse {

	public static void parse() {
		// TODO Auto-generated method stub
		Class<?> class1 = Itheimacloud.class;
		if(class1.isAnnotationPresent(ClassInfo.class));{
			//通过Itheimacloud的class对象获得他的类注解
			ClassInfo classInfo =class1.getAnnotation(ClassInfo.class);
			System.out.println(classInfo);
		}
		
		Method[] methods =class1.getMethods();
		for(Method method:methods) {
			if(method.isAnnotationPresent(MethodInfo.class)) {
				MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
				System.out.println(methodInfo);
			}
		}
	}

	public static void main(String[] args) {
		AnnotationParse.parse();
	}

}
