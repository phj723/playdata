package hangman;

import java.util.ArrayList;

public interface WordDao {
	void insert(Word w);
	ArrayList<String> selectAllCate();
	ArrayList<String> selectAllWord();	
	ArrayList<String> selectWordByCate(String category);
	void update(String oldWord, String newWord);
	void deleteWord(String word);
	void deleteCate(String category);

}
