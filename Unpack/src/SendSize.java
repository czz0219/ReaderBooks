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

        // ����saxReader����  
        SAXReader reader = new SAXReader();  
        // ͨ��read������ȡһ���ļ� ת����Document���� 
        String filename ="C:\\Users\\root\\Desktop\\���˷���.xml";
        String src=null;
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuffer sb = new StringBuffer();
        StringBuffer tar = new StringBuffer();
        String temp = null;
        while((temp = br.readLine())!=null) {
        	sb.append(temp);        
        	}
        int length = Integer.parseInt(sb.toString().substring(0, 8));
        System.out.println("����:"+length);
        src = sb.substring(sb.indexOf(">")+1);
        BufferedReader read  = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(src.getBytes()),"UTF-8"));
        Document document = reader.read(read);  
      //  document.getRootElement().asXML(); 
        //��ȡ���ڵ�Ԫ�ض���  
        Element rootnode = document.getRootElement();  
        //�������е�Ԫ�ؽڵ�  
        // listNodes(node); 
        
        Map<String,Object> headerMap =analysisSYS_HEAD(rootnode.element("sys-header"));
        Map<String,Object> bodyMap =analysisBody(rootnode.element("body"));
    } 
    
    public static Map<String,Object> analysisBody(Element node) {
		System.out.println("��body"+"========================================");
		Map<String,Object> map = new HashMap<String,Object>();
		if(node==null){
			return map;
		}
		Iterator<Element> it = node.elementIterator();
		while (it.hasNext()) {
			//data�ڵ�   ·��body/data
			Element e = it.next();
			//�ж�body���Ƿ���array�ڵ�
			if(!(e.element("array")==null)){
				//�õ�struct�ڵ�
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
		System.out.println("���Header���:"+map);
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
         // д�뵽һ���µ��ļ��� 
         writer(document);
    }

    /** 
     * ��document����д���µ��ļ� 
     *  
     * @param document 
     * @throws Exception 
     */  
    public void writer(Document document) throws Exception {  
        // ���յĸ�ʽ  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // �Ű������ĸ�ʽ  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // ���ñ���  
        format.setEncoding("UTF-8");  
        // ����XMLWriter����,ָ����д���ļ��������ʽ  
        // XMLWriter writer = new XMLWriter(new FileWriter(new  
        // File("src//a.xml")),format);  
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File("src//car.xml")), "UTF-8"), format);  
        // д��  
        writer.write(document);  
        // ����д��  
        writer.flush();  
        // �رղ���  
        writer.close();  
    }  

    /** 
     * ������ǰ�ڵ�Ԫ�����������(Ԫ�ص�)�ӽڵ� 
     *  
     * @param node 
     */  
    public static void listNodes(Element node) {  
        System.out.print(node.getName()+"--");  
        // ��ȡ��ǰ�ڵ���������Խڵ�  
        List<Attribute> list = node.attributes();  
        // �������Խڵ�  
     /*   for (Attribute attr : list) {  
            System.out.println("����:"+attr.getText() + "-----" + attr.getName()  
                    + "---" + attr.getValue());  
        }   */

        if (!(node.getTextTrim().equals(""))) { 
            System.out.println( node.getText());  
        }  else
        {
        	System.out.println("");
        }

        // ��ǰ�ڵ������ӽڵ������  
        Iterator<Element> it = node.elementIterator();  
        // ����  
        while (it.hasNext()) {  
            // ��ȡĳ���ӽڵ����  
            Element e = it.next();  
            // ���ӽڵ���б���  
            listNodes(e);  
        }  
    }  
    
    public static Map<String,Object> analysisSYS_HEAD(Element node) {
		System.out.println("��header"+"========================================");
		Map<String,Object> map = new HashMap<String,Object>();
		if(node==null){
			return map;
		}
		//����data�ڵ�  sys-header/data/struct/data
		Iterator<Element> it = node.element("data").element("struct").elementIterator();
		while (it.hasNext()) {
			// ��ȡĳ���ӽڵ����
			Element e = it.next();
			//�ж�data�ڵ����Ƿ���array�ڵ�
			if(!(e.element("array")==null)){
				//itd�Ľڵ�Ϊdata�ڵ�  sys-header/data/struct/data/array/struct/data
				Iterator<Element> itd = e.element("array").element("struct").elementIterator();
				while (itd.hasNext()) {
					Element ed=itd.next();
					//field�ڵ�  sys-header/data/struct/data/array/struct/data/field
					Element fe = ed.element("field");
					map.put(ed.attributeValue("name"),fe.getTextTrim());
				}
			}else{
				//header��δ��������
				Element fe = e.element("field");
				map.put(e.attributeValue("name"), fe.getTextTrim());
			}
		}
		System.out.println("���Header���:"+map);
		return map;
	}

    /** 
     * ����Element�е�element������elements������ʹ�� 
     *  
     * @param node 
     */  
    public static void elementMethod(Element node) {  
        // ��ȡnode�ڵ��У��ӽڵ��Ԫ������Ϊsupercars��Ԫ�ؽڵ㡣  
        Element e = node.element("RqHead");  
        // ��ȡsupercarsԪ�ؽڵ��У��ӽڵ�Ϊcarname��Ԫ�ؽڵ�(���Կ���ֻ�ܻ�ȡ��һ��carnameԪ�ؽڵ�)  
        Element carname = e.element("TranCode");  

        System.out.println(e.getName() + "----" + carname.getText());  

        // ��ȡsupercars���Ԫ�ؽڵ� �У������ӽڵ�����ΪcarnameԪ�صĽڵ� ��  

        List<Element> carnames = e.elements("carname");  
        for (Element cname : carnames) {  
            System.out.println(cname.getText());  
        }  

        // ��ȡsupercars���Ԫ�ؽڵ� ����Ԫ�ص��ӽڵ㡣  
        List<Element> elements = e.elements();  

        for (Element el : elements) {  
            System.out.println(el.getText());  
        }  

    }  

}  