package hangman;

import java.util.ArrayList;
import java.util.Scanner;

import player.Player;
import player.PlayerDao;
import player.PlayerDaoImpl;
import player.PlayerServiceImpl;

public class HmServiceImpl implements HmService {
	private WordDao wordDao;
	private WordService wordService;
	private PlayerDao playerDao;

	public HmServiceImpl() {
		wordDao = new WordDaoImpl();
		wordService = new WordServiceImpl();
		playerDao = new PlayerDaoImpl();
	}

	Scanner sc = new Scanner(System.in);
	Hangman h = new Hangman();

	String word = null;
	ArrayList<String> quizWordList = h.getQuizWordList(); // 블랭크로 이뤄진 리스트 - 플레이어에게 보여짐
	ArrayList<String> wordList = h.getWordList(); // 단어 알파벳으로 이뤄진 리스트
	ArrayList<String> wrongList = h.getWrongList(); // 틀린 답이 입력 됨 - 플레이어에게 보여짐
	String hintKey = h.getHintKeyWord();
	String undoKey = h.getUndoKeyWord();
	
	
	@Override
	public String getQuizWordByCate(String cate) {
		ArrayList<String> wordData = wordDao.selectWordByCate(cate);
		int wordCnt = wordService.getConutWordByCate(cate);

		int random = (int) (Math.random() * wordCnt);
		String quizWord = wordData.get(random);
		return quizWord;

	}
	

