package player;

import java.util.ArrayList;

// db작업만 함
public interface PlayerDao {
	// 회원가입 기능에 필요 service에서 id,pwd,name,email을 Member객체에 담아서 insert() 호출 => db에 저장하기위해
	void insert(Player p);

	// service의 login, 내정보확인
	Player select(String id);
	Player selectnick(String nickname);
	
	// 모든 회원 정보 확인
	ArrayList<Player> getAll();

	// service의 내 정보 수정 
	void update(String id, String new_pwd);
	void updateNick(String id, String nickName);
	void lossPoint(String id, int points);
	void getPoint(String id, int points);
	
	// service의 탈퇴
	void delete(String id);
}
