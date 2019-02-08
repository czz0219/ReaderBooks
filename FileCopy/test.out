package Copy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Redirecting {
public static void main(String args[])throws IOException{
	BufferedInputStream in= new BufferedInputStream(new FileInputStream("E:\\work\\EclipseWork\\FileCopy\\src\\Copy\\Redirecting.java"));
	PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream("test.out")));
	System.setIn(in); System.setOut(out); System.setErr(out);//标准输入、输出、错误重定向
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String s;//创建一个由标准输入对象构建的 “带缓冲的输入流”
	
	while((s=br.readLine())!=null) System.out.println(s);//一行一行的读文本
	in.close();
	out.close();
	}
}
