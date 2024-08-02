package service;

import java.util.Scanner;

import dto.MemberDTO;
import service.user.*;
import service.admin.*;
import service.game.BigSmallGameService;
import service.game.RankingService;
import service.game.UserInfoService;

public class MenuService {
	Scanner sc;
	int num;
	
	public void mainMenu() {
		/*
		 * 1. 회원가입 >> JoinMenu(); 함수 호출
		 * 2. 로그인 >> LoginMenu(); 함수 호출
		 */
		
		sc = new Scanner(System.in);
		boolean isSelect = true;
		
		while(isSelect) {
			
			System.out.println("\n------------------------------ Main ------------------------------\n");
			System.out.println("\t1. 회원가입\t2. 로그인\t       3. 종료 \n");
			System.out.println("------------------------------------------------------------------");
			System.out.print("\t메뉴 선택 : ");
			
			num = sc.nextInt();
			
			if(num == 1) {
				joinMenu();
			}else if(num == 2) loginMenu();
			else if(num == 3) {
				isSelect = false;
				System.out.println("프로그램이 종료되었습니다.");
			}
			else System.out.println("메뉴 중에서 선택해주세요.");
			
			System.out.println();
		}
	}
	
	public void joinMenu() {
		/*
		 * 1. 사용자 >> joinMembership(1);
		 * 2. 관리자 >> joinMembership(2);
		 * 3. Main 메뉴
		 * 
		 * joinMembership(num); 함수 호출
		 */
		
		sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("\n----------------------------- 회원가입 ------------------------------\n");
			System.out.println("\t 1.사용자\t 2.관리자\t 3.Main 메뉴\n");
			System.out.println("------------------------------------------------------------------");
			System.out.print("\t메뉴 선택 : ");
			
			num = sc.nextInt();
			
			if(num == 1 || num == 2) {
				new MemberService(num).joinMembership();
				break;
			}else if(num == 3) {
				System.out.println("Main 메뉴로 돌아갑니다. \n");
				break;
			}
			else System.out.println("메뉴 중에서 선택해주세요.");
			
			System.out.println();
		}
	}
	
	public void loginMenu() {
		/*
		 * 1. 사용자 >> login(1);
		 * 2. 관리자 >> login(2);
		 * 3. Main 메뉴
		 * 
		 * login(); 함수 호출
		 */
		
		sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("\n----------------------------- 로그인 -------------------------------\n");
			System.out.println("\t 1.사용자\t 2.관리자\t 3.Main메뉴\n");
			System.out.println("------------------------------------------------------------------");
			System.out.print("\t메뉴 선택 : ");
			
			num = sc.nextInt();
			
			if(num == 1 || num == 2) { // 로그인 진행
				new MemberService(num).login();
				break;
			}else if(num == 3) {
				System.out.println("Main 메뉴로 돌아갑니다. \n");
				break;
			}
			else System.out.println("메뉴 중에서 선택해주세요.");
			
			System.out.println();
		}
	}
	
	public void userMenu(String user_id) {
		/*
		 * 1. 게임 >> new User_Game();
		 * 2. 사용자 정보 수정 >> new user_Update();
		 * 3. 로그아웃 >> return;
		 * 4. 탈퇴 >> new User_Delete();
		 * 
		 * execute(); 함수 호출
		 * 
		 * new 다음은 클래스명을 의미하며 각 class 파일을 만들어주세요! 
		 * 또한, 모든 클래스는 Member 인터페이스를 implements 합니다! 
		 */
		
		sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("\n----------------------------- 사용자 -------------------------------\n");
			System.out.println("\t  1.게임\t  2.사용자 정보 수정\t  3.로그아웃\t 4.탈퇴");
			System.out.println("\t* 3, 4 번 선택 시, Main 메뉴로 이동합니다. \n");
			System.out.println("------------------------------------------------------------------");
			System.out.print("\t메뉴 선택 : ");
			
			num = sc.nextInt();
			
			if(num == 1) {
				gameMenu(user_id);
			}else if(num == 2) {
				new User_UpdateService(user_id).execute();
			}
			else if(num == 3 || num == 4) {
				if(num == 3) System.out.print("로그아웃 되었습니다. ");
				else new MemberService(1).delete();
				
				System.out.println("Main 메뉴로 돌아갑니다.\n");
				break;
			}
			else System.out.println("메뉴 중에서 선택해주세요.");
			
			System.out.println();
		}
		
	}
	
	public void adminMenu(String user_id) {
		/*
		 * 1. 회원 목록 >> new Admin_UserList();
		 * 2. 회원 삭제 >> new Admin_UserDelete();
		 * 3. 회원 관리 >> new Admin_Management();
		 * 4. 관리자 정보 수정 >> new Admin_Update();
		 * 5. 로그아웃 >> return;
		 * 6. 탈퇴 >> new Admin_Delete();
		 * 
		 * execute(); 함수 호출
		 * 
		 * new 다음은 클래스명을 의미하며 각 class 파일을 만들어주세요! 
		 * 또한, 모든 클래스는 Member 인터페이스를 implements 합니다! 
		 */
		
		sc = new Scanner(System.in);
		Member member = null;
		
		while(true) {
			
			System.out.println("\n----------------------------- 관리자 -------------------------------\n");
			System.out.println("\t1. 회원 목록\t2. 회원 관리\n\t3. 관리자 정보 수정 \t4. 로그아웃\t5. 탈퇴");
			System.out.println("\t* 4, 5 번 선택 시, Main 메뉴로 이동합니다. \n");
			System.out.println("------------------------------------------------------------------");
			System.out.print("\t메뉴 선택 : ");
			
			num = sc.nextInt();
			
			if(num == 1) { // 회원 목록 
				member = new Admin_UserListService();
			} else if(num == 2) { // 회원 관리 
				member = new Admin_UserControlService();
			} else if(num == 3) { // 관리자 정보 수정 
				member = new Admin_UpdateService(user_id);
			} else if(num == 4 || num == 5) { // 로그아웃 || 탈퇴
				if(num == 4) System.out.print("로그아웃 되었습니다. ");
				else new MemberService(2).delete();
				
				System.out.println("Main 메뉴로 돌아갑니다. \n");
				break;
			}
			else System.out.println("메뉴 중에서 선택해주세요.");
			
			member.execute();
			
			System.out.println();
		}
	}

	public void gameMenu(String user_id) {
		
		Game game = null;
		try {
			Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
		}
		System.out.println("=======================================");
		String[] d = {
	            "  _____  ",
	            " |  __ \\ ",
	            " | |  | |",
	            " | |  | |",
	            " | |__| |",
	            " |_____/ "
	    };

	    String[] i = {
	            "  _____  ",
	            " |_   _| ",
	            "   | |   ",
	            "   | |   ",
	            "  _| |_  ",
	            " |_____| "
	    };
	
	    String[] c = {
	            "   _____ ",
	            "  / ____|",
	            " | |     ",
	            " | |     ",
	            " | |____ ",
	            "  \\_____|"
	    };
	
	    String[] e = {
	            "  ______ ",
	            " |  ____|",
	            " | |__   ",
	            " |  __|  ",
	            " | |____ ",
	            " |______|"
	    };

	    for (int line = 0; line < d.length; line++) {
	    	System.out.println(d[line] + " " + i[line] + " " + c[line] + " " + e[line]);
	    	sleep(300);
	    	}
	    System.out.println("=======================================");
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("[1] Big, Small");
			System.out.println("[2] User Info");
			System.out.println("[3] Ranking");
			System.out.println("[4] 이전 화면으로");
			System.out.println("=======================================");
			System.out.print("원하는 메뉴를 선택해 주세요 : ");
			num = scanner.nextInt();
			System.out.println("=======================================");
			
			if(num == 1) {
				game = new BigSmallGameService();
			} else if(num == 2) {
				game = new UserInfoService();
			} else if(num == 3) {
				game = new RankingService();
			} else if(num == 4) {
				System.out.println("");
				break;
			}
			
			game.execute(user_id);
		}

	}
	
	private void sleep(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
