package service.admin;

import java.util.Scanner;
import dto.MemberDTO;
import dao.MemberDAO;
import service.Member;

public class Admin_Update implements Member {
   
	// 관리자 정보 수정
    @Override
    public void execute() {
        System.out.println("관리자 정보를 수정합니다.");

        Scanner scan = new Scanner(System.in);
        
        System.out.print("수정할 관리자 ID를 입력하세요 : ");
        String id = scan.next();
        
        //입력한 아이디로 관리자정보 확인
        MemberDAO memberDAO = new MemberDAO();
        MemberDTO memberDTO = memberDAO.getMember(id);
        
        if (memberDTO == null) {//정보가 없는경우
            System.out.println("검색한 ID의 관리자가 없습니다.");
            return;
        }
        
        System.out.println("현재 정보 : " + memberDTO); //현재 관리자정보
        
        System.out.print("수정할 이름을 입력하세요 : ");
        String name = scan.next();
        System.out.print("수정할 비밀번호를 입력하세요 : ");
        String pwd = scan.next(); 
        System.out.print("수정할 이메일을 입력하세요 : ");
        String email = scan.next();
        
        //입력한 정보로 관리자 정보수정
        memberDTO.setUser_Name(name);
        memberDTO.setUser_PW(pwd);
        memberDTO.setUser_Email(email);
        
        //수정된 정보 DB에, 결과반환
        int result = memberDAO.updateMember(memberDTO);
        
        if (result > 0) { //수정 성공
            System.out.println(result + "개의 관리자의 정보가 수정되었습니다.");
        } else {
            System.out.println("관리자 정보 수정에 실패하였습니다.");
        }
    }
}

/*
 4. 관리자 정보 수정 
	@Override
	public void execute() {
		
	}
}
*/
