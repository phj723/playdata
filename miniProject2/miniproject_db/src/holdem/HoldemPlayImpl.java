package holdem;

import java.util.ArrayList;
import java.util.Scanner;

import player.Player;
import player.PlayerDao;
import player.PlayerDaoImpl;
import player.PlayerServiceImpl;

public class HoldemPlayImpl implements HoldemPlay {
	private ArrayList<Integer> deck = (new Card()).shuffle();
	private ArrayList<Integer> dealerDeck = new ArrayList<>(); // dealer가 받은 카드
	private ArrayList<Integer> playerDeck = new ArrayList<>(); // player가 받은 카드
	private ArrayList<Integer> aiDeck = new ArrayList<>(); // ai가 받은 카드
	private int[] arrd = new int[4]; // dealer card open용 배열 생성
	private int[] arrp = new int[3];
	private int[] arrai = new int[3];
	private int bet = 10; // 게임 시작 시 최소 배팅 금액 및 현재 최대 배팅 금액
	private int sumBet = 10; // 배팅 총액
	private int myBet = 0;
	private PlayerDao pdao = new PlayerDaoImpl();

	public HoldemPlayImpl() {
	}

	public ArrayList<Integer> getDeck() {
		return deck;
	}

	public ArrayList<Integer> getDealerDeck() {
		return dealerDeck;
	}

	public ArrayList<Integer> getPlayerDeck() {
		return playerDeck;
	}

	public int[] getArrd() {
		return arrd;
	}

	public int getBet() {
		return bet;
	}

	public int getSumBet() {
		return sumBet;
	}

	public int getMyBet() {
		return myBet;
	}

	@Override
	public void draw(ArrayList<Integer> deck) {
		for (int i = 0; i < 3; i++) {
			playerDeck.add(deck.get(i));
		}
		for (int i = 3; i < 6; i++) {
			aiDeck.add(deck.get(i));
		}
		for (int i = 6; i < 10; i++) {
			dealerDeck.add(deck.get(i));
		}
	}

	@Override
	public void openCard(Scanner sc, Player p) {
		while (true) {
			System.out.println("오픈할 카드를 선택하세요");
			int index = sc.nextInt();
			if (index >= 1 && index <= 3) {
				if (arrp[(index - 1)] == 0) {
					int open = playerDeck.get(index - 1);
					arrp[(index - 1)] = open;
					break;
				} else {
					System.out.println("이미 공개한 카드입니다. 다른 카드를 선택하세요");
				}
			} else {
				System.out.println("1에서 3사이의 수를 선택하세요");
			}
		}
		System.out.println(p.getNickName() + "님이 오픈한 카드");
		for (int c : arrp) {
			if (c == 0)
				continue;
			System.out.print(c + " ");
		}
		System.out.print("\n");
	}

	@Override
	public void openCard(Player p) {
		int index = 0;
		while (index <= 3) {
			if (arrai[index] == 0) {
				int open = aiDeck.get(index);
				arrai[index] = open;
				break;
			} else {
				index++;
			}
		}
		System.out.println("aiPlayer가 오픈한 카드");
		for (int c : arrai) {
			if (c == 0)
				continue;
			System.out.print(c + " ");
		}
		System.out.print("\n");
	}

	@Override
	public void openCard() {
		int index = 0;
		System.out.println("공유카드 오픈");
		while (index <= 3) {
			if (arrd[index] == 0) {
				int open = dealerDeck.get(index);
				arrd[index] = open;
				break;
			} else {
				index++;
			}
		}
		System.out.println("dealer가 오픈한 카드");
		for (int c : arrd) {
			if (c == 0)
				continue;
			System.out.print(c + " ");
		}
		System.out.print("\n");
	}

	@Override
	public void betting(Scanner sc, Player p) {
		System.out.println(p.getNickName() + "님의 배팅 순서입니다.");
		System.out.println("배팅 가능 금액 : " + (p.getPoints() - sumBet));
		System.out.println("배팅 방식을 선택하세요");
		System.out.println("1. Call 2. Raise");
		int m = sc.nextInt();
		switch (m) {
		case 1:
			System.out.println("Call");
			System.out.println("배팅 금액 : " + bet);
			sumBet += bet;
			myBet += bet;
			p.setPoints(p.getPoints() - bet);
			System.out.println("현재 총 배팅 금액 : " + sumBet);
			break;
		case 2:
			System.out.println("Raise");
			System.out.print("추가할 배팅 금액을 입력하세요 : ");
			int b = sc.nextInt();
			bet += b;
			System.out.println("배팅 금액 : " + bet);
			sumBet += bet;
			p.setPoints(p.getPoints() - bet);
			System.out.println("현재 총 배팅 금액 : " + sumBet);
			break;
		}
	}

