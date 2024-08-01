package dto;

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
	private String nikname;
	private String reward;
	private String game_start;
	private String game_over;
	
	//게임 기록 정보를 포맷된 문자열로 반환하는 메소드.
	public String userPrint () {
		return user_id + "\t"
				+ nikname + "\t"
				+ reward + "\t"
				+ game_start + "\t"
				+ game_over;
	}
	
}
