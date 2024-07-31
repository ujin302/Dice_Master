package main;

import dao.BaseDAO;
import dao.MemberDAO;
import dto.MemberDTO;
import service.MenuService;

public class IndexMain {

	public static void main(String[] args) {
		new MemberDAO().getInstance().saveMember(new MemberDTO());
		
//		new BaseDAO().saveMember(new MemberDTO());
		
		new MenuService().mainMenu();
	}

}
