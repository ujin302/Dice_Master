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
	public int loginMember(String id, String pw) {
		int num = 0;
		sql = "select * from member where user_id =?, user_pw=?";
		
		try {
			super.con = super.getConnection();
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			num = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt);
	}
			
		return num;
	}

	public boolean findId(String id) {
		boolean exist = false;
		sql = "select * from member where user_id = ?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) exist = true;
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return true;
	}
	//관리자 정보 수정
	public int updateAdmin(MemberDTO memberDTO) {
		int exist = 0;
		
		sql = "update member set admin_name=?, user_pw=?, user_email=? where user_id=?";
		
		try {
			super.con = super.getConnection();
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberDTO.getUser_Name());
			pstmt.setString(2, memberDTO.getUser_PW());
			pstmt.setString(3, memberDTO.getUser_Email());
			pstmt.setString(4, memberDTO.getUser_ID());
			
			exist = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return exist;
		
	}
	//사용자 정보 수정
	public int updateUser(MemberDTO memberDTO) {
		int result = 0;
		
		sql = "update member set admin_name=?, user_pw=?, user_email=? where user_id=?";
		
		try {
			super.con = super.getConnection();
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberDTO.getUser_Name());
			pstmt.setString(2, memberDTO.getUser_PW());
			pstmt.setString(3, memberDTO.getUser_Email());
			pstmt.setString(4, memberDTO.getUser_ID());
			
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return result;
	}
}