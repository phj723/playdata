package blacklist;

import java.util.Scanner;

public interface BlackService {
	void addBlack(Scanner sc);
	void getAll();
	BlackList getById(String id);
	void delBlack(Scanner sc);
}