	@Override
	public void playGame(Player p) {
		System.out.println("카테고리를 선택하세요.");
		boolean flag = true;
		while(flag) {
			ArrayList<String> cateList = wordDao.selectAllCate();
			for (int i = 0; i < cateList.size(); i++) {
				if(i!=cateList.size()-1) {
					System.out.print((i + 1) + "." + cateList.get(i) + " ");
				}else {
					System.out.print((i + 1) + "." + cateList.get(i) + "\n");
				}
			}
			int cate = sc.nextInt();
			if (cate < 1 || cate > cateList.size()) {
				System.out.println("카테고리를 다시 선택하세요.");
			} else {
				String selectedCate = cateList.get(cate - 1);
				word = getQuizWordByCate(selectedCate);
				flag = false;
			}
		}
		
		for (int i = 0; i < word.length(); i++) { // 퀴즈워드리스트에 단어길이만큼 언더바 넣어줌
			quizWordList.add(i, "_");
			wordList.add(i, Character.toString(word.charAt(i)));
		}

		System.out.println("영어 단어를 맞혀주세요");
		System.out.print("제시어: ");
		for (String w : quizWordList)
			System.out.print(w + " ");
		System.out.println("\n============================");
		System.out.println("틀린 답: " + wrongList);
		System.out.println("============================");
		System.out.println("힌트를 보려면 " + hintKey + "를 입력하세요(포인트 차감)");
		System.out.println("되돌리려면 " + undoKey + "를 입력하세요(포인트 차감)");
		System.out.println("============================");
		System.out.println("알파벳을 입력하세요>>");
		String alpha = sc.next();

		int blankCnt = 0;
		for (int i = 0; i < quizWordList.size(); i++) {
			if (quizWordList.get(i) == "_")
				blankCnt++;
		}

		while (blankCnt > 1) { // 빈칸이 2개 이상 남아있는 동안
			if (wordList.contains(alpha)) {// 입력값이 맞을 경우
				for (int i = 0; i < wordList.size(); i++) {
					if (wordList.get(i).equals(alpha) && quizWordList.get(i).equals("_")) {// 빈칸이면서 i번째 글자랑 일치한다면
						quizWordList.set(i, alpha); // 입력값을 i번째에 넣음
						blankCnt--;
						System.out.println((i + 1) + "번째에 " + alpha + "(이)가 있었습니다!");
						if (blankCnt == 0) {
							System.out.println("============================");
							System.out.print("제시어: ");
							for (String w : quizWordList)
								System.out.print(w + " ");
							System.out.println("\n============================");
							winGame(p);
							return;
						}
					} else if (wordList.get(i).equals(alpha)) {// 중복이라면
						System.out.println("이미 입력한 문자입니다.");
						break;
					}
				}
			} else { // 입력값이 틀리면
				if (alpha.length() < 2) {
					if (wrongList.contains(alpha)) {
						System.out.println("이미 입력한 문자입니다.");
					} else {
						wrongList.add(alpha); // 틀린답 리스트에 넣음
						System.out.println(alpha + "(은)는 없었습니다!");
						int wCnt = wrongList.indexOf(alpha);
						if (wrongList.size() < h.getGameCount()) {
							System.out.println("기회는 " + (h.getGameCount() - wCnt - 1) + "번 남았습니다.");
						} else {
							loseGame(p);
							return;
						}
					}
				} else if (alpha.equals(hintKey)) {
					getHint(p);
				} else if (alpha.equals(undoKey)) {
					if (wrongList.size() < 1) {
						System.out.println("틀린 답이 없어서 되돌리기를 사용할 수 없습니다.");
					} else {
						getUndo(p);
					}
				} else {
					System.out.println("한 글자만 입력해 주세요.");
				}
			}
			System.out.println("============================");
			System.out.print("제시어: ");
			for (String w : quizWordList)
				System.out.print(w + " ");
			System.out.println("\n============================");
			System.out.println("틀린 답: " + wrongList);
			System.out.println("============================");
			System.out.println("힌트를 보려면 " + hintKey + "를 입력하세요(포인트 차감)");
			System.out.println("되돌리려면 " + undoKey + "를 입력하세요(포인트 차감)");
			System.out.println("============================");
			System.out.println("알파벳을 입력하세요>>");
			alpha = sc.next();
		}

		//// 빈칸이 1개 있는 경우
		while (blankCnt == 1) {
			if (wordList.contains(alpha)) {// 입력값이 맞을 경우
				for (int i = 0; i < wordList.size(); i++) {
					if (wordList.get(i).equals(alpha) && quizWordList.get(i).equals("_")) {// 빈칸이면서 i번째 글자랑 일치한다면
						quizWordList.set(i, alpha); // 입력값을 i번째에 넣음
						blankCnt--;
						System.out.println((i + 1) + "번째에 " + alpha + "(이)가 있었습니다!");
						System.out.println("============================");
						System.out.print("제시어: ");
						for (String w : quizWordList)
							System.out.print(w + " ");
						System.out.println("\n============================");
						winGame(p);
						return;
					} else if (wordList.get(i).equals(alpha)) {// 중복이라면
						System.out.println("이미 입력한 알파벳입니다.");
						break;
					}
				}
			} else { // 입력값이 틀리면
				if (alpha.length() < 2) {
					if (wrongList.contains(alpha)) {
						System.out.println("이미 입력한 문자입니다.");
					} else {
						wrongList.add(alpha); // 틀린답 리스트에 넣음
						System.out.println(alpha + "(은)는 없었습니다!");
						int wCnt = wrongList.indexOf(alpha);
						if (wrongList.size() < h.getGameCount()) {
							System.out.println("기회는 " + (h.getGameCount() - wCnt - 1) + "번 남았습니다.");
						} else {
							loseGame(p);
							return;
						}
					}
				} else {
					if (alpha.equals(hintKey)) {
						System.out.println("빈 칸이 1개 남아서 힌트를 사용할 수 없습니다.");
					} else if (alpha.equals(undoKey)) {
						if (wrongList.size() < 1) {
							System.out.println("틀린 답이 없어서 되돌리기를 사용할 수 없습니다.");
						} else {
							getUndo(p);
						}
					} else {
						System.out.println("한 글자만 입력해 주세요.");
					}
				}
			}
			System.out.println("============================");
			System.out.print("제시어: ");
			for (String w : quizWordList)
				System.out.print(w + " ");
			System.out.println("\n============================");
			System.out.println("틀린 답: " + wrongList);
			System.out.println("============================");
			System.out.println("힌트를 보려면 " + hintKey + "를 입력하세요(포인트 차감)");
			System.out.println("되돌리려면 " + undoKey + "를 입력하세요(포인트 차감)");
			System.out.println("============================");
			System.out.println("알파벳을 입력하세요>>");
			alpha = sc.next();
		}
	}

