package com.threadpool.server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PooledDaytimeServer {
	public final static int PORT =13;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService pool = Executors.newFixedThreadPool(10);
		try (ServerSocket server = new ServerSocket(PORT)){
			while(true) {
				//���һ��socket
				Socket connection = server.accept();
				//����һ���ɵ��õ� Callable
				Callable<Void> call_thread_instance = new DaytimeTask(connection);
				//�����̳߳���
				pool.submit(call_thread_instance);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ServerSocket ��������");
		}

	}
	
	private static class DaytimeTask implements Callable<Void>{
		private Socket connection;
		
		DaytimeTask(Socket connection){
			this.connection = connection;
		}
		@Override
		public Void call() {
			// TODO Auto-generated method stub
			try {
				
				 Writer out = new OutputStreamWriter(connection.getOutputStream());
				Date now = new Date();
				out.write(now.toString()+"\r\n");
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
	}

}
