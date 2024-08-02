package service.game;

import dao.GameHistoryDAO;
import dto.GameHistoryDTO;
import dto.MemberDTO;
import service.Game;

public class UserInfoService implements Game {
	
	@Override
	public void execute(String user_id) {
		GameHistoryDAO gameHistoryDAO = GameHistoryDAO.getInstance(); //싱글톤
	    GameHistoryDTO gameHistoryDTO = gameHistoryDAO.findId(user_id); //게임기록검색
	    MemberDTO memberDTO = gameHistoryDAO.findMemberId(user_id);
	    //gameHistoryDTO가 null이 아닐때만 출력(게임기록)
	    if (gameHistoryDTO != null) {
	    	String[] result = new RankingService().userRank(user_id, gameHistoryDAO.getRank(), false);
		    // 2번 매개 변수 : 랭킹 추출 & String[] = {user_id, nickname, 최고 보상}
		    if(result == null) { // 게임을 진행한적 없을 경우 
				System.out.println("\n게임 플레이 후, 순위를 확인해주세요. ");
			} else {
				System.out.println("\n" + result[0] + "님은 상위 " + result[2] + "%입니다.");
			}
	        System.out.println("닉네임 : " + gameHistoryDTO.getNickname());
	        System.out.println("보유 코인 : " + memberDTO.getReward());
	        
	    }else {
	    	System.out.println("조회 결과 기록이 없습니다.");
	    }
    }
}