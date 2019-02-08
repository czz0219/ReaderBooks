package com.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer {
	public final static int PORT =13;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (ServerSocket server = new ServerSocket(PORT)){
			while(true) {
				try(Socket connection = server.accept()){
					Writer out = new OutputStreamWriter(connection.getOutputStream());
					//������Ϣ
					Date now = new Date();
					out.write("current time:"+now.toString()+"\n\r");
					out.flush();
					connection.close();
					System.out.println("���ӹر�");
				}catch(IOException ex) {
					System.out.println("�������ӳ���");
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
