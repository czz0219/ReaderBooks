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
	System.setIn(in); System.setOut(out); System.setErr(out);//��׼���롢����������ض���
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String s;//����һ���ɱ�׼������󹹽��� �����������������
	
	while((s=br.readLine())!=null) System.out.println(s);//һ��һ�еĶ��ı�
	in.close();
	out.close();
	}
}
