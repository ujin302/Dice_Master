package dao;

import lombok.NoArgsConstructor;

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
	
}
