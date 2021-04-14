package player;

import java.util.Scanner;

public interface PlayerService {
	void addPlayer(Scanner sc);
	void login(Scanner sc);
	void logout(Scanner sc);
	boolean myInfo();
	void editPwd(Scanner sc);
	void editNick(Scanner sc);
	void delPlayer();
	boolean loginCheck();
	void rank();
	
	//manager 전용 method
	void playerInfo();
	void editPlayerNick(Scanner sc);
	void removePlayer(Scanner sc);
}
