package service.admin;

import java.util.Scanner;

import dao.MemberDAO;
import service.Member;

public class Admin_UserControlService implements Member {

	@Override
	public void execute() {
		Scanner sc = new Scanner(System.in);
		System.out.println("제거하고 싶은 상금 기준을 입력해주세요. ");
		System.out.print("입력 : ");
		int standard = sc.nextInt();
		
		int result = new MemberDAO().controlUser(standard);
		
		System.out.println(result + "명의 사용자를 제거하였습니다. ");
		if(result == 0) System.out.println("조건에 해당하는 사용자가 없습니다. ");
	}

}
