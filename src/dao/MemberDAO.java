package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDTO;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberDAO extends BaseDAO {
	private static MemberDAO instance;
	String sql;
	
	public static MemberDAO getInstance() {
		if(instance == null) {
			synchronized (MemberDAO.class) {
				instance = new MemberDAO();
			}
		}
		
		return instance;
	}
	
	// 회원정보 저장
	public int saveMember(MemberDTO memberDTO) {
		int num = 0;
		/*
		 * con 변수는 부모 클래스인 BaseDAO 변수이다. 
		 * 따라서 MemberDAO에서 con를 초기화해줘야 한다. 
		 */
		
		
		sql = "insert into member values (?, ?, ?, ?, ?)";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			num = pstmt.executeUpdate();
			
			System.out.println(num);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return num;
	}
	
	// 회원정보 저장, 매개변수 : id, pw, num(1 or 2)
	public int loginMember() {
		int num = 0;
		// git commit		
		return num;
	}
}
