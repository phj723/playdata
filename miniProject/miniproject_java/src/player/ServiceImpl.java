package player;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ServiceImpl implements Service {

	private Dao dao;
	private String path = "src/play/player.dat";

	public ServiceImpl() {
		dao = new DaoImpl();
	}

	@Override
	public void addPlayer(Scanner sc) {
		System.out.println("\n플레이어명을 입력하세요:");
		String name = sc.next();
		int point = 300;
		System.out.println("\n환영합니다, " + name + "님! 300 포인트가 추가되었습니다!\n");

		Player p = new Player(name, point);
		dao.addPlayer(p);
	}

	@Override
	public Player getByNum(int num) {
		return dao.selectByNum(num);
	}

	@Override
	public int checkPlayerList() {
		if (dao.showAll().isEmpty()) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	public void printPlayerByNum(Scanner sc) {
		System.out.println("플레이어 번호를 입력하시오.");
		System.out.print("플레이어 번호: ");
		int num = sc.nextInt();
		Player p = dao.selectByNum(num);
		if (p == null) {
			System.out.println("해당 플레이어가 존재하지 않습니다.");
		} else {
			System.out.println(p);
		}
	}

	@Override
	public void showAllPlayer() {
		ArrayList<Player> arr = dao.showAll();
		if (arr.isEmpty()) {
			System.out.println("저장된 플레이어가 없습니다. 플레이어 메뉴에서 플레이어를 추가하세요.\n");
		} else {
			for (int i = 0; i < arr.size(); i++)
				arr.sort(new Comparator<Player>() {
					@Override
					public int compare(Player o1, Player o2) {
						int point0 = o1.getNum();
						int point1 = o2.getNum();
						if (point0 == point1)
							return 0;
						else if (point0 > point1)
							return 1;
						else
							return -1;
					}
				});
			System.out.println("List of Players");
			for (int i = 0; i < arr.size(); i++)
				System.out.println(arr.get(i));
		}
	}

	@Override
	public void rankPlayer() {
		ArrayList<Player> arr = dao.rank();

		if (arr.isEmpty()) {
			System.out.println("저장된 플레이어가 없습니다. 플레이어 메뉴에서 플레이어를 추가하세요.\n");
		} else {
			for (int i = 0; i < arr.size(); i++)
				arr.sort(new Comparator<Player>() {
					@Override
					public int compare(Player o1, Player o2) {
						int point0 = o1.getPoint();
						int point1 = o2.getPoint();
						if (point0 == point1)
							return 0;
						else if (point1 > point0)
							return 1;
						else
							return -1;
					}
				});
			System.out.println("Point Ranking");
			for (int i = 0; i < arr.size(); i++)
				System.out.println(arr.get(i).toString());
		}
	}

	@Override
	public void delPlayer(Scanner sc) {
		System.out.println("플레이어 목록:");
		showAllPlayer();
		System.out.println("\n플레이어 번호로 선택:");
		int num = sc.nextInt();
		dao.delPlayer(num);
	}

	@Override
	public void start() {
		try {
			FileInputStream fi = new FileInputStream(path);

			if (fi.available() > 0) {// 스트림에 읽을 데이터의 크기 반환
				ObjectInputStream oi = new ObjectInputStream(fi);
				int num_max = -1;

				while (fi.available() > 0) {
					Player p = (Player) oi.readObject();
					dao.addPlayer(p);
					if (num_max < p.getNum()) {
						num_max = p.getNum();
					}
				}

				Player.setCnt(num_max);
				oi.close();
			}
			fi.close();
		} catch (EOFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("No saved data");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		try {
			FileOutputStream fo = new FileOutputStream(path);
			ObjectOutputStream oo = new ObjectOutputStream(fo);
			ArrayList<Player> list = dao.showAll();
			for (Player p : list) {
				oo.writeObject(p);
			}
			oo.close();
			fo.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
