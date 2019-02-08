package com.czz.oracle.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
/*
 * create or replace procedure queryempinfo(eno in number,
                                         pename out varchar2,
                                         pjob   out varchar2,
                                         psal   out number)
 * */

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

public class TestProcedure {
	@Test
	public void testProcedure() {
		Connection conn = null;
		CallableStatement call = null;
		//{call <procedure-name>[(<arg1>,<arg2>,..)]}
		String sql ="{call queryempinfo(?,?,?,?)}";
		try {
			conn = JDBCUtils.getConnection();
			call = conn.prepareCall(sql);
			//in ���� ��ֵ
			call.setInt(1, 7839);
			//out /return����     �����out������ʹ�ù�꣬�ͱ����ð� create or replace package mypackage as
			call.registerOutParameter(2, OracleTypes.VARCHAR);
			call.registerOutParameter(3, OracleTypes.VARCHAR);
			call.registerOutParameter(4, OracleTypes.NUMBER);
			call.execute();
			String name = call.getString(2);
			String job = call.getString(3);
			double sal= call.getDouble(4);
			System.out.println(name +"\t"+job+"\t"+sal);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.release(conn, call, null);
		}
		
		
	}
	
	/*
	 * create or replace function queryEmpIncome(eno in number) return number
*/
	@Test
	public void testFunction() {
	
		Connection conn = null;
		CallableStatement call = null;
		// {?=call <procedure-name>[(<arg1>,<arg2>,....)]}
		String sql ="{?=call queryEmpIncome(?)}";
		try {
			conn = JDBCUtils.getConnection();
			call = conn.prepareCall(sql);
			
			//return/out ����     
			call.registerOutParameter(1, OracleTypes.NUMBER);
			//in ���� ��ֵ
			call.setInt(2, 7839);
			//ִ��
			call.execute();
			//ȡ����ֵ
			double sal= call.getDouble(1);
			System.out.println(+sal);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.release(conn, call, null);
		}
	}
	
	@Test
	public void testCursor() {
		
		Connection conn = null;
		CallableStatement call = null;
		//{call <procedure-name>[(<arg1>,<arg2>,..)]}
		String sql ="{call mypackage.queryEmpList(?,?)}";
		ResultSet rs =null;
		try {
			conn = JDBCUtils.getConnection();
			call = conn.prepareCall(sql);
			//in ���� ��ֵ
			call.setInt(1, 10);
			//out /return����     �����out������ʹ�ù�꣬�ͱ����ð� create or replace package mypackage as
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			//ȡ�����
			rs = ((OracleCallableStatement)call).getCursor(2);
			while(rs.next()) {
				String name = call.getString(2);
				String job = call.getString(3);
				double sal= call.getDouble(4);
				System.out.println(name +"\t"+job+"\t"+sal);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.release(conn, call, null);
		}
	}

}
