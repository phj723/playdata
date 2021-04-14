package play;

import java.util.ArrayList;
import java.util.Scanner;

import blacklist.BlackService;
import blacklist.BlackServiceImpl;
import forum.Forum;
import forum.ForumService;
import forum.ForumServiceImpl;
import forumRep.RepDao;
import forumRep.RepDaoImpl;
import forumRep.RepService;
import forumRep.RepServiceImpl;
import hangman.HmService;
import hangman.HmServiceImpl;
import hangman.WordService;
import hangman.WordServiceImpl;
import holdem.HoldemPlayImpl;
import player.Player;
import player.PlayerDao;
import player.PlayerDaoImpl;
import player.PlayerService;
import player.PlayerServiceImpl;
import roulette.RouletteImpl;

public class Menu {
	private PlayerService playerservice;
	private ForumService forumservice;
	private RepService repservice;
	private HoldemPlayImpl hp;
	private Player ai;
	private HmService hmService;
	private RouletteImpl rouletteImpl;
	private RuleBook rb;
	private PlayerDao pdao;
	private RepDao rdao;
	private WordService wordService;
	private BlackService blackservice;

	public Menu() {
		playerservice = new PlayerServiceImpl();
		forumservice = new ForumServiceImpl();
		repservice = new RepServiceImpl();
		rdao = new RepDaoImpl();
		pdao = new PlayerDaoImpl();
		hmService = new HmServiceImpl();
		rouletteImpl = new RouletteImpl();
		rb = new RuleBook();
		wordService = new WordServiceImpl();
		blackservice = new BlackServiceImpl();
	}

	public void run(Scanner sc) {
		boolean flag1 = true;
		boolean flag2 = true;

		while (flag1) {
			System.out.println("==== 메인 메뉴 ====");
			System.out.println("1.회원가입 | 2.로그인 | 3.종료");
			int m = sc.nextInt();
			if (m < 1 || m > 3) {
				System.out.println("범위 내의 번호를 입력해주세요.");
			}
			switch (m) {
			case 1:
				playerservice.addPlayer(sc);
				break;
			case 2:
				while (flag2) {
					playerservice.login(sc);
					if (PlayerServiceImpl.getLogin_id() != null) {
						break;
					}
				}
				if (PlayerServiceImpl.getLogin_id().equals("manager")) {
					managerrun(sc);
				} else {
					playerrun(sc);
				}
				break;
			case 3:
				flag1 = false;
				break;
			}
		}
	}

