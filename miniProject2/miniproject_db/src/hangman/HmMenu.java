package hangman;

import java.util.Scanner;

import hangman.HmService;
import hangman.HmServiceImpl;
import player.Player;
import player.PlayerDao;
import player.PlayerDaoImpl;
import player.PlayerServiceImpl;


public class HmMenu {
	private HmService hmService;
	private WordService wordService;
	private PlayerDao pdao;

	public HmMenu() {
		hmService = new HmServiceImpl();
		wordService = new WordServiceImpl();
		pdao = new PlayerDaoImpl();
	}

	public void run(Scanner sc) {
		boolean flag = true;
		while (flag) {
			System.out.println("1.단어 추가 2.단어 수정 3.단어 삭제 4.카테고리 삭제 5.단어 조회 6.행맨 ");
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
				run_hm(sc);
				break;
			}
		}
	}
	
	

	public void run_hm(Scanner sc) {
		System.out.println("\n=============================== 행맨  ===============================");
		Player p = pdao.select(PlayerServiceImpl.getLogin_id());

		hmService.playGame(p);
	}
}