	@Override
	public void winGame(Player p) {
		System.out.println("정답을 맞혀서 행맨을 구했습니다!");
		playerDao.getPoint(PlayerServiceImpl.getLogin_id(), h.getWinPoint());
		p.setPoints(p.getPoints() + h.getWinPoint());
		System.out.println("포인트를 " + h.getWinPoint() + " 얻었습니다. 현재 보유 포인트: " + playerDao.select(PlayerServiceImpl.getLogin_id()).getPoints());

		for (int i = 0; i < word.length(); i++) { // 단어들 리셋
			quizWordList.remove(0);
			wordList.remove(0);
		}
		while (!wrongList.isEmpty()) {
			wrongList.remove(0);
		}

		boolean flag = true;
		while (flag) {
			System.out.println("\n1. 행맨 다시 하기 | 2. 메인 메뉴");
			String m = sc.next();
			switch (m) {
			case "1":
				playGame(p);
			case "2":
				flag = false;
				return;
			default:
				System.out.println("다시 입력해주세요");
				break;
			}
		}
	}

	@Override
	public void loseGame(Player p) {
		System.out.println("정답은 " + word + "입니다.");
		System.out.println("행맨을 구하지 못 했습니다!");

		for (int i = 0; i < word.length(); i++) {
			quizWordList.remove(0);
			wordList.remove(0);
		}
		while (!wrongList.isEmpty()) {
			wrongList.remove(0);
		}

		boolean flag = true;
		while (flag) {
			System.out.println("\n1. 행맨 다시 하기 | 2. 메인 메뉴");
			String m = sc.next();
			switch (m) {
			case "1":
				playGame(p);
			case "2":
				flag = false;
				return;
			default:
				System.out.println("다시 입력해주세요");
				break;
			}
		}
	}

	@Override
	public void getHint(Player p) {
		System.out.println("포인트가 " + h.getHintPrice() + " 차감됩니다. 힌트를 보시겠습니까? 현재 보유 포인트: " + playerDao.select(PlayerServiceImpl.getLogin_id()).getPoints());
		System.out.println("1. 힌트 보기 2. 문제로 되돌아 가기");
		int hint = sc.nextInt();

		switch (hint) {
		case 1:
			if (playerDao.select(PlayerServiceImpl.getLogin_id()).getPoints() < h.getHintPrice()) {
				System.out.println("포인트가 부족합니다.");
				break;
			} else {
				playerDao.lossPoint(PlayerServiceImpl.getLogin_id(),h.getHintPrice());
				System.out.println(h.getHintPrice() + "포인트가 차감되었습니다. 현재 보유 포인트: " + p.getPoints());
				for (int i = 0; i < quizWordList.size(); i++) {
					if (quizWordList.get(i) == "_") {
						String hintWord = Character.toString(word.charAt(i));
						System.out.println("\n" + (i + 1) + "번째 문자는 " + hintWord + "입니다.");
						break;
					}
				}
			}
		case 2:
			break;
		}
	}

	@Override
	public void getUndo(Player p) {
		System.out.println("포인트가 " + h.getUndoPrice() + " 차감됩니다. 되돌리기를 사용하시겠습니까? 현재 보유 포인트: " + playerDao.select(PlayerServiceImpl.getLogin_id()).getPoints());
		System.out.println("1. 되돌리기 사용하기 2. 문제로 되돌아 가기");
		int hint = sc.nextInt();

		switch (hint) {
		case 1:
			if (playerDao.select(PlayerServiceImpl.getLogin_id()).getPoints() < h.getUndoPrice()) {
				System.out.println("포인트가 부족합니다.");
				break;
			} else {
				playerDao.lossPoint(PlayerServiceImpl.getLogin_id(),h.getUndoPrice());
				System.out.println(h.getUndoPrice() + "포인트가 차감되었습니다. 현재 보유 포인트: " + p.getPoints());
				wrongList.remove(wrongList.size() - 1);
				System.out.println("최근에 입력한 오답이 되돌려졌습니다.");
			}
		case 2:
			break;
		}
	}

}