package main;

import dao.BaseDAO;
import dao.MemberDAO;
import dto.MemberDTO;
import service.MenuService;

public class IndexMain {

	public static void main(String[] args) {
		new MenuService().mainMenu();
	}

}
