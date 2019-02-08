import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetGet {
	public static void main(String[] args) {
		String  ProcSts =new String();
		Map<String,Object>recv = new HashMap<String,Object>();
		recv.put("TRAN_TIMESTAMP", "102909022");
		Map<String,Object>SUBINFO = new HashMap<String,Object>();
		Map<String,Object>record = new HashMap<String,Object>();
		SUBINFO.put("ProcSts", "NO01");
		record.put("SUBINFO", SUBINFO);
		List<Map<String,Object>> listnm = new ArrayList<Map<String,Object>>();
		listnm.add(record);
		recv.put("LISTNM", listnm);
		System.out.println("Êý¾ÝÔ´:"+recv);
		ProcSts =getRetInfo(recv);
		System.out.println("×´Ì¬:"+ProcSts);
	}
	public static String getRetInfo(Map<String,Object> p){
		String ProcSts = new String();
		if(p.get("LISTNM")!=null){
			@SuppressWarnings("unchecked")
			ArrayList<Map<String,Object>> listnm = (ArrayList<Map<String, Object>>) p.get("LISTNM");
			Map<String,Object> record =(Map<String, Object>) listnm.get(0);
			@SuppressWarnings("unchecked")
			Map<String,Object> subinfo = (Map<String, Object>)record.get("SUBINFO");
			ProcSts = (String)subinfo.get("ProcSts");    
		}
		return ProcSts;
	}

}
