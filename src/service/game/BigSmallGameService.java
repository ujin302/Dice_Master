package service.game;

import java.util.Random;
import java.util.Scanner;

import dao.GameHistoryDAO;
import dto.MemberDTO;
import service.Game;
import service.RoleService;

public class BigSmallGameService implements Game{
	private Scanner scanner;
	private Random random;
	private GameHistoryDAO gameHistoryDAO;
	
	
	public BigSmallGameService() {
		scanner = new Scanner(System.in);
		random = new Random();
		gameHistoryDAO = GameHistoryDAO.getInstance();
		
		return;
	}
	
	// 게임 시작 시, 문구 출력 
	public void printBanner() {
		System.out.println("=====================================================================================");
		String[] b = {
                "  ____   ",
                " |  _ \\  ",
                " | |_) | ",
                " |  _ <  ",
                " | |_) | ",
                " |____/  "
        };

        String[] i = {
                "  _____  ",
                " |_   _| ",
                "   | |   ",
                "   | |   ",
                "  _| |_  ",
                " |_____| "
        };

        String[] g = {
                "   ____  ",
                "  / ___| ",
                " | |  _  ",
                " | |_| | ",
                "  \\____| ",
                "         "
        };

        String[] s = {
                "  _____  ",
                " / ____| ",
                " | (___  ",
                "  \\___ \\ ",
                "  ____) |",
                " |_____/ "
        };

        String[] m = {
                "  __  __  ",
                " |  \\/  | ",
                " | |\\/| | ",
                " | |  | | ",
                " | |  | | ",
                " |_|  |_| "
        };

        String[] a = {
                "    _    ",
                "   / \\   ",
                "  / _ \\  ",
                " / ___ \\ ",
                "/_/   \\_\\",
                "         "
        };

        String[] l = {
                "  _       ",
                " | |      ",
                " | |      ",
                " | |      ",
                " | |____  ",
                " |______| "
        };

 
        for (int line = 0; line < b.length; line++) {
            System.out.println(b[line] + " " + i[line] + " " 
                               + g[line] + "    " + s[line] + " " 
            		           + m[line] + " " + a[line] + " " 
                               + l[line] + " " + l[line]);
        }
        System.out.println("=====================================================================================");
	}
	
	
	public int startGame() {
		int reward = 0;
		int attempts = 3;
        boolean success = false;
        int userGuess = 0;
        int dice1 = 0, dice2 = 0, dice3 = 0;
        int diceSum = 0;
        
        System.out.println("※주사위를 생성하고 있습니다. 잠시만 기다려 주세요!※");
        
        try {
        	//3s delay
        	Thread.sleep(2000);
        }catch(InterruptedException e) {
        	e.printStackTrace();
        }
        System.out.println(".");
        try {
        	//3s delay
        	Thread.sleep(2000);
        }catch(InterruptedException e) {
        	e.printStackTrace();
        }
        System.out.println(".");
        try {
        	//3s delay
        	Thread.sleep(2000);
        }catch(InterruptedException e) {
        	e.printStackTrace();
        }
        System.out.println(".");
        
       try {
    	   Thread.sleep(2000);
       }catch(InterruptedException e) {
    	   e.printStackTrace();
       }
        
       System.out.println("!!주사위 생성 완료!!");
       System.out.println("=======================================");
	   
	   dice1 = random.nextInt(6) + 1;
	   dice2 = random.nextInt(6) + 1;
	   dice3 = random.nextInt(6) + 1;
	   diceSum = dice1 + dice2 + dice3;
	   
       while (attempts > 0) {
    	   System.out.print("주사위의 합을 맞춰보세요 (3~18): ");
    	   userGuess = scanner.nextInt();
    	   
    	   if (userGuess == diceSum) {
			    success = true;
			    switch (3 - attempts + 1) {
			        case 1:
			        	reward = RoleService.GAME1;
			            break;
			        case 2:
			        	reward = RoleService.GAME2;
			        	break;
			        case 3:
			        	reward = RoleService.GAME3;
			            break;
			    }
			    System.out.println("축하합니다! " + (4 - attempts) + "회차에 성공했습니다!");
			    System.out.println("상금, " + reward + "코인 획득");
			    break;
			} else {
			    attempts--;
			    if (attempts > 0) {
			        System.out.println("틀렸습니다. " + attempts + "회차가 남았습니다.");
			    }
			}
        }

        if (!success) {
            System.out.println("모든 기회를 소진했습니다. 주사위 합은" + diceSum + "이었습니다.");
            System.out.println();
            System.out.println("=======================================");
        }
        
        return reward;
	}
	
	
	@Override
	public void execute(String user_id) {
		printBanner();
		/*
		 * 
		 * 1. member(T) id, nickname -> Insert gh(T) 
		 * 2. game
		 * 3. Update 
		 */
		
		// id, nickname, reward 추출
		MemberDTO memberDTO = gameHistoryDAO.findMemberId(user_id);
		// 1. insert
		gameHistoryDAO.startgameHistory(user_id);
				
		//사용자 정보 출력
		if(memberDTO != null) {
			System.out.println();
			System.out.println("["+memberDTO.getUser_Name()+"]" + "님 환영합니다." + "\t보유코인 : "+"["+memberDTO.getReward()+"]");
		}
		
		
		//비용 안내
		int gameEntryFree = 100;
		System.out.println("게임 참가 비용은 100코인입니다.");
		System.out.println();
		System.out.print("게임을 진행하시겠습니까? (Y/N) : ");
		
		//사용자 입력 대기
		String userInput = scanner.next().trim().toUpperCase();
		System.out.println("=======================================");
		
		if(userInput.equals("Y")) {
			if(memberDTO.getReward() < gameEntryFree) {
				System.out.println("보유 코인이 부족하여 게임을 진행할 수 없습니다.");
				return;
			}
			
			int reward = startGame(); // 2. 게임 시작 
			
			// 3. update 
			gameHistoryDAO.endgameHistory(user_id, reward);
			gameHistoryDAO.updateTotalReward(user_id, reward);
		}else if (userInput.equals("N")) {
			System.out.println("이전 메뉴로 돌아갑니다.");
			System.out.println();
			return;
		}else {
			System.out.println("잘못된 입력입니다. 이전 메뉴로 돌아갑니다.");
			System.out.println();
	    }
	}
}
