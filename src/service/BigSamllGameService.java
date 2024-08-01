package service;

public class BigSamllGameService implements Game{
	@Override
	public void execute() {
		String[] b = {
                "  ____   ",
                " |  _ \\  ",
                " | |_) | ",
                " |  _ <  ",
                " | |_) | ",
                " |____/  "
        };

        String[] i = {
                "  _____  ",
                " |_   _| ",
                "   | |   ",
                "   | |   ",
                "  _| |_  ",
                " |_____| "
        };

        String[] g = {
                "   ____  ",
                "  / ___| ",
                " | |  _  ",
                " | |_| | ",
                "  \\____| ",
                "         "
        };

        String[] s = {
                "  _____  ",
                " / ____| ",
                " | (___  ",
                "  \\___ \\ ",
                "  ____) |",
                " |_____/ "
        };

        String[] m = {
                "  __  __  ",
                " |  \\/  | ",
                " | |\\/| | ",
                " | |  | | ",
                " | |  | | ",
                " |_|  |_| "
        };

        String[] a = {
                "    _    ",
                "   / \\   ",
                "  / _ \\  ",
                " / ___ \\ ",
                "/_/   \\_\\",
                "         "
        };

        String[] l = {
                "  _       ",
                " | |      ",
                " | |      ",
                " | |      ",
                " | |____  ",
                " |______| "
        };

 
        for (int line = 0; line < b.length; line++) {
            System.out.println(b[line] + " " + i[line] + " " 
                               + g[line] + "    " + s[line] + " " 
            		           + m[line] + " " + a[line] + " " 
                               + l[line] + " " + l[line]);
        }
        System.out.println("---------------------------------------");
		System.out.println("---------------------------------------");
		
		
        /*
         * 승부가 났을 때, DB (game) 저장 
         * 
         * 컬럼 : user_id, m
         * user_id : 중복 X
         *  
         * GameDTO.setUser_ID(user_id);
         * GameDTO.setM(m);
         * 
         * 
         * GameDAO.save(GameDTO);
         * 
         */
    }
}