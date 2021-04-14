package player;

import java.util.ArrayList;

public interface Dao {
	void addPlayer(Player p);// 플레이어 추가

	Player selectByNum(int num);// 플레이어 선택

	ArrayList<Player> showAll();// 플레이어 모두 공개

	ArrayList<Player> rank();// 플레이어 포인트 순으로 정렬

	void delPlayer(int num);// 플레이어 삭제
}
