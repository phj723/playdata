package holdem;

import java.util.ArrayList;
import java.util.Scanner;

import player.Player;

public interface HoldemPlay {

	void draw(ArrayList<Integer> deck, Player p1, Player p2); // 카드 분배

	void betting(Scanner sc, Player p); // 플레이어 배팅

	void aiBetting(Player p); // ai 배팅

	void aiFirBetting();

	void openCard(Scanner sc, Player p); // 카드 공개

	void openCard(Player p);

	void openCard();

	int checkSum(Player p);

	boolean checkRainbow(Player p);

	void result(HoldemPlayImpl hp, Player p, Player ai);
}
