package hangman;

import player.Player;

public interface HmService {

	void playGame(Player p); 
	
	void winGame(Player p);

	void loseGame(Player p);
	
	void getHint(Player p); 
	
	void getUndo(Player p);

}
