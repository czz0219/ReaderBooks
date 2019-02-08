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
* 通过解析器将test.json转换成JsonObject 
* gson-2.8.0.jar
* @param args 
*/ 
   
public static void main(String[] args) { 
 
//将 test.json 的数据转换成 JSON 对象 
//需要创建一个解析器，可以用来解析字符串或输入流 
JsonParser parser=new JsonParser(); 
 
 
try { 
 
//创建一个JSON对象，接收parser解析后的返回值 
//使用parse()方法，传入一个Reader对象，返回值是JsonElement类型 
//因为要读取文件，所以传入一个FileReader 
//JsonObject是JsonElement的子类，所以需要强转 
//有异常抛出，使用 try catch 捕获 
JsonObject object=(JsonObject) parser.parse(new FileReader("test.json")); 
 
 
//先将两个外部的属性输出 category 和 pop 
//先 get 到名称（键），返回的是 JsonElement，再 getAs 转换成什么类型的值 
//依据 json 格式里的数据类型 
System.out.println("category="+object.get("category").getAsString()); 
System.out.println("pop="+object.get("pop").getAsBoolean()); 
System.out.println("jsonToString:"+object.toString());
 
//接着读取test.json里的JSON数组，名称是languages（键） 
//创建一个JsonArray 
JsonArray array=object.get("languages").getAsJsonArray(); 
for (int i = 0; i < array.size(); i++) { 
//分隔线 
System.out.println("-----------------"); 
//创建一个JsonObject，从array的下标获取，get() 返回JsonElement类型 
//这里不用强转，而用 getAsJsonObject() 进行转换 
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