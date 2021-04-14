package player;

import java.util.Scanner;

public interface Service {

	void addPlayer(Scanner sc);

	Player getByNum(int num);

	int checkPlayerList();

	void printPlayerByNum(Scanner sc);

	void showAllPlayer();

	void rankPlayer();

	void delPlayer(Scanner sc);

	void start();

	void stop();

}
