package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.PriorityQueue;

import dto.GameHistoryDTO;
import lombok.NoArgsConstructor;
import service.RoleService;

@NoArgsConstructor
public class GameHistoryDAO extends BaseDAO{
	// Singleton 인스턴스를 저장할 static 변수
	private static GameHistoryDAO instance;
	// sql 쿼리를 저장할 문자열 변수
	String sql;
	
	//Singleton 패턴을 구성한 메소드로 , GameHistory 클래스의 유일한 인스턴스 반환
	public static GameHistoryDAO getInstance() {
		//인스턴스가 아직 생성되지 않은 경우
		if(instance == null) {
			//멀티스레드 환경에서 안전하게 인스터스를 생성하기 위해 synchronized 블록 사용
			synchronized (GameHistoryDAO.class) { 
				instance = new GameHistoryDAO();
			}
		}
		return instance;
	}
	//게임 시작 시 user_id, nickname, time_start
	public int saveGameHistory(GameHistoryDTO gameHistoryDTO) {
		int num = 0;
		
		Connection con = super.getConnection();
		
		
		return num;
		
	}
	public GameHistoryDTO findId(String id) {
		GameHistoryDTO ghsDTO = null;
		sql = "select * from member where user_id = ?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ghsDTO = new GameHistoryDTO(rs.getString("user_id"),
						rs.getString("nikname"),
						rs.getString("reward"),
						rs.getString("game_start"),
						rs.getString("game_over"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return ghsDTO;
	}
	
	public ArrayList<String> getNicknameList() { // member.User_Id 추출 
		ArrayList<String> nicknameList = new ArrayList<String>();
		
		sql = "select user_name from member";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				nicknameList.add(rs.getString("user_name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return nicknameList;
	}
	
	public PriorityQueue<String[]> getRank() {
		ArrayList<String> nicknameList = getNicknameList();
		PriorityQueue<String[]> rankQueue = new PriorityQueue<>((o1, o2) -> Integer.parseInt(o2[2]) - Integer.parseInt(o1[2])); 
		// user_id, nickname, 최고 보상  >> 내림차순
		
		sql = "select * from gamehistory where nickname = ? order by reward desc"; // 보상 기준 내림차순
		
		try {
			super.con = super.getConnection();
			for(String n : nicknameList) {
				System.out.println(n);
				String[] data = new String[3];
				super.pstmt = con.prepareStatement(sql);
				pstmt.setString(1, n);

				rs = pstmt.executeQuery();
				
				
				if(rs.next()) { // 사용자의 게임 정보가 있을 경우에만 실행
					data[0] = rs.getString("user_id");
					data[1] = n;
					data[2] = rs.getInt("reward")+"";

					rankQueue.offer(data);
				}else continue;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return rankQueue;
	}
}
