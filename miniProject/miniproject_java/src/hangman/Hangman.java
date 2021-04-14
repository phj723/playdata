package hangman;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hangman {
	private ArrayList<String> wordAnimalData;
	private ArrayList<String> wordFoodData;
	private ArrayList<String> wordNationData;///////// 추가
	private Map<String, ArrayList<String>> wordDataMap;
	private int gameCount;// 알파벳 입력할 수 있는 횟수(게임 가능 횟수)
	private String hintKeyWord; // 힌트 호출 키워드
	private int hintPrice;
	private String undoKeyWord; // 되돌리기 호출 키워드
	private int undoPrice;
	private int winPoint;// 승리 시 포인트 얼마로?
	private ArrayList<String> quizWordList;
	private ArrayList<String> wordList;
	private ArrayList<String> wrongList;

	public Hangman() {
		gameCount = 7;
		hintKeyWord = "hint";
		hintPrice = 15;
		undoKeyWord = "undo";
		undoPrice = 15;
		winPoint = 20;
		wordAnimalData = new ArrayList<String>();
		wordFoodData = new ArrayList<String>();
		wordNationData = new ArrayList<String>();/////// 추가
		wordDataMap = new HashMap<String, ArrayList<String>>();
		quizWordList = new ArrayList<String>(); // 블랭크로 이뤄진 리스트 - 플레이어에게 보여짐
		wordList = new ArrayList<String>(); // 단어 알파벳으로 이뤄진 리스트
		wrongList = new ArrayList<String>(); // 틀린 답이 입력 됨 - 플레이어에게 보여짐
	}

	public void addWord() {
		wordAnimalData.add("penguin");
		wordAnimalData.add("kangaroo");
		wordAnimalData.add("rabbit");
		wordAnimalData.add("camel");
		wordAnimalData.add("orangutan");
		wordAnimalData.add("quokka");
		wordAnimalData.add("dolphin");
		wordAnimalData.add("giraffe");
		wordAnimalData.add("monkey");
		wordAnimalData.add("llama");
		wordAnimalData.add("elephant");
		wordAnimalData.add("cheetah");
		wordAnimalData.add("hippo");
		wordAnimalData.add("flamingo");
		wordAnimalData.add("duck");
		wordAnimalData.add("goat");
		wordAnimalData.add("hyena");
		wordAnimalData.add("sheep");
		wordAnimalData.add("zebra");
		wordAnimalData.add("skunk");
		wordAnimalData.add("raccoon");
		wordDataMap.put("animal", wordAnimalData);

		wordFoodData.add("hamburger");
		wordFoodData.add("pizza");
		wordFoodData.add("gambas");
		wordFoodData.add("churros");
		wordFoodData.add("steak");
		wordFoodData.add("tiramisu");
		wordFoodData.add("spaghetti");
		wordFoodData.add("curry");
		wordFoodData.add("sandwich");
		wordFoodData.add("fishandchips");
		wordFoodData.add("creambrulee");
		wordFoodData.add("burrito");
		wordFoodData.add("hashbrown");
		wordFoodData.add("pancake");
		wordFoodData.add("ratatouille");
		wordFoodData.add("macaron");
		wordFoodData.add("dimsum");
		wordFoodData.add("padthai");
		wordFoodData.add("sushi");
		wordFoodData.add("okonomiyaki");
		wordFoodData.add("bulgogi");
		wordDataMap.put("food", wordFoodData);

		wordNationData.add("cuba");
		wordNationData.add("croatia");
		wordNationData.add("vatican");
		wordNationData.add("namibia");
		wordNationData.add("india");
		wordNationData.add("italy");
		wordNationData.add("mexico");
		wordNationData.add("newzealand");
		wordNationData.add("mongolia");
		wordNationData.add("korea");
		wordNationData.add("philippines");
		wordNationData.add("germany");
		wordNationData.add("norway");
		wordNationData.add("uzbekistan");
		wordNationData.add("unitedarabemirates");
		wordNationData.add("scotland");
		wordNationData.add("peru");
		wordNationData.add("egypt");
		wordNationData.add("qatar");
		wordNationData.add("brazil");
		wordDataMap.put("nation", wordNationData);

	}

	public String getQuizWordByCate(String cate) {
		ArrayList<String> wordData = wordDataMap.get(cate);

		int random = (int) (Math.random() * 20);
		String quizWord = wordData.get(random);
		return quizWord;

	}

	public Map<String, ArrayList<String>> getWordDataMap() {
		return wordDataMap;
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
