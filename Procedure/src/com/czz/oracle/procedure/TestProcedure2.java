package com.czz.oracle.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
/*sunline ��������
 * 
 * 
 * ��ͷ���忪ʼ
 * create or replace package pkg_ch_sys is
 * ���̵�����
 *   procedure ch_pl_getfcbpsq
  (
    pi_subsys in varchar2, --��ϵͳ��
    po_workdt out varchar2, --ǰ������
    po_fcbpsq out varchar2, --ǰ����ˮ
    po_systti out varchar2, --ϵͳʱ��
    po_erorcd out varchar2, --�����루0���ɹ� ��������ʧ�ܣ�
    po_erortx out varchar2 --������Ϣ
  );
  ��ͷ�Ķ������
  end pkg_ch_sys;
 * ���嶨�忪ʼ
 *create or replace package body pkg_ch_sys is
 *--�����Ǵ洢����
 *--  ��ȡƽ̨��ʶ�Ĺ���
 *  procedure ch_pl_getfcbpsq
  (
    pi_subsys in varchar2, --��ϵͳ��
    po_workdt out varchar2, --ǰ������
    po_fcbpsq out varchar2, --ǰ����ˮ
    po_systti out varchar2, --ϵͳʱ��
    po_erorcd out varchar2, --�����루0���ɹ� ��������ʧ�ܣ�
    po_erortx out varchar2 --������Ϣ
  )  is .......
  end pkg_ch_sys;
  -----------���������
 * */

import oracle.jdbc.driver.OracleCallableStatement;
import oracle.jdbc.driver.OracleTypes;

public class TestProcedure2 {
	@Test
	public void testProcedure() {
		Connection conn = null;
		CallableStatement call = null;
		//{call <procedure-name>[(<arg1>,<arg2>,..)]}
		String sql ="{call pkg_ch_sys.ch_pl_getfcbpsq(?,?,?,?,?,?)}";
		try {
			conn = JDBCUtils.getConnection();
			call = conn.prepareCall(sql);
			//in ���� ��ֵ
			//call.setInt(1, 7839);
			call.setString(1, "IBPS");
			//out /return����     �����out������ʹ�ù�꣬�ͱ����ð� create or replace package mypackage as
			call.registerOutParameter(2, OracleTypes.VARCHAR);
			call.registerOutParameter(3, OracleTypes.VARCHAR);
			call.registerOutParameter(4, OracleTypes.VARCHAR);
			call.registerOutParameter(5, OracleTypes.VARCHAR);
			call.registerOutParameter(6, OracleTypes.VARCHAR);
			call.execute();
			Map<String,Object> retPltInfo = new HashMap<String,Object>();
			retPltInfo.put("fcbpdt", call.getString(2));
			retPltInfo.put("fcbpsq", call.getString(3));
			retPltInfo.put("systi", call.getString(4));
			retPltInfo.put("retCd", call.getString(5));
			retPltInfo.put("retRsp", call.getString(6));
			System.out.println(retPltInfo);
			
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
