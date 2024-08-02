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
		
		
		int size = rankQueue.size();
		int rank = 0;
		String nickName = null;
		
		System.out.println("----------------- Rangking ------------------");
		
		for(int i=0; i<size; i++) {
			String[] rArr = rankQueue.poll();
			System.out.print( (i+1) + "\t|\t" + rArr[1] + "\t" + rArr[2]);
			
			if(user_id.equals(rArr[0])) {
				System.out.print(" << 사용자 순위 ");
				rank = i+1;
				nickName = rArr[1];
			}
		}
		
		if(nickName == null) { // 게임을 진행한적 없을 경우 
			System.out.println("\n게임 플레이 후, 순위를 확인해주세요. ");
		} else {
			System.out.println("\n" + nickName + "님은 순위는 " + rank + "등 이며, 상위 " + (double) rank / size * 100 + "%입니다.");
		}
		
		System.out.println("--------------------------------------------");
		System.out.println();
	}

}
