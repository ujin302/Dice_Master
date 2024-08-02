package dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameHistoryDTO {
	private String user_id;
	private String nickname;
	private int reward;
	private Timestamp time_start;
	private Timestamp time_over;
	
	//게임 기록 정보를 포맷된 문자열로 반환하는 메소드.
	public String userPrint () {
		return user_id + "\t"
				+ nickname + "\t"
				+ reward + "\t"
				+ time_start + "\t"
				+ time_over;
	}
	
}