package hangman;

import java.util.Scanner;

public interface WordService {
	void inputWord(Scanner sc);
	void editWord(Scanner sc);
	void deleteWord(Scanner sc);
	void deleteCate(Scanner sc);
	void getAllWordByCate(Scanner sc);
	int getConutWordByCate(String category);

}
