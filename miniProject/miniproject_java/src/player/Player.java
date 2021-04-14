package player;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
	private static int cnt;
	private int num;
	private String name;
	private int point;
	private ArrayList<Integer> cards = new ArrayList<>(); // 받은 카드 리스트

	public Player() {
	}

	public Player(String name, int point) {
		super();
		this.num = ++cnt;
		this.name = name;
		this.point = point;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Player) {
			if (num == ((Player) obj).num) {
				return true;
			}
		}
		return super.equals(obj);
	}

	public ArrayList<Integer> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Integer> cards) {
		this.cards = cards;
	}

	public static int getCnt() {
		return cnt;
	}

	public static void setCnt(int cnt) {
		Player.cnt = cnt;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@Override
	public String toString() {
		return "Player " + num + " | Name : " + name + " | Point : " + point;
	}

}
