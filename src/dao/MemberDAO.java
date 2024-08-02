package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.MemberDTO;
import lombok.NoArgsConstructor;
import service.RoleService;

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
	
	// 사용자 & 관리자 회원가입
	public int joinMemberData(MemberDTO memberDTO) {
		int num = 0;
		/*
		 * con 변수는 부모 클래스인 BaseDAO 변수이다. 
		 * 따라서 MemberDAO에서 con를 초기화해줘야 한다. 
		 */
		sql = "insert into member values (?, ?, ?, ?, ?, ?)";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
		
			pstmt.setString(1, memberDTO.getUser_Name());
			pstmt.setString(2, memberDTO.getUser_ID());
			pstmt.setString(3, memberDTO.getUser_PW());
			pstmt.setString(4, memberDTO.getUser_Email());
			pstmt.setString(5, memberDTO.getRole());
			pstmt.setInt(6, memberDTO.getReward());
			
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return num;
	}

	// 사용자 & 관리자 로그인
	public MemberDTO loginMember(String userID, String userPW) {
		MemberDTO dto = null;
		sql = "select * from member where user_id = ? and user_pw =?";


		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				dto = new MemberDTO(
									rs.getString("user_Name"),
									rs.getString("user_ID"),
									rs.getString("user_PW"),
									rs.getString("user_Email"),
									rs.getString("role"),
									rs.getInt("reward")
								);
			}
			
		} catch (SQLException e) { 
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return dto; 
	}
		
	// 사용자 & 관리자 탈퇴
	public int deleteMember(String userID, String userPW) {
		int num = 0;

		sql = "delete from member where user_id = ? and user_pw =?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userID);
			pstmt.setString(2, userPW);
			
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return num;
	}

	// 사용자 삭제
	public int deleteUser(String userID) {
		int num = 0;

		sql = "delete from member where user_id = ?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userID);
			pstmt.setString(2, RoleService.USER);
			
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return num;
	}

	public ArrayList<MemberDTO> listUser() {
		ArrayList<MemberDTO> dtoList = new ArrayList<MemberDTO>();
		
		sql = "select * from member where role = ?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, RoleService.USER);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO(
											rs.getString("user_Name"),
											rs.getString("user_ID"),
											rs.getString("user_PW"),
											rs.getString("user_Email"),
											rs.getString("role"),
											rs.getInt("reward")
										);
				
				dtoList.add(dto);
			}	
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return dtoList;
	}
	
	public int controlUser(int standard) {
		int num = 0;

		sql = "delete from member where reward > ?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, standard);
			
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return num;		
	}
	
	public int updateAdmin(MemberDTO memberDTO) {
		int exist = 0;
		
		sql = "update member set user_name=?, user_pw=?, user_email=? where user_id=? and role = ?";
		
		try {
			super.con = super.getConnection();
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberDTO.getUser_Name());
			pstmt.setString(2, memberDTO.getUser_PW());
			pstmt.setString(3, memberDTO.getUser_Email());
			pstmt.setString(4, memberDTO.getUser_ID());
			pstmt.setString(5, RoleService.ADMIN);
			
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
		
		sql = "update member set user_name=?, user_pw=?, user_email=? where user_id=? and role = ?";
		
		try {
			super.con = super.getConnection();
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberDTO.getUser_Name());
			pstmt.setString(2, memberDTO.getUser_PW());
			pstmt.setString(3, memberDTO.getUser_Email());
			pstmt.setString(4, memberDTO.getUser_ID());
			pstmt.setString(5, RoleService.USER);
			
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return result;
	}
	
	public MemberDTO findId(String id) {
		MemberDTO dto = null;
		sql = "select * from member where user_id = ?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto = new MemberDTO(
						rs.getString("user_Name"),
						rs.getString("user_ID"),
						rs.getString("user_PW"),
						rs.getString("user_Email"),
						rs.getString("role"),
						rs.getInt("reward")
					);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return dto;
	}
	
	public boolean findName(String user_name) {
		boolean exist = false;
		sql = "select * from member where user_name = ?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_name);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) exist = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return exist;
	}
	
	public boolean findEmail(String user_email) {
		boolean exist = false;
		sql = "select * from member where user_email = ?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) exist = true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return exist;
	}
	
}
