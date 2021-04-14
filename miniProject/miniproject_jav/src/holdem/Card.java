package holdem;

import java.util.ArrayList;
import java.util.Collections;

public class Card {
	protected ArrayList<Integer> cards = new ArrayList<>();

	public Card() {
		this.cards = cards;
	}

	public ArrayList<Integer> shuffle() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= i; j++) {
				cards.add(i);
			}
		}
		Collections.shuffle(cards);
		return cards;
	}
	
}
