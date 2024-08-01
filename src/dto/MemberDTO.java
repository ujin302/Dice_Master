package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private String user_Name;
	private String user_ID;
	private String user_PW;
	private String user_Email;
	private String role;
	private int reward;
	/*
	 * 변수명 (모두 String 타입) 
	 * user_Name 
	 * user_ID 
	 * user_PW 
	 * user_Email 
	 * role
	 * reward
	 */
	
	public String userPrint () {
		return user_Name + "\t"
				+ user_ID + "\t"
				+ user_PW + "\t"
				+ user_Email + "\t"
				+ role + "\t"
				+ reward;
	}
}