	@Override
	public void aiFirBetting() {
		System.out.println("aiPlayer가 먼저 배팅합니다.");
		System.out.println("배팅 금액 : " + bet);
	}

	@Override
	public void aiBetting(Player p) {
		System.out.println("aiPlayer의 배팅 순서입니다.");
		int m;
		int b;
		while (true) {
			if ((Math.random()) <= 0.70) {
				m = 1;
				break;
			} else if ((Math.random()) > 0.70) {
				m = 2;
				break;
			}
		}
		switch (m) {
		case 1:
			System.out.println("Call");
			System.out.println("배팅 금액 : " + bet);
			sumBet += bet;
			p.setPoints(p.getPoints() - bet);
			System.out.println("현재 총 배팅 금액 : " + sumBet);
			break;
		case 2:
			System.out.println("Raise");
			if ((Math.random()) <= 0.75) {
				b = 5;
			} else if ((Math.random()) > 0.75 && (Math.random()) <= 0.95) {
				b = 10;
			} else {
				b = 20;
			}
			bet += b;
			System.out.println("배팅 금액 : " + bet);
			sumBet += bet;
			p.setPoints(p.getPoints() - bet);
			System.out.println("현재 총 배팅 금액 : " + sumBet);
			break;
		}
	}

	@Override
	public int checkSum(Player p) {
		ArrayList<Integer> tmp1 = new ArrayList<>();
		int i, j;
		int sum = 0;

		for (int k : playerDeck) {
			tmp1.add(k);
		}
		for (int l : dealerDeck) {
			tmp1.add(l);
		}
		tmp1.sort(null);

		for (j = 0; j < tmp1.size(); j++) {
			int val = tmp1.get(j);
			if (val == 0) {
				continue;
			}
			int cnt = 0;
			for (i = 0; i < tmp1.size(); i++) {
				if (i == j) {
					continue;
				}
				if (val == tmp1.get(i)) {
					tmp1.set(i, 0);
					cnt++;
				}
			}

			if (cnt > 0) {
				tmp1.set(j, 0);
			}
		}
		for (i = tmp1.size() - 1; i >= 0; i--) {
			if (tmp1.get(i) == 0) {
				tmp1.remove(i);
			}
		}

		for (int s : tmp1) {
			sum += s;
		}

		return sum;
	}

	@Override
	public boolean checkRainbow(Player p) {
		boolean flag = false;
		ArrayList<Integer> tmp = new ArrayList<>();
		ArrayList<Integer> tmp2 = new ArrayList<>();

		for (int i : aiDeck) {
			tmp.add(i);
		}
		for (int j : dealerDeck) {
			tmp.add(j);
		}
		tmp.sort(null);

		tmp2.add(tmp.get(0));
		for (int i = 1; i < tmp.size(); i++) {
			if (tmp.get(i) == tmp.get(i - 1))
				continue;
			if (tmp.get(i) != tmp.get(i - 1))
				tmp2.add(tmp.get(i));
		}

		if (tmp.size() == tmp2.size())
			flag = true;

		return flag;
	}

	@Override
	public boolean result(HoldemPlayImpl hp, Player p, Player ai) {
		if (hp.checkRainbow(p) == true && hp.checkRainbow(ai) == true) {
			if (hp.checkSum(p) < hp.checkSum(ai)) {
				System.out.println("축하합니다. " + p.getNickName() + "님이 승리했습니다.");
				p.setPoints(p.getPoints() + sumBet);
				return true;
			} else {
				System.out.println("안타까워라. " + p.getNickName() + "님이 패배했습니다.");
				return false;
			}
		} else if (hp.checkRainbow(p) == true) {
			System.out.println("축하합니다. " + p.getNickName() + "님이 승리했습니다.");
			p.setPoints(p.getPoints() + sumBet);
			return true;
		} else if (hp.checkRainbow(ai) == true) {
			System.out.println("안타까워라. " + p.getNickName() + "님이 패배했습니다.");
			return false;
		} else {
			if (hp.checkSum(p) < hp.checkSum(ai)) {
				System.out.println("축하합니다. " + p.getNickName() + "님이 승리했습니다.");
				p.setPoints(p.getPoints() + sumBet);
				return true;
			} else {
				System.out.println("안타까워라. " + p.getNickName() + "님이 패배했습니다.");
				return false;
			}
		}
	}

}
