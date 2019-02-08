package com.czz.dom4j.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class readXMLDemo {
	public static final String sysEncode="GBK";
	public static Map<String,Object>unpack = new HashMap<String,Object>();
	
	 //--------------------test method----------------------- 
    public static void main(String[] argc) throws Exception {
    	Map<String,Object> inData = new HashMap<String,Object>();
    	Element root = getRootElement();
    	System.out.println("-----------------------------");
    	inData =listSpecialNodes(root);
    	System.out.println("\ninData:"+inData);
    }
    
  //------------------Resource Method---------------------------------
    public static Element getRootElement() throws IOException, DocumentException {
    	String line =null;
    	String xmlBom =null;
        String xmlReq =null;
        String encode  = null;
        int len,startIndex=0;
        // 模拟网络数据源
        String filename ="D:\\ESB\\test-pkt\\test.xml";
        String filename1= "C:\\Users\\root\\Documents\\WeChat Files\\kuailexiaozeng\\Files\\jt1009_ret.xml";
        @SuppressWarnings("resource") //
        byte[] bytes =new byte[102400];
        FileInputStream fis = new FileInputStream(new File(filename1));
        while((len= fis.read(bytes, startIndex,1024))!=-1) {
        	startIndex +=len;
        };
        /*-------------------解包处理开始----------------------------*/
        String str0 = new String(bytes,"ASCII");
        System.out.println("ASCII:"+str0.trim());
        xmlBom = str0.substring(0, str0.indexOf(">")+1);
        encode = xmlBom.substring(xmlBom.indexOf("encoding=")+10, xmlBom.lastIndexOf('"'));
        
        String srcStr = new String(bytes,encode); //重新编码
        String targetStr = new String(srcStr.getBytes(sysEncode),sysEncode);//转换成GBK编码
        xmlBom = targetStr.substring(0, targetStr.indexOf(">")+1);
  
        xmlReq = targetStr.substring(targetStr.indexOf(">")+1);
        System.out.println("encode:"+encode+"\nxmlBom:"+ xmlBom +"\nxmlReq:"+xmlReq);
        //创建Document 
        Document document = DocumentHelper.parseText(xmlReq.trim());
        //获取根节点元素对象  
        Element root = document.getRootElement();  
        return root;
    }
	  /** 
     * 解包非标准XML
     *  
     * @param node 
     */  
    public static Map<String,Object> listSpecialNodes(Element node) {
    	if("data".equals(node.getName()) && !"Records".equals(node.attribute("name")) ){
    		Element field =null;
    		if ((field = node.element("field"))!=null) {
    		     if (!(field.getTextTrim().equals(""))) 
    		        {   
    		         // 获取当前节点data 的所有属性节点  
    		            @SuppressWarnings("unchecked")
    					List<Attribute> list = node.attributes();
    		           
    		            // 遍历属性节点  
    		            for (Attribute attr : list) { 
    		            	if("name".equals(attr.getName())) {
    		            		unpack.put(attr.getStringValue(), field.getTextTrim());
    		            		System.out.println("单次添加:["+attr.getStringValue()+"="+field.getTextTrim()+"]");
    		            	}
    	
    		            }
    		            
    		        }  else
    		        {
    		        	System.out.println("");
    		        }
			}
    	}else {
    		// Records/array/struct/data[n]
    		Iterator<Element> it = node.elementIterator();
    		while (it.hasNext()) {
    			//array
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
    				unpack.put("LISTNM", list);
    			}else{
    				Element fe = e.element("field");
    				unpack.put(e.attributeValue("name"), fe.getTextTrim());
    			}
    		}
    		
    		
    	}

        // 当前节点下面子节点迭代器  
        @SuppressWarnings("unchecked")
		Iterator<Element> it = node.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  
            listSpecialNodes(e);  
        }
        return unpack;
    }
    
    /** 
     * 解包   标准XML
     *  
     * @param node 
     */  
    public static Map<String,Object> listNodes(Element node) {
    	if("data".equals(node.getName())){
    		Element field =null;
    		if ((field = node.element("field"))!=null) {
    			
    		     if (!(field.getTextTrim().equals(""))) 
    		        { 
    		       // 	System.out.print(node.getName()+"--"+node.getTextTrim());
    		            
    		         // 获取当前节点的所有属性节点  
    		            @SuppressWarnings("unchecked")
    					List<Attribute> list = field.attributes();
    		           
    		            Map<String,Object>m_attr = new HashMap<String,Object>();
    		            // 遍历属性节点  
    		            for (Attribute attr : list) {  
    		                m_attr.put(attr.getName(), attr.getStringValue());//添加到属性列表
    		            }
    		            //添加属性和节点到field
    		            Map<String,Object> Node = new HashMap<String,Object>();
    		            Node.put("label", field.getName());
    		            Node.put("attr", m_attr);
    		            Node.put("value",field.getTextTrim());
    		            unpack.put(field.getName(), Node);
    		            
    		            System.out.println("单个变量:"+Node);
    		            
    		        }  else
    		        {
    		        	System.out.println("");
    		        }
			}
    	}
   

        // 当前节点下面子节点迭代器  
        @SuppressWarnings("unchecked")
		Iterator<Element> it = node.elementIterator();  
        // 遍历  
        while (it.hasNext()) {  
            // 获取某个子节点对象  
            Element e = it.next();  
            // 对子节点进行遍历  
             listNodes(e);  
        }
        return unpack;
    }  
}
