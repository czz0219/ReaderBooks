package com.daytimethreadserver;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MultithreadedDaytimeServer {
	public final static int PORT =13;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (ServerSocket server = new ServerSocket(PORT)){
			while(true) {
				Socket connection  = server.accept();
				Thread t = new DaytimeThread(connection);
				t.start();//此处线程多数情况并没有执行完任务
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static class DaytimeThread extends Thread{
		private  Socket connection;
		DaytimeThread(Socket connection){
			this.connection = connection;
		}
		public void run() {
			try {
				Writer out = new OutputStreamWriter(connection.getOutputStream());
				Date now = new Date();
				out.write(now.toString()+"\n\r");
				out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					connection.close();
				} catch(IOException e) {}
			
				}
		}
	}
}
