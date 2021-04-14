package blacklist;

import java.util.Scanner;

import player.PlayerDao;
import player.PlayerDaoImpl;

public class BlackServiceImpl implements BlackService {
	private BlackDao bdao;
	private PlayerDao pdao;

	public BlackServiceImpl() {
		bdao = new BlackDaoImpl();
		pdao = new PlayerDaoImpl();
	}

	@Override
	public void addBlack(Scanner sc) {
		boolean flag = true;
		String id = "";
		System.out.println("==== Blacklist 추가 ====");
		while (flag) {
			System.out.print("추가할 ID : ");
			id = sc.next();
			if (bdao.select(id) != null) {
				System.out.println("이미 list에 존재하는 ID입니다.");
			} else if (pdao.select(id) == null) {
				System.out.println("존재하지 않는 ID입니다.");
			} else {
				flag = false;
			}
		}
		bdao.insert(new BlackList(id));
	}

	@Override
	public void getAll() {
		System.out.println("==== Blacklist 명단 ====");
		for (BlackList b : bdao.selectAll()) {
			System.out.println(b);
		}
	}

	@Override
	public BlackList getById(String id) {
		BlackList b = bdao.select(id);
		return b;
	}

	@Override
	public void delBlack(Scanner sc) {
		System.out.println("==== 회원 삭제 ====");
		System.out.print("삭제할 회원 ID : ");
		String id = sc.next();
		if (bdao.select(id) != null) {
			bdao.delete(bdao.select(id));
			System.out.println("삭제가 완료되었습니다.");
		} else {
			System.out.println("없는 ID입니다.");
		}
	}

}
