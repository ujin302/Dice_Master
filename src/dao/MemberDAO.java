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
	public int joinMeberData(MemberDTO memberDTO) {
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
<<<<<<< HEAD
	public MemberDTO loginMember(String userID, String userPW) { // 매개변수 ID, PW 
		/*
		 * loginMember() 함수 사용 이유 : member(T)에서 id와 pw가 일치하는 한 행을 가져오겠다. 
		 * 
		 * 매개변수 필요해??? 
		 * 
		 * 방법 1. 매개변수 : id, pw 
		 * 방법 2. 매개변수 : memberDTO -> 4개 : null 
		 * 방법 3. 매개변수 : String[] = {id, pw} -> 인덱스 번호 기억  
		 * 
		 * 반환값 ? 
		 * 성공 여부만 확인? >> boolean
		 * 성공했다면 로그인 정보도 출력? -> DTO, Map, Array
		 * 	-> 환영합니다 "ooo" 님, 현재 잔액 ~ 
		 * 	-> MemberDTO : 해당 사용자의 모든 정보를 담고 있다.
		 * 
		 * 
		 * [ 작업 내용 ]
		 * 존재 O 
		 * 	로그인 성공 의미
		 * 존재 X 
		 * 	로그인 실패 의미 
		 * 
		 */
=======
	public MemberDTO loginMember(String userID, String userPW) {
		MemberDTO dto = null;
		sql = "select * from member where user_id = ?, user_pw =?";
>>>>>>> a6b49b1e965344ca92351391df802774f810d51a
		
		MemberDTO dto = null; // ? 반환 시, 사용하는 DTO 
		sql = "select * from member where uesr_id = ?, uesr_pw =?"; // ? 
		
		try { // ? 
			super.con = super.getConnection(); // 드라이버 연결한 데이터 저장 
			super.pstmt = con.prepareStatement(sql); // sql 컴파일 
			
			pstmt.setString(1, userID); // id = ? 데이터 매핑
			pstmt.setString(2, userPW);
			
			rs = pstmt.executeQuery(); // sql 실행 & rs에 결과값 저장 
			
			while(rs.next()) { // rs 다음꺼 들고오기 있으면 T, 없으면 F
				// 다음꺼 정보 저장 
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
			e.printStackTrace(); // e 변수에 try 예외 정보 전달. 빨간줄 출력 
		} finally {
			super.closeDB(con, pstmt, rs); // 편하게 가져다 쓰기 위해서 함수 호출 
		}
		
		
		return dto; 
		
	}

	// 사용자 & 관리자 탈퇴
	public int deleteMember(String userID, String userPW) {
		int num = 0;

		sql = "delete from member where user_id = ?, user_pw =?";
		
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

		sql = "delete from member where reward = ?";
		
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
		
		return exist;
	}
	
}
