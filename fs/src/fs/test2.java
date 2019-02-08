package fs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class test2 {
	public static void main(String[] args) throws Exception, Throwable {
		// TODO Auto-generated method stub
		String path = "com.bean.bean1";
		try {
			Class<?> clazz = Class.forName(path);
			System.out.println(clazz.getName()+"\n"+clazz.getSimpleName());
			//获得public属性
			Field[] fields = clazz.getFields();
			System.out.println("public 属性:"+fields);
			
			Field[] fields1 = clazz.getDeclaredFields();
			Field f = clazz.getDeclaredField("userName");
			System.out.println(fields.length);
			for(Field f0:fields1) {
				System.out.println("属性:"+f0);
			}
			
			//获得方法
			Method[] methods = clazz.getDeclaredMethods();
			Method m = clazz.getDeclaredMethod("getUserName", null);
			//第二个参数位class类型的对象表示方法的类型
			Method m1 = clazz.getDeclaredMethod("setUserName", String.class);
			System.out.println("方法:");
			for(Method m11:methods) {
				System.out.println("\t"+m11);
			}
			
			//获得构造器
			Constructor[] constructors = clazz.getDeclaredConstructors();
			Constructor c = clazz.getDeclaredConstructor(null);
			Constructor c1 = clazz.getDeclaredConstructor(int.class,int.class,String.class);
			for(Constructor con:constructors) {
				System.out.println(con);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