	public void playerrun(Scanner sc) {
		boolean flag = true;

		while (flag) {
			System.out.println("==== 플레이어 메뉴 ====");
			System.out.println("1.회원메뉴 | 2.게임 설명 | 3.유러피언 룰렛 | 4.레인보우 홀덤 | 5.행맨 | 6.랭킹 | 7.커뮤니티 | 8.로그아웃");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				infoRun(sc);
				break;
			case 2:
				rb.rule(sc);
				break;
			case 3:
				run_g(sc);
				break;
			case 4:
				run_rh(sc);
				break;
			case 5:
				run_hm(sc);
				break;
			case 6:
				playerservice.rank();
				break;
			case 7:
				run_forum(sc);
				break;
			case 8:
				playerservice.logout(sc);
				flag = false;
				break;
			}
		}
	}

	public void infoRun(Scanner sc) {
		boolean flag = true;

		while (flag) {
			System.out.println("==== 회원 메뉴 ====");
			System.out.println("1.회원정보 | 2.비밀번호 변경 | 3.닉네임 변경(Point 30 소모) | 4.회원탈퇴(탈퇴 시 프로그램 종료) | 5.돌아가기");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				playerservice.myInfo();
				break;
			case 2:
				playerservice.editPwd(sc);
				break;
			case 3:
				playerservice.editNick(sc);
				break;
			case 4:
				playerservice.delPlayer();
				System.exit(0);
			case 5:
				flag = false;
				break;
			}
		}
	}

	public void managerrun(Scanner sc) {
		boolean flag = true;

		while (flag) {
			System.out.println("==== 관리자 메뉴 ====");
			System.out.println("1.회원관리 | 2.행맨 단어 관리 | 3.게임 설명 | 4.유러피언 룰렛 | 5.레인보우 홀덤 | 6.행맨 | 7.커뮤니티 | 8.로그아웃");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				mInforun(sc);
				break;
			case 2:
				run_word(sc);
				break;
			case 3:
				rb.rule(sc);
				break;
			case 4:
				run_g(sc);
				break;
			case 5:
				run_rh(sc);
				break;
			case 6:
				run_hm(sc);
				break;
			case 7:
				run_manforum(sc);
				break;
			case 8:
				playerservice.logout(sc);
				flag = false;
				break;
			}
		}
	}

	public void mInforun(Scanner sc) {
		boolean flag = true;

		while (flag) {
			System.out.println("==== 회원 관리 ====");
			System.out.println("1.회원정보 | 2.닉네임 변경 | 3.회원삭제 | 4.돌아가기");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				playerservice.playerInfo();
				break;
			case 2:
				playerservice.editPlayerNick(sc);
				break;
			case 3:
				playerservice.removePlayer(sc);
				break;
			case 4:
				flag = false;
				break;
			}
		}
	}

	public void run_word(Scanner sc) {
		boolean flag = true;
		while (flag) {
			System.out.println("1.단어 추가 | 2.단어 수정 | 3.단어 삭제 | 4.카테고리 삭제 | 5.단어 조회 | 6.돌아가기 ");
			int m = sc.nextInt();
			if (m < 1 || m > 6)
				System.out.println("다시 입력 하셈");
			switch (m) {
			case 1:
				wordService.inputWord(sc);
				break;
			case 2:
				wordService.editWord(sc);
				break;
			case 3:
				wordService.deleteWord(sc);
				break;
			case 4:
				wordService.deleteCate(sc);
				break;
			case 5:
				wordService.getAllWordByCate(sc);
				break;
			case 6:
				flag = false;
				break;
			}
		}
	}

	public void run_g(Scanner sc) {
		Player p = pdao.select(PlayerServiceImpl.getLogin_id());
		System.out.println("\n=============================== 유러피언 룰렛 ===============================");
		boolean flag = true;
		while (flag) {
			System.out.println("\n어떤 베팅을 하시겠습니까?");
			System.out.println("1. Inside Bet | 2. Outside Bet | 3. Return");
			int b = sc.nextInt();
			if (b < 1 || b > 3)
				System.out.println("범위 내의 번호를 입력해주세요.");
			switch (b) {
			case 1:
				System.out.println(
						"\n1. Straight(35배) | 2. Split(17배) | 3. Street(11배) | 4. Corner(8배) | 5. Six Line(5배) | 6. Return");
				int m = sc.nextInt();
				if (m < 1 || m > 6)
					System.out.println("범위 내의 번호를 입력해주세요.");
				switch (m) {
				case 1:
					rouletteImpl.betStraight(sc, p);
					break;
				case 2:
					rouletteImpl.betSplit(sc, p);
					break;
				case 3:
					rouletteImpl.betStreet(sc, p);
					break;
				case 4:
					rouletteImpl.betCorner(sc, p);
					break;
				case 5:
					rouletteImpl.betLine(sc, p);
					break;
				case 6:
					flag = false;
					break;
				}
				break;

			case 2:
				System.out.println(
						"\n1. Column(2배) | 2. Dozens(2배) | 3. Color(1배) | 4. Even or Odd(1배) | 5. Low or High(1배) | 6. Return");
				int m2 = sc.nextInt();
				if (m2 < 1 || m2 > 6)
					System.out.println("범위 내의 번호를 입력해주세요.");
				switch (m2) {
				case 1:
					rouletteImpl.betColumn(sc, p);
					break;
				case 2:
					rouletteImpl.betDozen(sc, p);
					break;
				case 3:
					rouletteImpl.betColor(sc, p);
					break;
				case 4:
					rouletteImpl.betEO(sc, p);
					break;
				case 5:
					rouletteImpl.betLH(sc, p);
					break;
				}
				break;
			case 3:
				flag = false;
				break;
			}
		}
	}

	public void run_rh(Scanner sc) {
		hp = new HoldemPlayImpl();
		ai = new Player("aiPlayer", 300);
		System.out.println("\n=============================== 레인보우 홀덤 ===============================");
		System.out.println("============================================");
		System.out.println("Rainbow Holdem 게임을 시작합니다.");
		System.out.println("============================================");
		System.out.print("\n");

		// 카드 분배
		System.out.println("============================================");
		System.out.println("카드 분배");
		hp.draw(hp.getDeck());
		System.out.println("============================================");
		System.out.print("\n");

		// 받은 카드 확인
		System.out.println("============================================");
		System.out.println("카드를 확인하세요");
		for (int c : hp.getPlayerDeck()) {
			System.out.print(c + " ");
		}
		System.out.print("\n");
		System.out.println("============================================");
		System.out.print("\n");

		// 카드 1장 오픈
		System.out.println("============================================");
		hp.openCard(); // dealer
		hp.openCard(sc, pdao.select(PlayerServiceImpl.getLogin_id())); // 플레이어
		hp.openCard(ai); // ai
		System.out.println("============================================");
		System.out.print("\n");

		// 첫 번째 배팅
		System.out.println("============================================");
		hp.aiFirBetting(); // 초기 배팅 금액 10
		System.out.println("");
		hp.betting(sc, pdao.select(PlayerServiceImpl.getLogin_id()));
		System.out.println("============================================");
		System.out.print("\n");

		// 두 번째 카드 오픈
		System.out.println("============================================");
		hp.openCard(); // dealer
		hp.openCard(sc, pdao.select(PlayerServiceImpl.getLogin_id())); // 플레이어
		hp.openCard(ai); // ai
		System.out.println("============================================");
		System.out.print("\n");

		// 두 번째 배팅
		System.out.println("============================================");
		hp.aiBetting(ai);
		System.out.println("");
		hp.betting(sc, pdao.select(PlayerServiceImpl.getLogin_id()));
		System.out.println("============================================");
		System.out.print("\n");

		// 세 번째 카드 오픈
		System.out.println("============================================");
		hp.openCard(); // dealer
		hp.openCard(sc, pdao.select(PlayerServiceImpl.getLogin_id())); // 플레이어
		hp.openCard(ai); // ai
		System.out.println("============================================");
		System.out.print("\n");

		// 마지막 배팅
		System.out.println("============================================");
		hp.aiBetting(ai);
		System.out.println("");
		hp.betting(sc, pdao.select(PlayerServiceImpl.getLogin_id()));
		System.out.println("============================================");
		System.out.print("\n");

		// 마지막 딜러 카드 오픈
		System.out.println("============================================");
		hp.openCard(); // dealer
		System.out.println("============================================");
		System.out.print("\n");

		// 카드 합계 계산 및 출력
		System.out.println("============================================");
		System.out.println("카드 합계");
		System.out.println("Player의 합계 : " + hp.checkSum(pdao.select(PlayerServiceImpl.getLogin_id())));
		System.out.println("aiPlayer의 합계 : " + hp.checkSum(ai));
		System.out.println("============================================");
		System.out.print("\n");

		// Rainbow 여부 확인
		System.out.println("============================================");
		System.out.println("Rainbow?");
		System.out.println("Player : " + hp.checkRainbow(pdao.select(PlayerServiceImpl.getLogin_id())));
		System.out.println("aiPlayer : " + hp.checkRainbow(ai));
		System.out.println("============================================");
		System.out.print("\n");

		// 결과
		System.out.println("============================================");
		if (hp.result(hp, pdao.select(PlayerServiceImpl.getLogin_id()), ai) == true) {
			pdao.getPoint(PlayerServiceImpl.getLogin_id(), hp.getSumBet());
		} else {
			pdao.lossPoint(PlayerServiceImpl.getLogin_id(), hp.getMyBet());
		}
		System.out.println("보유 포인트 : " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
		System.out.println("============================================");
		System.out.print("\n");
	}

	public void run_hm(Scanner sc) {
		System.out.println("\n=============================== 행맨  ===============================");
		Player p = pdao.select(PlayerServiceImpl.getLogin_id());
		hmService.playGame(p);
	}

	public void run_forum(Scanner sc) {
		boolean flag = true;
		if (blackservice.getById(PlayerServiceImpl.getLogin_id()) != null) {
			System.out.println("커뮤니티를 사용할 수 없는 id입니다.");
			return;
		} else {
			while (flag) {
				System.out.println("==== 커뮤니티 ====");
				forumservice.printAll(forumservice.getAll());
				System.out.println("1.게시글 작성 | 2.게시글 검색 | 3.댓글 작성 | 4.내가 쓴 글(수정, 삭제) | 5.돌아가기");
				int m = sc.nextInt();
				switch (m) {
				case 1:
					forumservice.addBoard(sc);
					break;
				case 2:
					run_search(sc);
					break;
				case 3:
					repservice.addRep(sc);
					break;
				case 4:
					run_art(sc);
					break;
				case 5:
					flag = false;
					break;
				}
			}
		}
	}

	public void run_search(Scanner sc) {
		boolean flag = true;
		System.out.println("==== 게시글 검색 ====");

		while (flag) {
			System.out.println("1.번호로 검색 | 2.작성자로 검색 | 3.제목으로 검색 | 4.카테고리로 검색 | 5.돌아가기");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				forumservice.getByNum(sc);
				break;
			case 2:
				ArrayList<Forum> list = forumservice.getByWriter(sc);
				forumservice.printAll(list);
				break;
			case 3:
				ArrayList<Forum> list1 = forumservice.getByTitle(sc);
				forumservice.printAll(list1);
				break;
			case 4:
				ArrayList<Forum> list2 = forumservice.getByCategory(sc);
				forumservice.printAll(list2);
				break;
			case 5:
				flag = false;
				break;
			}
		}
	}

	public void run_art(Scanner sc) {
		boolean flag = true;
		System.out.println("==== 내가 쓴 게시글 ====");
		forumservice.printAll(forumservice.getAll());
		System.out.println("==== 내가 쓴 댓글 ====");
		repservice.printAll(rdao.selectByWriter(PlayerServiceImpl.getLogin_id()));
		while (flag) {
			System.out.println("1.게시글 수정 | 2.게시글 카테고리 수정 | 3.게시글 삭제 | 4.댓글 수정 | 5.댓글 삭제 | 6.돌아가기");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				forumservice.editBoard(sc);
				break;
			case 2:
				forumservice.editCatBoard(sc);
				break;
			case 3:
				forumservice.delBoard(sc);
				break;
			case 4:
				repservice.editRep(sc);
				break;
			case 5:
				repservice.delRep(sc);
				break;
			case 6:
				flag = false;
				break;
			}
		}
	}

	public void run_manforum(Scanner sc) {
		boolean flag = true;

		while (flag) {
			System.out.println("==== 커뮤니티 ====");
			forumservice.printAll(forumservice.getAll());
			System.out.println("1.게시글 작성 | 2.게시글 검색 | 3.댓글 작성 | 4.게시글 관리 | 5.블랙리스트 관리 | 6.돌아가기");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				forumservice.mAddBoard(sc);
				break;
			case 2:
				run_search(sc);
				break;
			case 3:
				repservice.addRep(sc);
				break;
			case 4:
				run_manart(sc);
				break;
			case 5:
				run_black(sc);
				break;
			case 6:
				flag = false;
				break;
			}
		}
	}

	public void run_manart(Scanner sc) {
		boolean flag = true;

		while (flag) {
			System.out.println("1.카테고리 수정 | 2.게시글 삭제 | 3.돌아가기");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				forumservice.mEditBoard(sc);
				break;
			case 2:
				forumservice.mDelBoard(sc);
				break;
			case 3:
				flag = false;
				break;
			}
		}
	}

	public void run_black(Scanner sc) {
		boolean flag = true;
		System.out.println("==== 블랙리스트 관리 ====");
		while (flag) {
			System.out.println("1.블랙리스트 추가 | 2.블랙리스트 목록 | 3.블랙리스트 삭제 | 4.돌아가기");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				blackservice.addBlack(sc);
				break;
			case 2:
				blackservice.getAll();
				break;
			case 3:
				blackservice.delBlack(sc);
				break;
			case 4:
				flag = false;
				break;
			}
		}
	}

}
