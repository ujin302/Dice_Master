package main;

import dao.GameHistoryDAO;
import service.MenuService;

public class IndexMain {

	public static void main(String[] args) {
		new MenuService().mainMenu();
		
//		new GameHistoryDAO().getNicknameList();
	}

}

