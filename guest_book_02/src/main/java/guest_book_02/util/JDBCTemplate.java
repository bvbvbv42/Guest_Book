package guest_book_02.util;  // 데이터접근하는 모든일을 함

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplate {

	public static Connection getConnection() {
		Connection conn = null;
		try {
			String url = "jdbc:mariadb://127.0.0.1:3306/guest_book";
			String user = "root";
			String pw = "1234";
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pw);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			if(conn != null && conn.isClosed() == false) {
				conn.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null && pstmt.isClosed() == false) {
				pstmt.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		try {
			if(rs != null && rs.isClosed() == false) {
				rs.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
		
	//overload = 위와같이 close에 매개변수만다르고 내용은 다 같음
	
}
	
