package hangman;

public class Word {
	private int num;
	private String category;
	private String word;
	
	public Word() {}
	public Word(String category, String word) {
		this.category = category;
		this.word = word;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	
	

}
