package player;

import java.util.ArrayList;

public class DaoImpl implements Dao {
	private ArrayList<Player> players;

	public DaoImpl() {
		players = new ArrayList<>();
	}

	@Override
	public void addPlayer(Player p) {
		players.add(p);
	}

	@Override
	public Player selectByNum(int num) {
		Player p = new Player();
		p.setNum(num);
		int idx = players.indexOf(p);
		if (idx < 0) {
			return null;
		} else {
			return players.get(idx);
		}
	}

	@Override
	public ArrayList<Player> showAll() {
		return players;
	}
	
	@Override
	public ArrayList<Player> rank() {
		return players;
	}

	@Override
	public void delPlayer(int num) {
		Player p = new Player();
		p.setNum(num);
		boolean flag = players.remove(p);
		if (flag) {
			System.out.println("플레이어 삭제 완료.\n");
		} else {
			System.out.println("해당 플레이어가 존재하지 않습니다.\n");
		}
	}


}
