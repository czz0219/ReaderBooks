package com.annatation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/*
 * 使用反射处理注解信息
 * */
@SuppressWarnings("all")
public class Demo03 {
	public static void main(String[] args) {
		try {
			Class clazz = Class.forName("com.annatation.SxtStudent");
			Annotation[] annotations=clazz.getAnnotations();
			for(Annotation a:annotations) {
				System.out.println(a);
			}
			/*获得指定注解的方式*/
			SxtTable st = (SxtTable) clazz.getAnnotation(SxtTable.class);
			System.out.println(st);
			/*获得类的属性的注解
			 *通常用注解的属性来描述表的字段，被注解的属性也就与表的字段关联起来了 
			 */
			Field f = clazz.getDeclaredField("studentName");
			SxtField sf = f.getAnnotation(SxtField.class);
			System.out.println("insert into "+st.value()+" values('"+sf.columnName()+"');\n"+sf.length()+"---"+sf.type());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
