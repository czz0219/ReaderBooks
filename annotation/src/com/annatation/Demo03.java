package com.annatation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/*
 * ʹ�÷��䴦��ע����Ϣ
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
			/*���ָ��ע��ķ�ʽ*/
			SxtTable st = (SxtTable) clazz.getAnnotation(SxtTable.class);
			System.out.println(st);
			/*���������Ե�ע��
			 *ͨ����ע�����������������ֶΣ���ע�������Ҳ�������ֶι��������� 
			 */
			Field f = clazz.getDeclaredField("studentName");
			SxtField sf = f.getAnnotation(SxtField.class);
			System.out.println("insert into "+st.value()+" values('"+sf.columnName()+"');\n"+sf.length()+"---"+sf.type());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
