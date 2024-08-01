package service.admin;

import java.util.ArrayList;

import dao.MemberDAO;
import dto.MemberDTO;
import service.Member;

public class Admin_UserListService implements Member {
	// 회원 목록 출력 
	
	@Override
	public void execute() {
		ArrayList<MemberDTO> dtoList = new ArrayList<MemberDTO>();
		System.out.println("모든 사용자 정보를 출력합니다. ");
		
		dtoList = new MemberDAO().listUser();
		
		for(MemberDTO dto : dtoList) {
			System.out.println(dto.userPrint());
		}
		
		
	}
}
