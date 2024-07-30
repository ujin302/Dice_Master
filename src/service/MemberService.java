package service;

public class MemberService {
	// 사용자 & 관리자 공통 부분 처리 

	// 사용자 & 관리자 회원가입
	public void joinMembership(int num) {
		/*
		 * 사용자 : 무제한 & num == 1
		 * 관리자 : 인원 제한 3명 & num == 2
		 * 
		 * [ 사용자 입력 ] 
		 * UserName 
		 * ID
		 * PW 
		 * Email 
		 * 
		 * PW 제외 중복 X 
		 * 
		 * 회원가입 후, 로그인 화면 이동 >> login(); 호출
		 */
	}
	
	// 사용자 & 관리자 로그인
	public void login(int num) {
		/*
		 * 사용자 : num == 1
		 * 관리자 : num == 2
		 * 
		 * [ 사용자 입력 ] 
		 * ID
		 * PW 
		 */
	}
	
}
