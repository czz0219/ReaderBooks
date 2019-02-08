import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.junit.Test;

public class InetAddressTest {
	public static final String sysEncode="GBK";
	public static final String destEncode="UTF-8";
	public static void main(String[] args)throws UnknownHostException, SocketException {
		// TODO Auto-generated method stub
	/*	InetAddress address = InetAddress.getLocalHost();
		System.out.println("本地主机地址:"+address+"\n名字:"+address.getHostName());
		
		address =InetAddress.getByName("www.nba.com");
		System.out.println(address);
		InetAddress SW[] = InetAddress.getAllByName("www.baidu.com");
		for(int i =0;i<SW.length;i++)
			System.out.println((i+1)+"号服务器"+SW[i]);
		
		InetAddress multiAdd = InetAddress.getByName("224.0.0.0");
		System.out.println(""+"组播地址:"+multiAdd.isMulticastAddress()
		+"组织范围组播地址:"+multiAdd.isMCOrgLocal()
		+"网站范围组播地址:"+multiAdd.isMCSiteLocal()
		+"子网范围组播地址:"+multiAdd.isMCLinkLocal()
		);
		InetAddress proxS = InetAddress.getByName("172.20.13.205");
		try {
			System.out.println("代理服务器可达:"+proxS.isReachable(10));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   */
		//具有相同IP地址的两个InetAddress 实例的equals/hashCode方法返回true;由此判断是否是同一台机器
	/*	InetAddress ibiblio = InetAddress.getByName("www.ibiblio.org");
		InetAddress helios = InetAddress.getByName("helios.ibiblio.org");
		System.out.println("address equals/one machine:"+ibiblio.equals(helios));*/
		
/*		System.out.println("本地主以太网接口:"+NetworkInterface.getByName("net0"));
		//获取网络接口列表
		getNetworkInterfaces();
		//下载页面
		downloadPage("http://world.chinadaily.com.cn/a/201901/03/WS5c2dd37ca3106072a9033f31.html");
		//url解析
		String[] urls = {"ftp://mp3:mp3@138.247.121.61:21000/c%3a/",
				"http://www.oreilly.com",
				"http://www.ibiblio.org/nywc/comdfsd.phtml?category=Piano",
				"http://admin@www.blackstar.com:8080/"};
		urlSplitter(urls);           
		urlEncodeErr("");  */
		urlEncodeStd("");
		//String targetStr = new String(srcStr.getBytes(sysEncode),sysEncode);//转换成GBK编码
		ENandDeCode();
	}
	public static Enumeration getNetworkInterfaces()throws SocketException{
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		System.out.println("网络接口列表:");
		while(interfaces.hasMoreElements()) {
			NetworkInterface ni = interfaces.nextElement();
			System.out.println(ni);
			Enumeration<?> address = ni.getInetAddresses();
			while(address.hasMoreElements()) {
				System.out.println("(虚拟/真实)网络接口 "+ni.getName()+" 的ip元素:"+address.nextElement());
			}
		}
		return interfaces;
	}
	//http://tieba.baidu.com/   下载一个页面
	public static void downloadPage(String url) {
		InputStream in = null;
		URL u = null;
		try {
			u = new URL(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			in = u.openStream();
			in = new BufferedInputStream(in); //封装成带缓冲的字节流
			Reader r = new InputStreamReader(in);
			int c;
			while((c =r.read())!=-1) {//读取一个字符,java 中一个字符是int大小和c语言区别
				System.out.print((char)c);//转换成ascii编码的字符打印
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(in!=null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	/*URL解析*/
	public static  void urlSplitter(String[] urls) {
		for(int i=0;i<urls.length;i++) {
			try {
				URL u = new URL(urls[i]);
				System.out.println("URL:"+u);
				System.out.println("protocol:"+u.getProtocol());
				System.out.println("userInfo:"+u.getUserInfo());
				
				String host = u.getHost();
				if(host!=null) {
					System.out.println("source host:"+host);
					int atSign = host.indexOf('@');
					if(atSign !=-1)host = host.substring(atSign+1); 
					System.out.println("host:"+host);
				}
				
				System.out.println("port:"+u.getPort());
				System.out.println("path:"+u.getPath());
				System.out.println("ref:"+u.getRef());
				System.out.println("query:"+u.getQuery());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*URL编码 error*/
	public static void urlEncodeErr(String src) {
		if("".equals(src)) {
			src="https://www.google.com/search?h1=en&as_q=java&as_epq=I/O";
		}
		try {
			String query = URLEncoder.encode(src, "UTF-8");
			System.out.println("URLEncoder整体编码结果:"+query);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*URL编码 true*/
	public static String  addQuery(String[] name,String[] value,String linkStr,String endStr) throws UnsupportedEncodingException {
		StringBuilder buildStr = new StringBuilder();
		for(int i=0;name[i]!=null;i++) {
			buildStr.append(new String(name[i].getBytes(destEncode),destEncode));
			buildStr.append(linkStr);
			buildStr.append(new String(value[i].getBytes(destEncode),destEncode));
			if(name[i+1]!=null) {
			buildStr.append(endStr);
			}
		}
		return buildStr.toString();
	}
	@SuppressWarnings("null")
	public static void urlEncodeStd(String url) {  //终止符有待优化
		if("".equals(url)) {
		//	url="https://www.google.com/search?h1=en&as_q=java&as_epq=I/O";
			url="https://www.google.com/search?";
		}
		try {
			String temp="";
			String[] names = new String[4];
			String[] values= new String[4];
			String[] tempK = new String[2];
			String[] tempV = new String[2];
			names[0]=new String("h1");
			values[0]=new String("中文");
			names[1]=new String("as_q");
			values[1]=new String("java语言");
			names[2]=new String("as_epq");
			values[2]="";
			
			url +=addQuery(names,values,"=","&");
			tempK[0]="I";
			tempV[0]="O";
			temp=addQuery(tempK,tempV,"/","&");
			url +=temp;
			System.out.println("URLEncoder局部编码结果:"+url);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void ENandDeCode() {
		String str="我是中文的GBK";
		try {
			String utfStr = new String(str.getBytes(destEncode),destEncode);//编解码都是将字符串转换成对应的字节数组然后再转String
			System.out.println(utfStr);
			FileOutputStream f = new FileOutputStream("E:\\test.txt");
			f.write(utfStr.getBytes("UTF-8"));
			f.flush();
			f.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
