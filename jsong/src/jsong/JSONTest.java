package jsong;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import com.pojo.Grade;
import com.pojo.Student;
/*
 * JSON�ٷ��Ľ�������
 * */
public class JSONTest {

    //����JSON
    @Test
    public void test1() throws JSONException{
        //����Ƕ������Ƕ�׶���
        String json1="{'id':1,'name':'JAVAEE-1703','stus':[{'id':101,'name':'��һ','age':16}]}";
        //����
        String json2="['����','���','����']";

        //1��
        //������һ��---����
        JSONObject jObject1=new JSONObject(json1);
        Grade grade=new Grade();
        grade.setId(jObject1.getInt("id"));
        grade.setName(jObject1.getString("name"));
        ArrayList<Student> stus=new ArrayList<>();
        grade.setStus(stus);
        //�����ڶ���----����
        JSONArray jsonArray2=jObject1.getJSONArray("stus");
        //���������ȡԪ��----����
        for(int i=0;i<jsonArray2.length();i++){
            //����������----����
            JSONObject jObject3=jsonArray2.getJSONObject(i);
            Student student=new Student(jObject3.getInt("id"), jObject3.getString("name"), jObject3.getInt("age"));
            grade.getStus().add(student);
        }

        System.out.println(grade);

        //��ȡ��ǰ������������Եĵ���������
//      Iterator<String> iterator=jObject1.keys();
//      while (iterator.hasNext()) {
//          String key = iterator.next();
//          System.out.println("���ԣ�"+key);
//      }

        //2��
        //��ȡ�������
        JSONArray jArray=new JSONArray(json2);
        ArrayList<String> list=new ArrayList<>();
        //������ȡԪ��
        for(int i=0;i<jArray.length();i++){
            //jArray.optString(i);//�ȼ���getXXX
            list.add(jArray.getString(i));
        }
        System.out.println("���������"+list);
    }
    //����JSON
    @Test
    public void test2() throws JSONException{
        JSONObject jo1=new JSONObject();
        jo1.put("id", 1001);
        jo1.put("name", "����");
        jo1.put("age", 20);
        JSONObject jo2=new JSONObject();
        jo2.put("id", 1002);
        jo2.put("name", "ƽƽ");
        jo2.put("age", 19);
        JSONArray ja2=new JSONArray();
        ja2.put(jo1);
        ja2.put(jo2);
        JSONObject jo3=new JSONObject();
        jo3.put("id", 11);
        jo3.put("name", "JAVAEE-1704");
        jo3.put("stus",ja2);        
        String json=jo3.toString();
        System.out.println(json);

    }

}
