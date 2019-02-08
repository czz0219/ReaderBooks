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
			//���public����
			Field[] fields = clazz.getFields();
			System.out.println("public ����:"+fields);
			
			Field[] fields1 = clazz.getDeclaredFields();
			Field f = clazz.getDeclaredField("userName");
			System.out.println(fields.length);
			for(Field f0:fields1) {
				System.out.println("����:"+f0);
			}
			
			//��÷���
			Method[] methods = clazz.getDeclaredMethods();
			Method m = clazz.getDeclaredMethod("getUserName", null);
			//�ڶ�������λclass���͵Ķ����ʾ����������
			Method m1 = clazz.getDeclaredMethod("setUserName", String.class);
			System.out.println("����:");
			for(Method m11:methods) {
				System.out.println("\t"+m11);
			}
			
			//��ù�����
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
