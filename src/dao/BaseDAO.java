package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String username = "hr";
	private String password = "hr";
	
	protected Connection con;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	public BaseDAO() {
		try {
			Class.forName(driver); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// DB 연결
	public Connection getConnection() {
		try {// 드라이버 연결시 오류 발생 할 경우 대비 
			// con = 연결된 정보 담음. DriverManager : 오라클 DB의 드라이버와 자바 프로젝트 연결 시, 사용 
			DriverManager.setLoginTimeout(10); 
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	// DB 종료 1
	public void closeDB(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// DB 종료 2
	public void closeDB(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int saveMember(MemberDTO memberDTO) {
		int num = 0;
		getConnection();
		
		String sql = "select * from member";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			System.out.println(rs.next());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeDB(con, pstmt, rs);
		}
		
		
		return num;
	}
}
