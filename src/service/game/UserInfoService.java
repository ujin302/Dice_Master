package service.game;

import dao.GameHistoryDAO;
import dto.GameHistoryDTO;
import service.Game;

public class UserInfoService implements Game {
	
	@Override
	public void execute(String uesr_id) {
		GameHistoryDAO gameHistoryDAO = GameHistoryDAO.getInstance(); //싱글톤
	    GameHistoryDTO gameHistoryDTO = gameHistoryDAO.findId(uesr_id); //게임기록검색
	
	    //gameHistoryDTO가 null이 아닐때만 출력(게임기록)
	    if (gameHistoryDTO != null) {
	        System.out.println("닉네임 : " + gameHistoryDTO.getNickname());
	        System.out.println("보유 코인 : " + gameHistoryDTO.getReward());
	        
	    }else {
	    	System.out.println("조회 결과 기록이 없습니다.");
	    }
    }
}

