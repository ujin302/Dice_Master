package service;

import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;
import service.*;

public class MemberService { // 사용자 & 관리자 공통 부분 처리
	Scanner sc;

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
		String role = null;
		MemberDTO dto = new MemberDTO();
		MemberDAO memberDAO = MemberDAO.getInstance();
		
		sc = new Scanner(System.in);
		
		// Memer Role 설정
		if(num == 1) {
			role = RoleService.USER;
		}else {
			role = RoleService.ADMIN;
		}
		
		System.out.println("안녕하세요. "+ role + "회원 가입을 위해 아래 정보를 작성해주세요.");
		
		System.out.print("사용자 이름 : ");
		dto.setUser_Name(sc.next());
		
		System.out.print("사용자 아이디 : ");
		dto.setUser_ID(sc.next());
		
		System.out.print("사용자 비밀번호 : ");
		dto.setUser_PW(sc.next());
		
		System.out.print("사용자 이메일 : ");
		dto.setUser_Email(sc.next());
		
		dto.setRole(role);
		
		memberDAO.joinMemerData(dto);
		
		System.out.println("");
		
		
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
