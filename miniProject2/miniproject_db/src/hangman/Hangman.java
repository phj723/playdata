package hangman;

import java.util.ArrayList;

public class Hangman {
	private int gameCount;
	private String hintKeyWord; 
	private int hintPrice;
	private String undoKeyWord; 
	private int undoPrice;
	private int winPoint;
	private ArrayList<String> quizWordList;
	private ArrayList<String> wordList;
	private ArrayList<String> wrongList;

	public Hangman() {
		gameCount = 7;
		hintKeyWord = "hint";
		hintPrice = 20;
		undoKeyWord = "undo";
		undoPrice = 20;
		winPoint = 50;
		quizWordList = new ArrayList<String>(); 
		wordList = new ArrayList<String>();
		wrongList = new ArrayList<String>(); 
	}

	public int getGameCount() {
		return gameCount;
	}

	public String getHintKeyWord() {
		return hintKeyWord;
	}

	public int getHintPrice() {
		return hintPrice;
	}

	public String getUndoKeyWord() {
		return undoKeyWord;
	}

	public int getUndoPrice() {
		return undoPrice;
	}

	public int getWinPoint() {
		return winPoint;
	}

	public ArrayList<String> getQuizWordList() {
		return quizWordList;
	}

	public ArrayList<String> getWordList() {
		return wordList;
	}

	public ArrayList<String> getWrongList() {
		return wrongList;
	}

}
