import java.util.HashMap;
import java.util.Map;

public class ChannelSelect {
	public static Map<String,Object> main(String[] args ){
		Map<String,Object> retchl= new HashMap<String,Object>();
		retchl.put("tranam", "100");
		retchl.put("paraif", "111000");
		retchl.put("value1", "0");
		if(Float.parseFloat((String) retchl.get("tranam")) > 50000){
			System.out.println("���");
			retchl.put("channel", "0");//�ߴ��,��ǰ��С�������Ŷ�Ϊ0
		}else{//С�����
			if("1".equals(retchl.get("paraif").toString().charAt(2)) ){//����ҵ��֧��
				System.out.println("����");
				if("1".equals(retchl.get("value1"))){//��������
					System.out.println("�°�����");
					retchl.put("channel", "2");//�ϰ汾
				}else{
					System.out.println("�ϰ汾����");
					retchl.put("channel", "3");;//�°汾
				}
			}else{
				retchl.put("channel", "0");;//С��
			}
			
		}
		return retchl;
	}

}

