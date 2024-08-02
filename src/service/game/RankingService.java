package service.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import dao.GameHistoryDAO;
import service.Game;

public class RankingService implements Game {

	public String[] userRank(String user_id, PriorityQueue<String[]> rankQueue, boolean isPrint) {
		String[] result = new String[3]; // {nickname, 등수 ,상위 %}
		int size = rankQueue.size();
		
		for(int i=0; i<size; i++) {
			String[] rArr = rankQueue.poll();
			
			if(isPrint) System.out.print( (i+1) + "\t|\t" + rArr[1] + "\t" + rArr[2]);
			
			if(user_id.equals(rArr[0])) {
				if(isPrint) System.out.print(" << 사용자 순위 ");
				result[0] = rArr[1]; // nickname
				result[1] = (i+1) + ""; // 등수
				double topN = (double) (i+1) / size * 100;
				result[2] = String.format("%.2f", topN);  // 상위 %						
			}
			if(isPrint) System.out.println();
		}
		
		return result;
	}
	
	@Override
	public void execute(String user_id) {
		/*
		 * [ 랭킹 ]
		 * 1. user_idList = member.user_id 리스트 담기 
		 * 2. 각 user_id 별 최고 보상 반환 
		 * 3. 
		 */
		
		GameHistoryDAO gameHistoryDAO = GameHistoryDAO.getInstance();
		PriorityQueue<String[]> rankQueue = gameHistoryDAO.getRank(); // 랭킹 추출 & String[] = {user_id, nickname, 최고 보상}
		String[] result = null;
		System.out.println("----------------- Rangking ------------------");
		
		result = userRank(user_id, rankQueue, true);
		
		
		
		if(result == null) { // 게임을 진행한적 없을 경우 
			System.out.println("\n게임 플레이 후, 순위를 확인해주세요. ");
		} else {
			System.out.println("\n" + result[0] + "님은 순위는 " + result[1] + "등 이며, 상위 " + result[2] + "%입니다.");
		}
		
		System.out.println("--------------------------------------------");
		System.out.println();
	}

}
