package jsong;
import java.io.FileNotFoundException; 
import java.io.FileReader; 
   
import com.google.gson.JsonArray; 
import com.google.gson.JsonIOException; 
import com.google.gson.JsonObject; 
import com.google.gson.JsonParser; 
import com.google.gson.JsonSyntaxException; 
   
public class oper_json { 
 
/** 
* ͨ����������test.jsonת����JsonObject 
* gson-2.8.0.jar
* @param args 
*/ 
   
public static void main(String[] args) { 
 
//�� test.json ������ת���� JSON ���� 
//��Ҫ����һ�����������������������ַ����������� 
JsonParser parser=new JsonParser(); 
 
 
try { 
 
//����һ��JSON���󣬽���parser������ķ���ֵ 
//ʹ��parse()����������һ��Reader���󣬷���ֵ��JsonElement���� 
//��ΪҪ��ȡ�ļ������Դ���һ��FileReader 
//JsonObject��JsonElement�����࣬������Ҫǿת 
//���쳣�׳���ʹ�� try catch ���� 
JsonObject object=(JsonObject) parser.parse(new FileReader("test.json")); 
 
 
//�Ƚ������ⲿ��������� category �� pop 
//�� get �����ƣ����������ص��� JsonElement���� getAs ת����ʲô���͵�ֵ 
//���� json ��ʽ����������� 
System.out.println("category="+object.get("category").getAsString()); 
System.out.println("pop="+object.get("pop").getAsBoolean()); 
System.out.println("jsonToString:"+object.toString());
 
//���Ŷ�ȡtest.json���JSON���飬������languages������ 
//����һ��JsonArray 
JsonArray array=object.get("languages").getAsJsonArray(); 
for (int i = 0; i < array.size(); i++) { 
//�ָ��� 
System.out.println("-----------------"); 
//����һ��JsonObject����array���±��ȡ��get() ����JsonElement���� 
//���ﲻ��ǿת������ getAsJsonObject() ����ת�� 
JsonObject subObject=array.get(i).getAsJsonObject(); 
System.out.println("id="+subObject.get("id").getAsInt()); 
System.out.println("name="+subObject.get("name").getAsString()); 
System.out.println("ide="+subObject.get("ide").getAsString()); 
 
} 
 
 
 
 
} catch (JsonIOException e) { 
e.printStackTrace(); 
} catch (JsonSyntaxException e) { 
e.printStackTrace(); 
} catch (FileNotFoundException e) { 
e.printStackTrace(); 
} 
} 
   
}