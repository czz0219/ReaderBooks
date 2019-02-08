package com.settle;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class FileExistsDZ {
	public static void main(String[] args) {
		String filesDir="D:\\²Ù×÷\\jtqz\\servicesrc\\sunline\\subsystem\\ibps\\product\\etc\\settle_dir\\";
		fileIsExitIbps(filesDir);
		
	}
    	public static Map<String,Object> fileIsExitIbps(String filesDir){
    		boolean isok=false;
    		int fc = 0;
    		int fcok =0;
    		Map<String,Object> retInfo = new HashMap<String,Object>();
	    	File file = new File(filesDir);
	    	File [] fs = file.listFiles();
	    	
	    	for(File f:fs){
	    		if(!f.isDirectory()){
	    			String fn = f.getName();
	    			if(fn.startsWith("cw_") && fn.endsWith(".txt") && !fn.isEmpty()){
	    				retInfo.put("subName", fn);
	    				fc++;
	    			}
	    			if(fn.startsWith("cw_") && fn.endsWith(".txt.ok")){
	    				fcok++;
	    			}
	    			if(fc ==1 && fcok ==1){
	    				isok = true;
	    				break;
	    			}
	    			}
	    	}
	    	retInfo.put("isok", isok);	
	    	retInfo.put("fileName",filesDir + retInfo.get("subName"));
	    	System.out.println(isok+File.separator+retInfo.get("subName"));
	    	return  retInfo;
	}
}