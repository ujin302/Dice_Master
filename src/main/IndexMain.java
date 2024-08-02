package main;

import dao.GameHistoryDAO;
import service.MenuService;
import service.game.BigSmallGameService;

public class IndexMain {

	public static void main(String[] args) {
		new MenuService().mainMenu();
//		new GameHistoryDAO().getNicknameList();
	}

}

