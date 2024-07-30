package service;

public class MenuService {
	public void mainMenu() {
		/*
		 * 1. 회원가입 >> JoinMenu(); 함수 호출
		 * 2. 로그인 >> LoginMenu(); 함수 호출
		 */
	}
	
	public void JoinMenu() {
		/*
		 * 1. 사용자 >> new UserService().joinMembership(1);
		 * 2. 관리자 >> new AdminService().joinMembership(2);
		 * 3. 메인 메뉴
		 * 
		 * joinMembership(num); 함수 호출
		 */
		
	}
	
	public void LoginMenu() {
		/*
		 * 1. 사용자 >> new UserService.login(1);
		 * 2. 관리자 >> new AdminService.login(2);
		 * 3. 메인 메뉴
		 * 
		 * login(); 함수 호출
		 */
	}
	
	public void AdminMenu() {
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
		
	}
	
	public void UserMenu() {
		/*
		 * 1. 게임 >> new User_Game();
		 * 2. 사용자 정보 수정 >> new Uesr_Update();
		 * 3. 로그아웃 >> return;
		 * 4. 탈퇴 >> new User_Delete();
		 * 
		 * execute(); 함수 호출
		 * 
		 * new 다음은 클래스명을 의미하며 각 class 파일을 만들어주세요! 
		 * 또한, 모든 클래스는 Member 인터페이스를 implements 합니다! 
		 */
		
	}
}
