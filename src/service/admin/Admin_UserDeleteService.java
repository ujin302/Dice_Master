package service.admin;

import java.util.Scanner;

import dao.MemberDAO;
import service.Member;
import service.RoleService;

public class Admin_UserDeleteService implements Member {

	@Override
	public void execute() {
		Scanner sc = new Scanner(System.in);	
		MemberDAO memberDAO = new MemberDAO();
		System.out.println("탈퇴시키고 싶은 사용자 아이디를 작성해주세요.");
        
		System.out.print("아이디 : ");
        String userID = sc.next();
        
        int result = memberDAO.deleteUser(userID);
        
        if(result == 1) System.out.println("성공적으로 탈퇴하였습니다. ");
        else System.out.println("정보가 알맞지 않아 탈퇴에 실패하였습니다. ");
	}

}
