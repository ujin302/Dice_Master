package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.PriorityQueue;

import dto.GameHistoryDTO;
import dto.MemberDTO;
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
	
	public MemberDTO findMemberId(String user_id) {
		MemberDTO memberDTO = new MemberDTO();
		
		sql = "select * from member where user_id = ?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				memberDTO.setUser_ID(rs.getString("user_id"));
				memberDTO.setUser_Name(rs.getString("user_name"));
				memberDTO.setReward(rs.getInt("reward"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return memberDTO;
	}
	
	
	
	//게임 시작 시 user_id, nickname, time_start
	public int startgameHistory(String user_id) {
		int num = 0;
		// member : id, name 추출하는 함수 호출
		MemberDTO memberDTO = findMemberId(user_id);
		// GAME Start Time
		LocalDateTime time_start = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		sql = "insert into gameHistory values (?, ?, ?, ?, ?)";
		
		try {
			super.con = super.getConnection();
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, memberDTO.getUser_ID());
			pstmt.setString(2, memberDTO.getUser_Name());
			pstmt.setInt(3, 0);
			pstmt.setTimestamp(4, Timestamp.valueOf(time_start));
			pstmt.setTimestamp(5, null);

			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println();
			System.out.println( "sql"+ e.getSQLState());
			System.out.println("ErrorCode : " + e.getErrorCode());
			System.out.println( "msg"+ e.getLocalizedMessage());
			
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return num;
	}
	
	public GameHistoryDTO findId(String id) { // userinfo 에서 사용 
		GameHistoryDTO ghsDTO = null;
		sql = "select * from gamehistory where user_id = ?";
		
		try {
			super.con = super.getConnection(); 
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ghsDTO = new GameHistoryDTO(rs.getString("user_id"),
						rs.getString("nickname"),
						rs.getInt("reward"),
						rs.getTimestamp("time_start"),
						rs.getTimestamp("time_over"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeDB(con, pstmt, rs);
		}
		
		return ghsDTO;
	}
	
	// User_id를 통해 nickname을 조회
    public String getNicknameById(String user_id) {
        String nickname = null;
        sql = "select user_name from member where user_id = ?";
        
        try {
            super.con = super.getConnection();
            super.pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user_id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                nickname = rs.getString("user_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeDB(con, pstmt, rs);
        }
        
        return nickname;
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

	public int endgameHistory(String user_id, int reward) {
		int num = 0;
		// Game end time
		LocalDateTime time_over = LocalDateTime.now();
		sql = "update gameHistory set time_over=?, reward=? where user_id = ?"; // 1 게임 종료 후, 결과 업데이트 
		
		try {
			super.con = super.getConnection();
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setTimestamp(1,  Timestamp.valueOf(time_over));
			pstmt.setInt(2, reward);
			pstmt.setString(3, user_id);
			
//			findId.setReward(findId.getReward() - gameEntryFree);
			
			num = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return num;
	}
	
	public int updateTotalReward(String user_id, int reward) {
		int num = 0;
		int setReward = findMemberId(user_id).getReward() - RoleService.GAMECOIN + reward;
		sql = "update member set reward=? where user_id = ?";
		
		try {
			super.con = super.getConnection();
			super.pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, setReward);
			pstmt.setString(2, user_id);
			num = pstmt.executeUpdate();
			System.out.println(num);
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			super.closeDB(con, pstmt);
		}
		
		return num;
	}
}
