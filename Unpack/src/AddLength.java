import java.util.HashMap;
import java.util.Map;

public class AddLength {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*
		 * ����  ��  ������洢�� byte[]�ֽ��� ������ֵ���ҵ���Ӧ�ַ�
		 * ����   ��ԭ���ַ��� ������ֵ�ͱ����ʽ�ҵ���Ӧ����ֵ���� ���� byte[] �ֽ���
		 * */
		String new_str ="我是系统某种编码的字符集";
		byte [] dataSource = new_str.getBytes("gbk");
		/*普通字符集的编解码*/
		String final_str = new String(new_str.getBytes("gbk"), "gbk"); //转gbk
		String final_str1 = new String(new_str.getBytes("utf-8"), "utf-8");//转UTF-8
		System.out.println(final_str+"||"+final_str1);
		
		/*模仿来自网络字节流的编码转换*/
		String final_str2 = new String(dataSource, "gbk"); //还原成GBK编码的字符集
		//将(GBK编码的)字符集转UTF-8编码的字节流，然后转成字符集
		String final_str3 = new String(final_str2.getBytes("utf-8"), "utf-8");
		System.out.println("转换后的字符utf-8:"+final_str3);
		
		String num =String.valueOf(1024);
		System.out.println("-------------------------------");
		byte[] gbk =num.getBytes("gbk");//����ֵ�� �ַ���ת���� GBK���� �ֽ���
		System.out.printf("gbk�ֽ�����:%d,%d,%d,%d\n",gbk[0],gbk[1],gbk[2],gbk[3]); //��ӡ��һ���ַ��� gbk����
		
		byte[] utf8=num.getBytes("utf-8");
		System.out.printf("utf-8�ֽ�����:%d,%d,%d,%d\n",utf8[0],utf8[1],utf8[2],utf8[3]);
		Map<String,Object> src = new HashMap<String ,Object>();
		src.put("bs", new byte[1024]);
		byte[] bs = addPktLength(src);
		System.out.println(bs);
		
	}
	
	public static byte[] addPktLength(Map<String,Object> src) throws Exception{
		byte[]bs =null;
		int length =-1;
		length =((byte[]) src.get("bs")).length;
		byte[] len = String.format("%08d", length).getBytes();
		System.out.println(String.format("%08d", length));
		bs = new byte[length+len.length];
		System.arraycopy(len, 0, bs, 0, len.length);
		System.arraycopy(src.get("bs"), 0, bs, len.length,length);
		return bs;
	}

}
