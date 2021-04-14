package player;

import java.util.Scanner;

//회원 파트 기능 구현
public class PlayerServiceImpl implements PlayerService {
	private PlayerDao dao;
	private static String Login_id = null; // null이면 로그아웃 상태, !null이면 로그인 상태

	public PlayerServiceImpl() {
		dao = new PlayerDaoImpl();
	}

	public static String getLogin_id() {
		return Login_id;
	}

	@Override
	public void addPlayer(Scanner sc) {
		System.out.println("==== 회원 가입 ====");
		// 회원 가입에 필요한 정보 입력
		String id = "";
		String nickname = "";
		Player p = null;
		do { // id 중복 체크
			System.out.print("ID : ");
			id = sc.next();
			p = dao.select(id);
			if(p != null) {
				System.out.println("이미 존재하는 ID입니다.");
			}
		} while (p != null);

		System.out.print("Password : ");
		String pwd = sc.next();
		do { // nickname 중복 체크
			System.out.print("Nickname : ");
			nickname = sc.next();
			p = dao.selectnick(nickname);
			if(p != null) {
				System.out.println("이미 존재하는 닉네임입니다.");
			}
		} while (p != null);
		// dao의 insert로 입력한 정보 영구저장
		dao.insert(new Player(id, pwd, nickname, null, 0));
	}

	@Override
	public void login(Scanner sc) {
		System.out.println("==== 로그인 ====");
		System.out.print("ID : ");
		String id = sc.next();
		System.out.print("Password : ");
		String pwd = sc.next();

		Player p = dao.select(id);
		if (p == null) {
			System.out.println("없는 아이디입니다.");
		} else {
			if (pwd.equals(p.getPwd())) {
				System.out.println("로그인 성공");
				Login_id = id;
			} else {
				System.out.println("Password 불일치");
			}
		}
	}

	@Override
	public void logout(Scanner sc) {
		System.out.println("==== 로그아웃 ====");
		if (loginCheck()) {
			Login_id = null; // 로그아웃 처리
		} else {
			System.out.println("로그인 상태가 아닙니다.");
		}
	}

	@Override
	public boolean myInfo() {
		System.out.println("==== 내 정보 확인 ====");
		if (loginCheck()) {
			Player p = dao.select(Login_id);
			System.out.println(p);
			return true;
		} else {
			System.out.println("로그인 하세요.");
			return false;
		}
	}

	@Override
	public void editPwd(Scanner sc) {
		boolean flag = myInfo();
		if (flag) {
			System.out.println("==== 비밀변호 변경 ====");
			System.out.print("New Password : ");
			String pwd = sc.next();
			dao.update(Login_id, pwd);
		}
	}

	@Override
	public void editNick(Scanner sc) {
		boolean flag = myInfo();
		String nickname = "";
		Player p = null;
		if (flag) {
			System.out.println("==== 닉네임 변경 ====");
			do {
				System.out.print("New Nickname : ");
				nickname = sc.next();
				p = dao.selectnick(nickname);
				if(p != null) {
					System.out.println("이미 존재하는 닉네임입니다.");
				}
			} while (p != null);
			dao.updateNick(Login_id, nickname);
			dao.lossPoint(Login_id, 30);
		}
	}

	@Override
	public void delPlayer() {
		System.out.println("==== 회원 탈퇴 ====");
		if (loginCheck()) {
			dao.delete(Login_id);
			Login_id = null; // 로그아웃 처리
		} else {
			System.out.println("로그인 상태가 아닙니다.");
		}
	}

	@Override
	public boolean loginCheck() {
		if (Login_id == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void rank() {
		System.out.println("==== 랭킹 ====");
		for(int i = 0; i < dao.getAll().size(); i++) {
			System.out.println((i+1) + "위 - " + dao.getAll().get(i).getInfo());
		}
	}
	
	// manager 전용 method
	@Override
	public void playerInfo() {
		System.out.println("==== 회원 정보 ====");
		for (Player p : dao.getAll()) {
			System.out.println(p);
		}
	}

	@Override
	public void editPlayerNick(Scanner sc) {
		boolean flag = myInfo();
		String newNick = "";
		System.out.println("==== Player 닉네임 변경 ====");
		System.out.print("변경할 Player Nickname : ");
		String nickname = sc.next();
		Player p = dao.selectnick(nickname);
		if (flag) {
			do {
				System.out.print("New Nickname : ");
				newNick = sc.next();
				p = dao.selectnick(newNick);
				if(p != null) {
					System.out.println("이미 존재하는 닉네임입니다.");
				}
			} while (p != null);
			dao.updateNick(p.getId(), newNick);
		}
	}

	// manager 전용 method
	@Override
	public void removePlayer(Scanner sc) {
		System.out.println("==== 회원 삭제 ====");
		System.out.print("삭제할 회원 ID : ");
		String id = sc.next();
		if (dao.select(id) != null) {
			dao.delete(id);
			System.out.println("삭제가 완료되었습니다.");

		} else {
			System.out.println("없는 ID입니다.");
		}
	}

}
