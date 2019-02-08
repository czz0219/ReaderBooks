package jsong;

import java.util.ArrayList;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pojo.Grade;
import com.pojo.Student;
/*�ȸ��GSON����*/
public class GSONTest {

    //����
    @Test
    public void test1() {
        // ����Ƕ������Ƕ�׶���
        String json1 = "{'id':1,'name':'JAVAEE-1703','stus':[{'id':101,'name':'��һ','age':16}]}";
        // ����
        String json2 = "['����','���','����']";

        Gson gson=new Gson();
        //1��
        //�������󣺵�һ�����������������ַ��� �ڶ�������������������͵�Class����
        Grade grade=gson.fromJson(json1, Grade.class);
        System.out.println(grade);

        //2��
        //��������Ҫ��ʹ��Type
        ArrayList<String> list=gson.fromJson(json2, 
                new TypeToken<ArrayList<String>>(){}.getType());
        System.out.println(list);
    }
    //����
    @Test
    public void test2(){
        ArrayList<Student> list=new ArrayList<>();
        for(int i=1;i<3;i++){
            list.add(new Student(101+i, "����", 20+i));
        }
        Grade grade=new Grade(100001,"����", list);
        Gson gson=new Gson();
        //������ת��Ϊ��JSON��ʽ�ַ���
        String json=gson.toJson(grade);
        System.out.println(json);

    }
}
