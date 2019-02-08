package com.czz.oracle.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
/*sunline 开发环境
 * 
 * 
 * 包头定义开始
 * create or replace package pkg_ch_sys is
 * 过程的声明
 *   procedure ch_pl_getfcbpsq
  (
    pi_subsys in varchar2, --子系统名
    po_workdt out varchar2, --前置日期
    po_fcbpsq out varchar2, --前置流水
    po_systti out varchar2, --系统时间
    po_erorcd out varchar2, --返回码（0：成功 ，其他：失败）
    po_erortx out varchar2 --错误信息
  );
  包头的定义结束
  end pkg_ch_sys;
 * 包体定义开始
 *create or replace package body pkg_ch_sys is
 *--以下是存储过程
 *--  获取平台标识的过程
 *  procedure ch_pl_getfcbpsq
  (
    pi_subsys in varchar2, --子系统名
    po_workdt out varchar2, --前置日期
    po_fcbpsq out varchar2, --前置流水
    po_systti out varchar2, --系统时间
    po_erorcd out varchar2, --返回码（0：成功 ，其他：失败）
    po_erortx out varchar2 --错误信息
  )  is .......
  end pkg_ch_sys;
  -----------包定义结束
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
			//in 参数 赋值
			//call.setInt(1, 7839);
			call.setString(1, "IBPS");
			//out /return参数     如果在out参数中使用光标，就必须用包 create or replace package mypackage as
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
			
			//return/out 参数     
			call.registerOutParameter(1, OracleTypes.NUMBER);
			//in 参数 赋值
			call.setInt(2, 7839);
			//执行
			call.execute();
			//取返回值
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
			//in 参数 赋值
			call.setInt(1, 10);
			//out /return参数     如果在out参数中使用光标，就必须用包 create or replace package mypackage as
			call.registerOutParameter(2, OracleTypes.CURSOR);
			call.execute();
			//取出结果
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
