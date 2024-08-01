package service.user;

import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;
import service.Member;

public class User_UpdateService implements Member {
	
	// 사용자 정보 수정
    @Override
    public void execute() {
    	MemberDAO memberDAO = MemberDAO.getInstance();
    	MemberDTO memberDTO = new MemberDTO();
    	
    	Scanner scan = new Scanner(System.in);
    	
        System.out.println("사용자 정보를 수정합니다.");

        System.out.print("수정할 사용자 ID를 입력하세요 : ");
        String id = scan.next();
        
        boolean findId = memberDAO.findId(id);
        
        if(findId) {
        	System.out.println("현재 정보: " + memberDTO);
        	
        	System.out.print("수정할 이름을 입력하세요 : ");
        	String name = scan.next();
        	System.out.print("수정할 비밀번호를 입력하세요 : ");
        	String pwd = scan.next(); 
        	System.out.print("수정할 이메일을 입력하세요 : ");
        	String email = scan.next();
        	
        	memberDTO.setUser_ID(id);
        	memberDTO.setUser_Name(name);
        	memberDTO.setUser_PW(pwd);
        	memberDTO.setUser_Email(email);
        	memberDAO.updateUser(memberDTO);
        	
            int result = memberDAO.updateUser(memberDTO);
        
            if (result > 0) {
                System.out.println(result + "개의 사용자의 정보가 수정되었습니다.");
            } else {
                System.out.println("사용자 정보 수정에 실패하였습니다.");
          }
        } else {
    	   System.out.println("정보가 일치하지 않습니다.");
   
       }
        
    }
}

