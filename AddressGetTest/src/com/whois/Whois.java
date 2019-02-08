package com.whois;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Whois {

	public static void main(String[] args)throws Exception{
		// TODO Auto-generated method stub
		Socket s = new Socket("whois.internic.net",43);
		InputStream in = s.getInputStream();
		OutputStream out = s.getOutputStream();
		String cmd ="MHProfessional.com";
		byte cmd_byte[] = cmd.getBytes();
		out.write(cmd_byte);
		
		int c;
		while((c = in.read())!=-1) {
			System.out.println((char)c);
		}
		s.close();
	}

}
