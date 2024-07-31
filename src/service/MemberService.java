package service;

import java.util.Scanner;
import dao.MemberDAO;
import dto.MemberDTO;

public class MemberService {
    // 사용자 & 관리자 공통 부분 처리 
    MemberDAO memberDAO = new MemberDAO(); //객체생성
    Scanner scan = new Scanner(System.in);

    // 사용자 & 관리자 회원가입
    public void joinMembership(int num) {
        MemberDTO memberDTO = new MemberDTO(); //회원정보 저장 객체
        System.out.println("회원가입 정보를 입력해주세요.");
        System.out.print("사용자 이름 : ");
        memberDTO.setUser_Name(scan.next());
        System.out.print("아이디 : ");
        memberDTO.setUser_ID(scan.next());
        System.out.print("비밀번호 : ");
        memberDTO.setUser_PW(scan.next());
        System.out.print("이메일 : ");
        memberDTO.setUser_Email(scan.next());
        
        memberDTO.setRole(num == 1 ? "USER" : "ADMIN"); //사용자/관리자 각자 role 설정
        
        //회원정보 DB에 저장, 결과반환
        int result = memberDAO.saveMember(memberDTO);
        if (result > 0) { //저장성공 시
            System.out.println("회원가입 성공");
            login(num);
        } else {
            System.out.println("회원가입 실패");
        }
    }
    
    // 사용자 & 관리자 로그인
    public void login(int num) {
        System.out.println("로그인 정보를 입력해주세요.");
        System.out.print("아이디 : ");
        String userID = scan.next();
        System.out.print("비밀번호 : ");
        String userPW = scan.next();

        //입력한 로그인정보를 DB에서 확인, 결과반환
        int result = memberDAO.loginMember(userID, userPW);
        if (result > 0) {
            System.out.println("로그인 성공");
            if (num == 1) {
                new MenuService().UserMenu(); //1인경우 사용자 -> User메뉴
            } else {
                new MenuService().AdminMenu(); //관리자
            }
        } else {
            System.out.println("로그인 실패");
        }
    }


//사용자 & 관리자 공통 부분 처리 

	// 사용자 & 관리자 회원가입
	//public void joinMembership(int num) {
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
	
	
	// 사용자 & 관리자 로그인
	//public void login(int num) {
		/*
		 * 사용자 : num == 1
		 * 관리자 : num == 2
		 * 
		 * [ 사용자 입력 ] 
		 * ID
		 * PW 
		 */
	}
