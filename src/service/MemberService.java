package service;

import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;
import service.*;

public class MemberService { // 사용자 & 관리자 공통 부분 처리
	MemberDAO memberDAO;
	MemberDTO dto;
	String role;
	Scanner sc;
	
	
	public MemberService(int num) {
		if(num == 1) {
			role = RoleService.USER;
		} else if(num == 2) {
			role = RoleService.ADMIN;
		}
		
		memberDAO = MemberDAO.getInstance();
		sc = new Scanner(System.in);
	}

	// 사용자 & 관리자 회원가입
	public void joinMembership() {
		String input = null;
		dto = new MemberDTO(); // insert 용 
		
		System.out.println("안녕하세요. " + role + " 회원 가입을 위해 아래 정보를 작성해주세요.");
		
		while(true) {
			System.out.print("닉네임 : ");
			input = sc.next();
			
			boolean isExist = memberDAO.findName(input);
			
			if(isExist) System.out.println("닉네임이 중복됩니다. 다시 입력해주세요.");
			else {
				dto.setUser_Name(input);
				input = null;
				break;
			}
		}
		
		while(true) {
			System.out.print("사용자 아이디 : ");
			input = sc.next();
			MemberDTO checkDTO = memberDAO.findId(input); // 중복 방지 확인 용 
			
			if(checkDTO != null) System.out.println("아이디가 중복됩니다. 다시 입력해주세요.");
			else {
				dto.setUser_ID(input);
				input = null;
				break;
			}
		}
		
		System.out.print("사용자 비밀번호 : ");
		dto.setUser_PW(sc.next());
		
		while(true) {
			System.out.print("사용자 이메일 : ");
			input = sc.next();
			
			if(!input.contains("@")) {
				System.out.println("올바른 이메일 형식이 아닙니다. 형식에 맞춰어 다시 작성해주세요.");
				continue;
			}
			
			boolean isExist = memberDAO.findEmail(input);
			
			if(isExist) System.out.println("이메일이 중복됩니다. 다시 입력해주세요.");
			else {
				dto.setUser_Email(input);
				input = null;
				break;
			}
		}
		
		dto.setRole(role);
		if(role.equals(RoleService.USER)) dto.setReward(RoleService.REWARD);
		
		int num = memberDAO.joinMemberData(dto);
		if(num == 1) System.out.println(role + " 회원가입 성공하였습니다.");
		else System.out.println(role + " 회원가입 실패하였습니다.");
		
		System.out.println("");
		
		
	}
	
	// 사용자 & 관리자 로그인
	public void login() {
		System.out.println("안녕하세요. " + role + " 로그인 정보를 입력해주세요.");
        System.out.print("아이디 : ");
        String userID = sc.next();
        System.out.print("비밀번호 : ");
        String userPW = sc.next();

        // 로그인 확인 
        dto = memberDAO.loginMember(userID, userPW);
        if (dto != null) {
            System.out.println("환영합니다. " + dto.getUser_Name() + "님, " + role + "로그인에 성공하였습니다.");
            // 각자 메뉴 이동
			if(role.equals(RoleService.USER)) new MenuService().userMenu(dto.getUser_ID());  // 사용자 메뉴
			else new MenuService().adminMenu(dto.getUser_ID()); // 관리자 메뉴
        } else {
            System.out.println(role + " 로그인 실패하였습니다. Main 메뉴로 돌아갑니다.");
        }
	}
	
	// 사용자 & 관리자 탈퇴
	public void delete() {
		System.out.println("Dice Master 프로그램 탈퇴를 위해 " + role + " 정보를 입력해주세요.");
        System.out.print("아이디 : ");
        String userID = sc.next();
        System.out.print("비밀번호 : ");
        String userPW = sc.next();
        
        int result = memberDAO.deleteMember(userID, userPW);
        
        if(result == 1) System.out.println("성공적으로 탈퇴하였습니다. ");
        else System.out.println("정보가 알맞지 않아 탈퇴에 실패하였습니다. ");
	
	}
}