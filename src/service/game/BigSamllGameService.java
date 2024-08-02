package service.game;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

import dao.GameHistoryDAO;
import dto.GameHistoryDTO;
import service.Game;

public class BigSamllGameService implements Game{
	private Scanner scanner;
	private Random random;
	private GameHistoryDAO gameHistoryDAO;
	
	
	public BigSamllGameService() {
		scanner = new Scanner(System.in);
		random = new Random();
		gameHistoryDAO = GameHistoryDAO.getInstance();
		
		return;
	}
	
	public void printBanner() {
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
        System.out.println("---------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------");
	}
	@Override
	public void execute(String user_id) {
		
		GameHistoryDTO findId = gameHistoryDAO.findId(user_id);
		if(findId != null) {
			System.out.println(findId.getNickname() + "님 환영합니다." +"\t"+ "보유코인 : " + findId.getReward());
			
		}else {
			System.out.println("사용자 정보를 찾을 수 없습니다.");
			return;
		}
		
		
		// GAME Start Time
		LocalDateTime time_start = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        int attempts = 3;
        int reward = 0;
        boolean success = false;

        //해당 부분에 저장된 db값 nickname, reward 나와야함.
        // [nickname]님 환영합니다. 보유코인 : [reward] 
        //게임 참가 비용은 100코인입니다. 게임을 진행하시겠습니까?
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

        while (attempts > 0) {
            System.out.println("주사위의 합을 맞춰보세요 (3~18): ");
            int userGuess = scanner.nextInt();

            int dice1 = random.nextInt(6) + 1;
            int dice2 = random.nextInt(6) + 1;
            int dice3 = random.nextInt(6) + 1;
            int diceSum = dice1 + dice2 + dice3;

            System.out.println("주사위 결과: " + dice1 + " + " + dice2 + " + " + dice3 + " = " + diceSum);

            if (userGuess == diceSum) {
                success = true;
                switch (3 - attempts + 1) {
                    case 1:
                        reward = 200;
                        break;
                    case 2:
                        reward = 150;
                        break;
                    case 3:
                        reward = 100;
                        break;
                }
                System.out.println("축하합니다! " + (4 - attempts) + "회차에 성공했습니다!");
                break;
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("틀렸습니다. " + attempts + "회차가 남았습니다.");
                }
            }
        }

        if (!success) {
            reward = -100;
            System.out.println("3회차 실패!");
        }

        // Game end time
        LocalDateTime time_over = LocalDateTime.now();

        // Save game history
        GameHistoryDTO gameHistoryDTO = new GameHistoryDTO(
        		user_id,
                "nickname", // Replace with actual nickname retrieval
                reward,
                Timestamp.valueOf(time_start),
                Timestamp.valueOf(time_over)
        );

        gameHistoryDAO.GameHistory(gameHistoryDTO);
    }
		
		
		
		
        /*
         * 승부가 났을 때, DB (game) 저장 
         * 
         * 컬럼 : user_id, m
         * user_id : 중복 X
         *  
         * GameDTO.setUser_ID(user_id);
         * GameDTO.setM(m);
         * 
         * 
         * GameDAO.save(GameDTO);
         * 
         */
    }