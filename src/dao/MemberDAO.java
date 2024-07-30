package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDTO;

public class MemberDAO extends BaseDAO {
	
	// 회원정보 저장
	public int saveMember(MemberDTO memberDTO) {
		int num = 0;
		getConnection();
		
		return num;
	}
	
	// 회원정보 저장, 매개변수 : id, pw, num(1 or 2)
	public int loginMember() {
		int num = 0;
		// git commit		
		return num;
	}
}
