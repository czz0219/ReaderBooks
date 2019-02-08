import java.util.HashMap;
import java.util.Map;

public class ChannelSelect {
	public static Map<String,Object> main(String[] args ){
		Map<String,Object> retchl= new HashMap<String,Object>();
		retchl.put("tranam", "100");
		retchl.put("paraif", "111000");
		retchl.put("value1", "0");
		if(Float.parseFloat((String) retchl.get("tranam")) > 50000){
			System.out.println("大额");
			retchl.put("channel", "0");//走大额,当前大小额渠道号都为0
		}else{//小额类的
			if("1".equals(retchl.get("paraif").toString().charAt(2)) ){//网银业务支持
				System.out.println("网银");
				if("1".equals(retchl.get("value1"))){//新老网银
					System.out.println("新版网银");
					retchl.put("channel", "2");//老版本
				}else{
					System.out.println("老版本网银");
					retchl.put("channel", "3");;//新版本
				}
			}else{
				retchl.put("channel", "0");;//小额
			}
			
		}
		return retchl;
	}

}

