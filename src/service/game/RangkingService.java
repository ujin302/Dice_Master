package service.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import dao.GameHistoryDAO;
import service.Game;

public class RangkingService implements Game {

	@Override
	public void execute(String user_id) {
		/*
		 * [ 랭킹 ]
		 * 1. user_idList = member.user_id 리스트 담기 
		 * 2. 각 user_id 별 최고 보상 반환 
		 * 3. 
		 */
		
		GameHistoryDAO gameHistoryDAO = GameHistoryDAO.getInstance();
		PriorityQueue<String[]> rankQueue = gameHistoryDAO.getRank(); // 랭킹 추출
		
		
		for(String[] rArr : rankQueue) {
			System.out.println(rArr[0] + "\t|\t" + rArr[1]);
		}
	}

}
