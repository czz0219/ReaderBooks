package connect;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToOracle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver ="oracle.jdbc.driver.OracleDriver";
		String url ="jdbc:Oracle:thin:@localhost:1521:Glb";
		Statement stmt = null;
		ResultSet res = null;
		Connection conn=null;
		CallableStatement proc= null;
		String sql ="select * from kaifa";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url,"system","root");
			stmt =conn.createStatement();
			res = stmt.executeQuery(sql);
			while(res.next())
			{
				String GroupCode = res.getString("GROUP_CODE");
				String ChildCompanyName =res.getString("CHILD_COMPANY_NNAME");
				int GroupPersonNum = res.getInt("GROUP_PERSON_NUM");
				String GroupAddress = res.getString("GROUP_ADDRESS");
				String GroupLeaderName = res.getString("GROUP_LEADER_NAME");
				String ProjectName = res.getString("GROUP_PROJECT_NAME");
				System.out.println("½á¹û£º"+GroupCode+":"+ChildCompanyName+":"+GroupPersonNum+":"+GroupAddress+":"+GroupLeaderName+":"+ProjectName+"\n");
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
