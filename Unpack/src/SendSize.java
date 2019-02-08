import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;  

public class SendSize { 
	
	public static void main(String[]arc) throws Exception {
		readXMLDemo();
	}

    public static void readXMLDemo() throws Exception {  

        // 创建saxReader对象  
        SAXReader reader = new SAXReader();  
        // 通过read方法读取一个文件 转换成Document对象 
        String filename ="C:\\Users\\root\\Desktop\\记账返回.xml";
        String src=null;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuffer sb = new StringBuffer();
        StringBuffer tar = new StringBuffer();
        String temp = null;
        while((temp = br.readLine())!=null) {
        	sb.append(temp);        
        	}
        int length = Integer.parseInt(sb.toString().substring(0, 8));
        System.out.println("长度:"+length);
        src = sb.substring(sb.indexOf(">")+1);
        BufferedReader read  = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(src.getBytes()),"UTF-8"));
        Document document = reader.read(read);  
      //  document.getRootElement().asXML(); 
        //获取根节点元素对象  
        Element rootnode = document.getRootElement();  
        //遍历所有的元素节点  
        // listNodes(node); 
        
        Map<String,Object> headerMap =analysisSYS_HEAD(rootnode.element("sys-header"));
        Map<String,Object> bodyMap =analysisBody(rootnode.element("body"));
    } 
    
    public static Map<String,Object> analysisBody(Element node) {
		System.out.println("解body"+"========================================");
		Map<String,Object> map = new HashMap<String,Object>();
		if(node==null){
			return map;
		}
		Iterator<Element> it = node.elementIterator();
		while (it.hasNext()) {
			//data节点   路径body/data
			Element e = it.next();
			//判断body下是否有array节点
			if(!(e.element("array")==null)){
				//得到struct节点
				Iterator<Element> structs = e.element("array").elementIterator();
				@SuppressWarnings("rawtypes")
				List list = new ArrayList();
				while (structs.hasNext()) {
					Element struct = structs.next();
					Map<String,Object> structMap = new HashMap<String,Object>();
					Iterator<Element> datas = struct.elementIterator();
					while(datas.hasNext()){
						Element data = datas.next();
						Element field = data.element("field");
						structMap.put(data.attributeValue("name"), field.getTextTrim());
					}
					list.add(structMap);
				}
				map.put("LISTNM", list);
			}else{
				Element fe = e.element("field");
				map.put(e.attributeValue("name"), fe.getTextTrim());
			}
		}
		System.out.println("解包Header结果:"+map);
		return map;
	}

    public void createXMLDemo() throws Exception {  
         Document document = DocumentHelper.createDocument();
         Element root = document.addElement( "cars" );
         Element supercarElement= root.addElement("supercars")
            .addAttribute("company", "Ferrai");

         supercarElement.addElement("carname")
            .addAttribute("type", "Ferrari 101")
            .addText("Ferrari 101");

         supercarElement.addElement("carname")
            .addAttribute("type", "sports")
            .addText("Ferrari 202");
         // 写入到一个新的文件中 
         writer(document);
    }

    /** 
     * 把document对象写入新的文件 
     *  
     * @param document 
     * @throws Exception 
     */  
    public void writer(Document document) throws Exception {  
        // 紧凑的格式  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // 排版缩进的格式  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // 设置编码  
        format.setEncoding("UTF-8");  
        // 创建XMLWriter对象,指定了写出文件及编码格式  
        // XMLWriter writer = new XMLWriter(new FileWriter(new  
        // File("src//a.xml")),format);  
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File("src//car.xml")), "UTF-8"), format);  
        // 写入  
        writer.write(document);  
        // 立即写入  
        writer.flush();  
        // 关闭操作  
        writer.close();  
    }  

    /** 
     * 遍历当前节点元素下面的所有(元素的)子节点 
     *  
     * @param node 
     */  
    public static void listNodes(Element node) {  
        System.out.print(node.getName()+"--");  
        // 获取当前节点的所有属性节点  
        List<Attribute> list = node.attributes();  
        // 遍历属性节点  
     /*   for (Attribute attr : list) {  
            System.out.println("属性:"+attr.getText() + "-----" + attr.getName()  
                    + "---" + attr.getValue());  
        }   */

        if (!(node.getTextTrim().equals(""))) { 
            System.out.println( node.getText());  
        }  else
        {
        	System.out.println("");
        }

        // 当前节点下面子节点迭代器  
        Iterator<Element> it = node.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  
            listNodes(e);  
        }  
    }  
    
    public static Map<String,Object> analysisSYS_HEAD(Element node) {
		System.out.println("解header"+"========================================");
		Map<String,Object> map = new HashMap<String,Object>();
		if(node==null){
			return map;
		}
		//到了data节点  sys-header/data/struct/data
		Iterator<Element> it = node.element("data").element("struct").elementIterator();
		while (it.hasNext()) {
			// 获取某个子节点对象
			Element e = it.next();
			//判断data节点下是否有array节点
			if(!(e.element("array")==null)){
				//itd的节点为data节点  sys-header/data/struct/data/array/struct/data
				Iterator<Element> itd = e.element("array").element("struct").elementIterator();
				while (itd.hasNext()) {
					Element ed=itd.next();
					//field节点  sys-header/data/struct/data/array/struct/data/field
					Element fe = ed.element("field");
					map.put(ed.attributeValue("name"),fe.getTextTrim());
				}
			}else{
				//header中未返回数组
				Element fe = e.element("field");
				map.put(e.attributeValue("name"), fe.getTextTrim());
			}
		}
		System.out.println("解包Header结果:"+map);
		return map;
	}

    /** 
     * 介绍Element中的element方法和elements方法的使用 
     *  
     * @param node 
     */  
    public static void elementMethod(Element node) {  
        // 获取node节点中，子节点的元素名称为supercars的元素节点。  
        Element e = node.element("RqHead");  
        // 获取supercars元素节点中，子节点为carname的元素节点(可以看到只能获取第一个carname元素节点)  
        Element carname = e.element("TranCode");  

        System.out.println(e.getName() + "----" + carname.getText());  

        // 获取supercars这个元素节点 中，所有子节点名称为carname元素的节点 。  

        List<Element> carnames = e.elements("carname");  
        for (Element cname : carnames) {  
            System.out.println(cname.getText());  
        }  

        // 获取supercars这个元素节点 所有元素的子节点。  
        List<Element> elements = e.elements();  

        for (Element el : elements) {  
            System.out.println(el.getText());  
        }  

    }  

}  