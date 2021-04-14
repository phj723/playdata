package roulette;

import java.util.Scanner;

import player.Player;
import player.PlayerDao;
import player.PlayerDaoImpl;
import player.PlayerServiceImpl;


public class RouletteImpl implements RouletteService {
	
	private PlayerDao pdao = new PlayerDaoImpl();
	
	@Override
	public void betStraight(Scanner sc, Player p) {
		while (true) {
			int x = (int) (Math.random() * 37);// 당첨 번호
			System.out.println("\n얼마를 베팅하시겠습니까?");
			System.out.println("현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
			int bet = sc.nextInt();
			if (bet == 0 || bet < 0) {
				System.out.println("올바르지 않은 베팅 금액입니다.");
				break;
			}
			int reward = (bet * 35);
			if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() < bet) {
				System.out.println("\n포인트가 부족합니다. 현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
				break;
			} else {
				System.out.println(
						"      0\n 1R  2B  3R\n 4B  5R  6B\n 7R  8B  9R\n10B 11B 12R\n13B 14R 15B\n16R 17B 18R\n19R 20B 21R\n22B 23R 24B\n25R 26B 27R\n28B 29B 30R\n31B 32R 33B\n34R 35B 36R");
				System.out.println("\n어디에 베팅하시겠습니까?\n0 ~ 36 사이의 숫자를 입력해주세요.");
			}
			while (true) {
				if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() >= bet) {
					int guess = sc.nextInt();
					if (guess < 0 || guess > 36) {
						System.out.println("\n0 ~ 36 사이의 숫자를 입력해주세요.\n");
					} else {
						System.out.println("\n베팅하신 번호는... " + guess + " 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x == guess) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					}
				}
			}
			break;
		}
	}

	@Override
	public void betSplit(Scanner sc, Player p) {
		while (true) {
			int x = (int) (Math.random() * 37);// 당첨 번호
			System.out.println("\n얼마를 베팅하시겠습니까?");
			System.out.println("현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
			int bet = sc.nextInt();
			if (bet == 0 || bet < 0) {
				System.out.println("올바르지 않은 베팅 금액입니다.");
				break;
			}
			int reward = (bet * 17);
			if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() < bet) {
				System.out.println("\n포인트가 부족합니다. 현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
				break;
			} else {
				System.out.println(
						"      0\n 1R  2B  3R\n 4B  5R  6B\n 7R  8B  9R\n10B 11B 12R\n13B 14R 15B\n16R 17B 18R\n19R 20B 21R\n22B 23R 24B\n25R 26B 27R\n28B 29B 30R\n31B 32R 33B\n34R 35B 36R");
				System.out.println("\n어디에 베팅하시겠습니까?\n룰렛 테이블에서 인접한 두 숫자를 입력해주세요.");
			}
			while (true) {
				if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() >= bet) {
					int guess = sc.nextInt();
					int guess2 = sc.nextInt();
					if ((guess < 0 || guess > 36) || (guess2 < 0 || guess2 > 36)) {
						System.out.println("\n룰렛 테이블에서 인접한 두 숫자를 입력해주세요.\n");
					} else if (guess == guess2) {
						System.out.println("\n서로 다른 값을 입력해주세요.");
					} else if (guess == (guess2 + 3) || guess == (guess2 - 3) || guess == (guess2 + 1)
							|| guess == (guess2 - 1) || (guess == 0 && guess2 == 2)) {
						System.out.println("\n베팅하신 번호는... " + guess + ", " + guess2 + " 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x == guess || x == guess2) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else {
						System.out.println("\n룰렛 테이블에서 인접한 두 숫자를 입력해주세요.");
					}
				}
			}
			break;
		}
	}

	@Override
	public void betStreet(Scanner sc, Player p) {
		while (true) {
			int x = (int) (Math.random() * 37);// 당첨 번호
			System.out.println("\n얼마를 베팅하시겠습니까?");
			System.out.println("현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
			int bet = sc.nextInt();
			if (bet == 0 || bet < 0) {
				System.out.println("올바르지 않은 베팅 금액입니다.");
				break;
			}
			int reward = (bet * 11);
			if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() < bet) {
				System.out.println("\n포인트가 부족합니다. 현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
				break;
			} else {
				System.out.println(
						"      0\n 1R  2B  3R\n 4B  5R  6B\n 7R  8B  9R\n10B 11B 12R\n13B 14R 15B\n16R 17B 18R\n19R 20B 21R\n22B 23R 24B\n25R 26B 27R\n28B 29B 30R\n31B 32R 33B\n34R 35B 36R");
				System.out.println("\n어디에 베팅하시겠습니까?\n0, 또는 행의 맨좌측 값을 입력해주세요.");
			}
			while (true) {
				if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() >= bet) {
					int guess = sc.nextInt();
					if (guess < 0 || guess > 34) {
						System.out.println("\n0, 또는 행의 맨좌측 값을 입력해주세요.\n");
					} else if (guess == 1 || guess == 4 || guess == 7 || guess == 10 || guess == 13 || guess == 16
							|| guess == 19 || guess == 22 || guess == 25 || guess == 28 || guess == 31 || guess == 34) {
						System.out.println("\n베팅하신 번호는... " + guess + " ~ " + (guess + 2) + " 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x == guess || x == (guess + 1) || x == (guess + 2)) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else if (guess == 0) {
						System.out.println("[0,1,2] 를 베팅하시려면 1\n[0,2,3] 을 베팅하시려면 2 를 입력해주세요.");
						int z = sc.nextInt();
						if (z < 1 || z > 2) {
							System.out.println("잘못 입력하셨습니다. 베팅 메뉴로 돌아갑니다.");
							break;
						} else {
							switch (z) {
							case 1:
								System.out.println("\n베팅하신 번호는... 0, 1, 2 입니다.");
								System.out.println("당첨번호는... " + x + " 입니다!");
								if (x == 0 || x == 1 || x == 2) {
									System.out.println("축하합니다! 베팅에 성공하셨습니다!");
									pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
									System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
									break;
								} else {
									System.out.println("아쉽게도 베팅에 실패하였습니다.");
									pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
									System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
									break;
								}
							case 2:
								System.out.println("\n베팅하신 번호는... 0, 2, 3 입니다.");
								System.out.println("당첨번호는... " + x + " 입니다!");
								if (x == 0 || x == 2 || x == 3) {
									System.out.println("축하합니다! 베팅에 성공하셨습니다!");
									pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
									System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
									break;
								} else {
									System.out.println("아쉽게도 베팅에 실패하였습니다.");
									pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
									System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
									break;
								}
							}
						}
						break;
					} else {
						System.out.println("\n0 또는 행의 맨좌측 값을 입력해주세요.");
					}
				}
			}
			break;
		}
	}

	@Override
	public void betCorner(Scanner sc, Player p) {
		while (true) {
			int x = (int) (Math.random() * 37);// 당첨 번호
			System.out.println("\n얼마를 베팅하시겠습니까?");
			System.out.println("현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
			int bet = sc.nextInt();
			if (bet == 0 || bet < 0) {
				System.out.println("올바르지 않은 베팅 금액입니다.");
				break;
			}
			int reward = (bet * 8);
			if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() < bet) {
				System.out.println("\n포인트가 부족합니다. 현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
				break;
			} else {
				System.out.println(
						"      0\n 1R  2B  3R\n 4B  5R  6B\n 7R  8B  9R\n10B 11B 12R\n13B 14R 15B\n16R 17B 18R\n19R 20B 21R\n22B 23R 24B\n25R 26B 27R\n28B 29B 30R\n31B 32R 33B\n34R 35B 36R");
				System.out.println("\n어디에 베팅하시겠습니까?\n[0,1,2,3]을 베팅하시려면 0, 또는 인접한 4개의 값 중 제일 적은 값을 입력해주세요.");
			}
			while (true) {
				if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() >= bet) {
					int guess = sc.nextInt();
					if (guess < 0 || guess > 32) {
						System.out.println("\n[0,1,2,3]을 베팅하시려면 0, 또는 인접한 4개의 값 중 제일 적은 값을 입력해주세요.\n");
					} else if (guess == 0) {
						System.out.println("\n베팅하신 번호는... 0, 1, 2, 3 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x == 0 || x == 1 || x == 2 || x == 3) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else if (guess == 1 || guess == 2 || guess == 4 || guess == 5 || guess == 7 || guess == 8
							|| guess == 10 || guess == 11 || guess == 13 || guess == 14 || guess == 16 || guess == 17
							|| guess == 19 || guess == 20 || guess == 22 || guess == 23 || guess == 25 || guess == 26
							|| guess == 28 || guess == 29 || guess == 31 || guess == 32) {
						System.out.println("\n베팅하신 번호는... " + guess + ", " + (guess + 1) + ", " + (guess + 3) + ", "
								+ (guess + 4) + " 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x == guess || x == (guess + 1) || x == (guess + 3) || x == (guess + 4)) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else {
						System.out.println("\n[0,1,2,3]을 베팅하시려면 0, 또는 인접한 4개의 값 중 제일 적은 값을 입력해주세요.");
					}
				}
			}
			break;
		}
	}

	@Override
	public void betLine(Scanner sc, Player p) {
		while (true) {
			int x = (int) (Math.random() * 37);// 당첨 번호
			System.out.println("\n얼마를 베팅하시겠습니까?");
			System.out.println("현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
			int bet = sc.nextInt();
			if (bet == 0 || bet < 0) {
				System.out.println("올바르지 않은 베팅 금액입니다.");
				break;
			}
			int reward = (bet * 5);
			if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() < bet) {
				System.out.println("\n포인트가 부족합니다. 현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
				break;
			} else {
				System.out.println(
						"      0\n 1R  2B  3R\n 4B  5R  6B\n 7R  8B  9R\n10B 11B 12R\n13B 14R 15B\n16R 17B 18R\n19R 20B 21R\n22B 23R 24B\n25R 26B 27R\n28B 29B 30R\n31B 32R 33B\n34R 35B 36R");
				System.out.println("\n어디에 베팅하시겠습니까?\n베팅하실 연속되는 여섯개의 숫자 중, 첫번째 숫자를 입력해주세요.(0 ~ 31)");
			}
			while (true) {
				if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() >= bet) {
					int guess = sc.nextInt();
					if (guess < 0 || guess > 31) {
						System.out.println("\n베팅하실 연속되는 여섯개의 숫자 중, 첫번째 숫자를 입력해주세요.(0 ~ 31)\n");
					} else {
						System.out.println("\n베팅하신 번호는... " + guess + " ~ " + (guess + 5) + " 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x == guess || x == (guess + 1) || x == (guess + 2) || x == (guess + 3) || x == (guess + 4)
								|| x == (guess + 5)) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					}
				}
			}
			break;
		}
	}

	@Override
	public void betColumn(Scanner sc, Player p) {
		while (true) {
			int x = (int) (Math.random() * 37);// 당첨 번호
			System.out.println("\n얼마를 베팅하시겠습니까?");
			System.out.println("현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
			int bet = sc.nextInt();
			if (bet == 0 || bet < 0) {
				System.out.println("올바르지 않은 베팅 금액입니다.");
				break;
			}
			int reward = (bet * 2);
			if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() < bet) {
				System.out.println("\n포인트가 부족합니다. 현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
				break;
			} else {
				System.out.println(
						"      0\n 1R  2B  3R\n 4B  5R  6B\n 7R  8B  9R\n10B 11B 12R\n13B 14R 15B\n16R 17B 18R\n19R 20B 21R\n22B 23R 24B\n25R 26B 27R\n28B 29B 30R\n31B 32R 33B\n34R 35B 36R");
				System.out.println("\n어디에 베팅하시겠습니까?\n맨좌측 열은 1, 가운데 열은 2, 맨우측 열은 3을 입력해주세요.");
			}
			while (true) {
				if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() >= bet) {
					int guess = sc.nextInt();
					if (guess < 0 || guess > 3) {
						System.out.println("\n맨좌측 열은 1, 가운데 열은 2, 맨우측 열은 3을 입력해주세요.\n");
					} else if (guess == 1) {
						System.out.println("\n베팅하신 번호는... " + guess + ", " + (guess + 3) + ", " + (guess + 6) + ", "
								+ (guess + 9) + ", " + (guess + 12) + ", " + (guess + 15) + ", " + (guess + 18) + ", "
								+ (guess + 21) + ", " + (guess + 24) + ", " + (guess + 27) + ", " + (guess + 30) + ", "
								+ (guess + 33) + " 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x == guess || x == (guess + 3) || x == (guess + 6) || x == (guess + 9)
								|| x == (guess + 12) || x == (guess + 15) || x == (guess + 18) || x == (guess + 21)
								|| x == (guess + 24) || x == (guess + 27) || x == (guess + 30)
								|| x == (guess + 33)) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else if (guess == 2) {
						System.out.println("\n베팅하신 번호는... " + guess + ", " + (guess + 3) + ", " + (guess + 6) + ", "
								+ (guess + 9) + ", " + (guess + 12) + ", " + (guess + 15) + ", " + (guess + 18) + ", "
								+ (guess + 21) + ", " + (guess + 24) + ", " + (guess + 27) + ", " + (guess + 30) + ", "
								+ (guess + 33) + " 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x == guess || x == (guess + 3) || x == (guess + 6) || x == (guess + 9)
								|| x == (guess + 12) || x == (guess + 15) || x == (guess + 18) || x == (guess + 21)
								|| x == (guess + 24) || x == (guess + 27) || x == (guess + 30)
								|| x == (guess + 33)) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else if (guess == 3) {
						System.out.println("\n베팅하신 번호는... " + guess + ", " + (guess + 3) + ", " + (guess + 6) + ", "
								+ (guess + 9) + ", " + (guess + 12) + ", " + (guess + 15) + ", " + (guess + 18) + ", "
								+ (guess + 21) + ", " + (guess + 24) + ", " + (guess + 27) + ", " + (guess + 30) + ", "
								+ (guess + 33) + " 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x == guess || x == (guess + 3) || x == (guess + 6) || x == (guess + 9)
								|| x == (guess + 12) || x == (guess + 15) || x == (guess + 18) || x == (guess + 21)
								|| x == (guess + 24) || x == (guess + 27) || x == (guess + 30)
								|| x == (guess + 33)) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else {
						System.out.println("\n맨좌측 열은 1\n가운데 열은 2\n맨우측 열은 3을 입력해주세요.");
					}
				}
			}
			break;
		}
	}

	@Override
	public void betDozen(Scanner sc, Player p) {
		while (true) {
			int x = (int) (Math.random() * 37);// 당첨 번호
			System.out.println("\n얼마를 베팅하시겠습니까?");
			System.out.println("현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
			int bet = sc.nextInt();
			if (bet == 0 || bet < 0) {
				System.out.println("올바르지 않은 베팅 금액입니다.");
				break;
			}
			int reward = (bet * 2);
			if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() < bet) {
				System.out.println("\n포인트가 부족합니다. 현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
				break;
			} else {
				System.out.println(
						"      0\n 1R  2B  3R\n 4B  5R  6B\n 7R  8B  9R\n10B 11B 12R\n13B 14R 15B\n16R 17B 18R\n19R 20B 21R\n22B 23R 24B\n25R 26B 27R\n28B 29B 30R\n31B 32R 33B\n34R 35B 36R");
				System.out.println("\n어디에 베팅하시겠습니까?\n1 ~ 12는 1\n13 ~ 24는 2\n25 ~ 36은 3 을 입력해주세요.");
			}
			while (true) {
				if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() >= bet) {
					int guess = sc.nextInt();
					if (guess < 0 || guess > 3) {
						System.out.println("\n1 ~ 12는 1,\n13 ~ 24는 2,\n25 ~ 36은 3 을 입력해주세요.\n");
					} else if (guess == 1) {
						System.out.println("\n베팅하신 번호는... 1 ~ 12 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x == guess || x == (guess + 1) || x == (guess + 2) || x == (guess + 3)
								|| x == (guess + 4) || x == (guess + 5) || x == (guess + 6) || x == (guess + 7)
								|| x == (guess + 8) || x == (guess + 9) || x == (guess + 10) || x == (guess + 11)) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else if (guess == 2) {
						System.out.println("\n베팅하신 번호는... 13 ~ 24 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if ((x == guess + 22) || x == (guess + 11) || x == (guess + 12) || x == (guess + 13)
								|| x == (guess + 14) || x == (guess + 15) || x == (guess + 16) || x == (guess + 17)
								|| x == (guess + 18) || x == (guess + 19) || x == (guess + 20)
								|| x == (guess + 21)) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else if (guess == 3) {
						System.out.println("\n베팅하신 번호는... 25 ~ 36 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if ((x == guess + 22) || x == (guess + 23) || x == (guess + 24) || x == (guess + 25)
								|| x == (guess + 26) || x == (guess + 27) || x == (guess + 28) || x == (guess + 29)
								|| x == (guess + 30) || x == (guess + 31) || x == (guess + 32)
								|| x == (guess + 33)) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else {
						System.out.println("\n1 ~ 12는 1,\n13 ~ 24는 2,\n25 ~ 36은 3 을 입력해주세요.");
					}
				}
			}
			break;
		}
	}

	@Override
	public void betColor(Scanner sc, Player p) {
		while (true) {
			int x = (int) (Math.random() * 37);// 당첨 번호
			System.out.println("\n얼마를 베팅하시겠습니까?");
			System.out.println("현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
			int bet = sc.nextInt();
			if (bet == 0 || bet < 0) {
				System.out.println("올바르지 않은 베팅 금액입니다.");
				break;
			}
			int reward = bet;
			if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() < bet) {
				System.out.println("\n포인트가 부족합니다. 현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
				break;
			} else {
				System.out.println(
						"      0\n 1R  2B  3R\n 4B  5R  6B\n 7R  8B  9R\n10B 11B 12R\n13B 14R 15B\n16R 17B 18R\n19R 20B 21R\n22B 23R 24B\n25R 26B 27R\n28B 29B 30R\n31B 32R 33B\n34R 35B 36R");
				System.out.println("\n어디에 베팅하시겠습니까?\nBlack (B)는 1, Red (R)은 2를 입력해주세요.");
			}
			while (true) {
				if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() >= bet) {
					int guess = sc.nextInt();
					if (guess < 1 || guess > 2) {
						System.out.println("\nBlack (B)는 1, Red (R)은 2를 입력해주세요.\n");
					} else if (guess == 1) {
						System.out.println("\n베팅하신 색은... 검은색입니다.");
						if (x == 2 || x == 4 || x == 6 || x == 8 || x == 10 || x == 11 || x == 13 || x == 15
								|| x == 17 || x == 20 || x == 22 || x == 24 || x == 26 || x == 28 || x == 29
								|| x == 31 || x == 33 || x == 35) {
							System.out.println("당첨번호는... " + x + ", 검은색 번호입니다!");
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("당첨번호는... " + x + ", 빨간색 번호입니다!");
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else if (guess == 2) {
						System.out.println("\n베팅하신 색은... 빨간색입니다.");
						if (x == 1 || x == 3 || x == 5 || x == 7 || x == 9 || x == 12 || x == 14 || x == 16
								|| x == 18 || x == 19 || x == 21 || x == 23 || x == 25 || x == 27 || x == 30
								|| x == 32 || x == 34 || x == 36) {
							System.out.println("당첨번호는... " + x + ", 빨간색 번호입니다!");
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("당첨번호는... " + x + ", 검은색 번호입니다!");
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else {
						System.out.println("\nBlack (B)는 1, Red (R)은 2를 입력해주세요.");
					}
				}
			}
			break;
		}
	}

	@Override
	public void betEO(Scanner sc, Player p) {
		while (true) {
			int x = (int) (Math.random() * 37);// 당첨 번호
			System.out.println("\n얼마를 베팅하시겠습니까?");
			System.out.println("현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
			int bet = sc.nextInt();
			if (bet == 0 || bet < 0) {
				System.out.println("올바르지 않은 베팅 금액입니다.");
				break;
			}
			int reward = bet;
			if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() < bet) {
				System.out.println("\n포인트가 부족합니다. 현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
				break;
			} else {
				System.out.println(
						"      0\n 1R  2B  3R\n 4B  5R  6B\n 7R  8B  9R\n10B 11B 12R\n13B 14R 15B\n16R 17B 18R\n19R 20B 21R\n22B 23R 24B\n25R 26B 27R\n28B 29B 30R\n31B 32R 33B\n34R 35B 36R");
				System.out.println("\n어디에 베팅하시겠습니까?\n홀수는 1, 짝수는 2를 입력해주세요.");
			}
			while (true) {
				if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() >= bet) {
					int guess = sc.nextInt();
					if (guess < 1 || guess > 2) {
						System.out.println("\n홀수는 1, 짝수는 2를 입력해주세요.\n");
					} else if (guess == 1) {
						System.out.println("\n베팅하신 번호는... 홀수 번호입니다.");
						if (x % 2 != 0) {
							System.out.println("당첨번호는... " + x + ", 홀수 번호입니다!");
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("당첨번호는... " + x + ", 짝수 번호입니다!");
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else if (guess == 2) {
						System.out.println("\n베팅하신 번호는... 짝수 번호입니다.");
						if (x % 2 == 0) {
							System.out.println("당첨번호는... " + x + ", 짝수 번호입니다!");
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("당첨번호는... " + x + ", 홀수 번호입니다!");
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else {
						System.out.println("\n홀수는 1, 짝수는 2를 입력해주세요.");
					}
				}
			}
			break;
		}
	}

	@Override
	public void betLH(Scanner sc, Player p) {
		while (true) {
			int x = (int) (Math.random() * 37);// 당첨 번호
			System.out.println("\n얼마를 베팅하시겠습니까?");
			System.out.println("현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
			int bet = sc.nextInt();
			if (bet == 0 || bet < 0) {
				System.out.println("올바르지 않은 베팅 금액입니다.");
				break;
			}
			int reward = bet;
			if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() < bet) {
				System.out.println("\n포인트가 부족합니다. 현재 보유 중이신 포인트는 " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() + " 포인트 입니다.");
				break;
			} else {
				System.out.println(
						"      0\n 1R  2B  3R\n 4B  5R  6B\n 7R  8B  9R\n10B 11B 12R\n13B 14R 15B\n16R 17B 18R\n19R 20B 21R\n22B 23R 24B\n25R 26B 27R\n28B 29B 30R\n31B 32R 33B\n34R 35B 36R");
				System.out.println("\n어디에 베팅하시겠습니까?\n1 ~ 18은 1, 19 ~ 36은 2를 입력해주세요.");
			}
			while (true) {
				if (pdao.select(PlayerServiceImpl.getLogin_id()).getPoints() >= bet) {
					int guess = sc.nextInt();
					if (guess < 1 || guess > 2) {
						System.out.println("\n1 ~ 18은 1, 19 ~ 36은 2를 입력해주세요.\n");
					} else if (guess == 1) {
						System.out.println("\n베팅하신 번호는... 1 ~ 18 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x >= 1 && x <= 18) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else if (guess == 2) {
						System.out.println("\n베팅하신 번호는... 19 ~ 36 입니다.");
						System.out.println("당첨번호는... " + x + " 입니다!");
						if (x >= 19 && x <= 36) {
							System.out.println("축하합니다! 베팅에 성공하셨습니다!");
							pdao.getPoint(PlayerServiceImpl.getLogin_id(), reward);
							System.out.println(reward + " 포인트 획득! 현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						} else {
							System.out.println("아쉽게도 베팅에 실패하였습니다.");
							pdao.lossPoint(PlayerServiceImpl.getLogin_id(),bet);
							System.out.println("현재 보유 포인트: " + pdao.select(PlayerServiceImpl.getLogin_id()).getPoints());
							break;
						}
					} else {
						System.out.println("\n1 ~ 18은 1, 19 ~ 36은 2를 입력해주세요.");
					}
				}
			}
			break;
		}
	}

	

}
