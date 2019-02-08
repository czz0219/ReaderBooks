package com.czz.oracle.procedure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*
 * java 的GC（）
 * java -Xms100M -Xmx200M helloworld  --运行 helloworld占用堆内存所允许的大小是100-200M
 * 
 * 技术方向
 * 1.故障诊断: 死锁 ： ThreadDump 会定位到死锁位置;性能瓶颈;  linux :kill -3 pid   win   :ctrl+break 
 * 2.性能调优:
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
				st = null; //java gc()请求回收堆内存; 垃圾回收机制，对于null对象优先回收
			}
		}
	}
}
