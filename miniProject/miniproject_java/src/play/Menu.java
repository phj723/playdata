package play;

import java.util.Scanner;

import hangman.HmService;
import hangman.HmServiceImpl;
import holdem.HoldemPlayImpl;
import player.Player;
import player.Service;
import player.ServiceImpl;
import roulette.RouletteImpl;

public class Menu {
	private Service service;
	private HoldemPlayImpl hp;
	private Player ai;
	private HmService hmService;
	private RouletteImpl rouletteImpl;
	private RuleBook rb;

	public Menu() {
		service = new ServiceImpl();
		hmService = new HmServiceImpl();
		rouletteImpl = new RouletteImpl();
		rb = new RuleBook();
	}

	public void run(Scanner sc) {
		boolean flag = true;
		service.start();
		while (flag) {
			System.out.println("================================ 메인 메뉴 ================================");
			System.out.println("1.플레이어 메뉴 | 2.게임 설명 | 3.유러피언 룰렛 | 4.레인보우 홀덤 | 5.행맨 | 6.저장 및 종료");
			int m = sc.nextInt();
			if (m < 1 || m > 6)
				System.out.println("범위 내의 번호를 입력해주세요.");
			switch (m) {
			case 1:
				run_p(sc);
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
				flag = false;
				break;

			}
		}
		service.stop();
	}

	public void run_p(Scanner sc) {
		boolean flag = true;
		while (flag) {
			System.out.println("\n=============================== 플레이어 메뉴 ===============================");
			System.out.println("1.플레이어 추가 | 2.플레이어 정보 | 3.포인트 순위 | 4.플레이어 삭제 | 5.메인 메뉴");
			int m = sc.nextInt();
			if (m < 1 || m > 5)
				System.out.println("범위 내의 번호를 입력해주세요.");
			switch (m) {
			case 1:
				service.addPlayer(sc);
				break;
			case 2:
				service.showAllPlayer();
				break;
			case 3:
				service.rankPlayer();
				break;
			case 4:
				service.delPlayer(sc);
				break;
			case 5:
				flag = false;
				break;
			}
		}
	}

	public void run_g(Scanner sc) {
		System.out.println("\n=============================== 유러피언 룰렛 ===============================");
		System.out.println("\n사용할 플레이어를 선택해주세요:");
		service.showAllPlayer();
		int num = 0;
		Player p = null;
		if (service.checkPlayerList() == 0) {
			return;
		} else {
			System.out.print("\n플레이어 번호로 선택: \n");
			num = sc.nextInt();
			if (service.getByNum(num) == null) {
				System.out.println("플레이어 번호를 확인하세요.");
				return;
			} else {
				p = service.getByNum(num);
			}
		}

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
		System.out.println("\n사용할 플레이어를 선택해주세요:");
		service.showAllPlayer();
		int num = 0;
		Player p = null;
		if (service.checkPlayerList() == 0) {
			return;
		} else {
			System.out.print("\n플레이어 번호로 선택: \n");
			num = sc.nextInt();
			if (service.getByNum(num) == null) {
				System.out.println("플레이어 번호를 확인하세요.");
				return;
			} else {
				p = service.getByNum(num);
			}
		}

		System.out.println("============================================");
		System.out.println("Rainbow Holdem 게임을 시작합니다.");
		System.out.println("============================================");
		System.out.print("\n");

		// 카드 분배
		System.out.println("============================================");
		System.out.println("카드 분배");
		hp.draw(hp.getDeck(), p, ai);
		System.out.println("============================================");
		System.out.print("\n");

		// 받은 카드 확인
		System.out.println("============================================");
		System.out.println("카드를 확인하세요");
		for (int c : p.getCards()) {
			System.out.print(c + " ");
		}
		System.out.print("\n");
		System.out.println("============================================");
		System.out.print("\n");

		// 카드 1장 오픈
		System.out.println("============================================");
		hp.openCard(); // dealer
		hp.openCard(sc, p); // 플레이어
		hp.openCard(ai); // ai
		System.out.println("============================================");
		System.out.print("\n");

		// 첫 번째 배팅
		System.out.println("============================================");
		hp.aiFirBetting(); // 초기 배팅 금액 10
		System.out.println("");
		hp.betting(sc, p);
		System.out.println("============================================");
		System.out.print("\n");

		// 두 번째 카드 오픈
		System.out.println("============================================");
		hp.openCard(); // dealer
		hp.openCard(sc, p); // 플레이어
		hp.openCard(ai); // ai
		System.out.println("============================================");
		System.out.print("\n");

		// 두 번째 배팅
		System.out.println("============================================");
		hp.aiBetting(ai);
		System.out.println("");
		hp.betting(sc, p);
		System.out.println("============================================");
		System.out.print("\n");

		// 세 번째 카드 오픈
		System.out.println("============================================");
		hp.openCard(); // dealer
		hp.openCard(sc, p); // 플레이어
		hp.openCard(ai); // ai
		System.out.println("============================================");
		System.out.print("\n");

		// 마지막 배팅
		System.out.println("============================================");
		hp.aiBetting(ai);
		System.out.println("");
		hp.betting(sc, p);
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
		System.out.println("Player의 합계 : " + hp.checkSum(p));
		System.out.println("aiPlayer의 합계 : " + hp.checkSum(ai));
		System.out.println("============================================");
		System.out.print("\n");

		// Rainbow 여부 확인
		System.out.println("============================================");
		System.out.println("Rainbow?");
		System.out.println("Player : " + hp.checkRainbow(p));
		System.out.println("aiPlayer : " + hp.checkRainbow(ai));
		System.out.println("============================================");
		System.out.print("\n");

		// 결과
		System.out.println("============================================");
		hp.result(hp, p, ai);
		System.out.println("============================================");
		System.out.print("\n");

	}

	public void run_hm(Scanner sc) {
		System.out.println("\n=============================== 행맨  ===============================");
		System.out.println("\n사용할 플레이어를 선택해주세요:");
		service.showAllPlayer();
		int num = 0;
		Player p = null;
		if (service.checkPlayerList() == 0) {
			return;
		} else {
			System.out.print("\n플레이어 번호로 선택: \n");
			num = sc.nextInt();
			if (service.getByNum(num) == null) {
				System.out.println("플레이어 번호를 확인하세요.");
				return;
			} else {
				p = service.getByNum(num);
			}
		}
		hmService.playGame(p);
	}
}
