package com.czz.oracle.procedure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * java ��GC����
 * java -Xms100M -Xmx200M helloworld  --���� helloworldռ�ö��ڴ�������Ĵ�С��100-200M
 * 
 * ��������
 * 1.�������: ���� �� ThreadDump �ᶨλ������λ��;����ƿ��;  linux :kill -3 pid   win   :ctrl+break 
 * 2.���ܵ���:
 * */
public class JDBCUtils {
	private static String driver ="oracle.jdbc.OracleDriver";
	private static String url	 ="jdbc:oracle:thin:@172.20.13.14:1521:jtqz";
	private static String user ="sunfcbp";
	private static String password ="sunfcbp";
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public static void release(Connection conn,Statement st,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				rs = null;
			}
		}
		
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				conn = null;
			}
		}
		
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				st = null; //java gc()������ն��ڴ�; �������ջ��ƣ�����null�������Ȼ���
			}
		}
	}
}
