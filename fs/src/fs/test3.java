package fs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.bean.bean1;

public class test3 {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		//��̬����������
		String path = "com.bean.bean1";
		try {
			Class<?> clazz = Class.forName(path);
			bean1 u = (bean1)clazz.newInstance();
			System.out.println(u);
			Constructor<?> c = clazz.getDeclaredConstructor(int.class,int.class,String.class);
			bean1 u2 =(bean1)c.newInstance(1001,18,"��Դ��");
			System.out.println("��������:"+u2.getUserName());
			
			bean1 u3 = (bean1) clazz.newInstance();
			Method method = clazz.getDeclaredMethod("setUserName", String.class);
			method.invoke(u3, "��Դ��");
			System.out.println("����ִ�з���setUserName:"+u3.getUserName());
			
			bean1 u4 = (bean1) clazz.newInstance();
			Field f = clazz.getDeclaredField("userName");
			f.setAccessible(true);//��������Ȩ��
			f.set(u4,"czz");//ͨ������д����
			System.out.println(u4.getUserName());//ͨ�����������
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
