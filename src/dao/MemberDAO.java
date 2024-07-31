package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.MemberDTO;

public class MemberDAO extends BaseDAO {
	//MemberDAO의 싱글톤 인스턴스 생성
	static MemberDAO instance = new MemberDAO();
	
	// 회원정보 저장
	public int saveMember(MemberDTO memberDTO) {
		int num = 0; //결과 저장변수
		getConnection(); //DB연결 
		PreparedStatement pstmt = null; //쿼리실행

		return num; //반환
	}
	//회원 로그인
	public int loginMember(String userID, String userPW) {
		int result = 0;
		getConnection();
		PreparedStatement pstmt = null;
		
		return result;
		
	}
	
	// 회원정보 저장, 매개변수 : id, pw, num(1 or 2)
	public int loginMember() {
		int num = 0;
		getConnection();
		
		return num;
	}
	// ID로 회원정보 조회
	public MemberDTO getMember(String id) {
		MemberDTO memberDTO = null; //조회된 회원정보 저장
		getConnection();
		PreparedStatement pstmt = null;

		return memberDTO;
		
	}
	//회원정보 수정
	public int updateMember(MemberDTO memberDTO) {
		int result = 0;
		getConnection();
		PreparedStatement pstmt = null;

		
		return result;
		
	}
	
	
}
