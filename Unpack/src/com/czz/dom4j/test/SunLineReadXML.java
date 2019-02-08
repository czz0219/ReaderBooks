package com.czz.dom4j.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class SunLineReadXML {
	public static final String sysEncode="GBK";
	public static Map<String,Object>unpack = new HashMap<String,Object>();

	public static void main(String[] args) throws IOException, Exception {
		// TODO Auto-generated method stub
		Map<String,Object> inData = new HashMap<String,Object>();
    	Element root = getRootElement();
    	Map<String,Object> bodyMap =analysisBody(root.element("body"));
    	System.out.println("\ninData:"+bodyMap);
	}

	public static Map<String,Object> analysisBody(Element node) {
		System.out.println("解body"+"========================================");
		Map<String,Object> map = new HashMap<String,Object>();
		if(node==null){
			return map;
		}
		@SuppressWarnings("unchecked")
		Iterator<Element> it = node.elementIterator();
		while (it.hasNext()) {
			//data节点   路径body/data
			Element e = it.next();
			System.out.println(e.attributeValue("name"));
			//自贡新版解包  成增增
				if(!(e.element("array")==null)){
					//得到struct节点
					Iterator<Element> records = e.element("array").elementIterator();
					@SuppressWarnings("rawtypes")
					List list = new ArrayList();
					while (records.hasNext()) {
						//遍历 records
						Element record = records.next();
						System.out.println(record.attributeValue("name"));
						Map<String,Object> recordMap = new HashMap<String,Object>();
						Iterator<Element> datas = record.elementIterator();//遍历每个字段
						while(datas.hasNext()){
							Element data = datas.next();
							if(data.element("array")!=null && data.element("array").element("struct")!=null) {
								@SuppressWarnings("unchecked")
								Iterator<Element> structinfo = data.element("array").element("struct").elementIterator();
								Map<String,Object> subMap = new HashMap<String,Object>();
								while(structinfo.hasNext()) {
									Element info = structinfo.next();
									Element field = info.element("field");
									System.out.println("["+info.attributeValue("name")+":"+ field.getTextTrim()+"]");
									subMap.put(info.attributeValue("name"), field.getTextTrim());
								}
								recordMap.put("SUBINFO", subMap);
							}else
							{
								Element field = data.element("field");
								System.out.println("["+data.attributeValue("name")+":"+ field.getTextTrim()+"]");
								recordMap.put(data.attributeValue("name"), field.getTextTrim());
							}
							
						}
						list.add(recordMap);
					}
					map.put("LISTNM", list);
				}else{
					Element fe = e.element("field");
					map.put(e.attributeValue("name"), fe.getTextTrim());
				}
		//	}
		}
		return map;
	}
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
	        FileInputStream fis = new FileInputStream(new File(filename));
	        while((len= fis.read(bytes, startIndex,1024))!=-1) {
	        	startIndex +=len;
	        };
	        /*-------------------解包处理开始----------------------------*/
	        String str0 = new String(bytes,"ASCII");
	     //   System.out.println("ASCII:"+str0.trim());
	        xmlBom = str0.substring(0, str0.indexOf(">")+1);
	        encode = xmlBom.substring(xmlBom.indexOf("encoding=")+10, xmlBom.lastIndexOf('"'));
	        
	        String srcStr = new String(bytes,encode); //重新编码
	        String targetStr = new String(srcStr.getBytes(sysEncode),sysEncode);//转换成GBK编码
	        xmlBom = targetStr.substring(0, targetStr.indexOf(">")+1);
	  
	        xmlReq = targetStr.substring(targetStr.indexOf(">")+1);
	     //   System.out.println("encode:"+encode+"\nxmlBom:"+ xmlBom +"\nxmlReq:"+xmlReq);
	        //创建Document 
	        Document document = DocumentHelper.parseText(xmlReq.trim());
	        //获取根节点元素对象  
	        Element root = document.getRootElement();  
	        return root;
	    }
}
