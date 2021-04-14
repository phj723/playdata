package forumRep;

import java.util.ArrayList;
import java.util.Scanner;

import forum.Forum;
import forum.ForumDao;
import forum.ForumDaoImpl;
import player.PlayerServiceImpl;

public class RepServiceImpl implements RepService {
	private RepDao dao;
	private ForumDao fdao;

	public RepServiceImpl() {
		dao = new RepDaoImpl();
		fdao = new ForumDaoImpl();
	}

	@Override
	public void addRep(Scanner sc) {
		boolean flag = true;
		int art_num = 0;
		System.out.println("==== 댓글 작성 ====");
		for (Forum f : fdao.selectAll()) {
			System.out.println(f);
		}
		Rep r = new Rep();
		r.setWriter(PlayerServiceImpl.getLogin_id());

		while (flag) {
			System.out.print("댓글을 달 게시글 번호 : ");
			art_num = sc.nextInt();
			if (fdao.selectByNum(art_num) == null) {
				System.out.println("존재하지 않는 게시글입니다.");
				return;
			} else {
				flag = false;
			}
		}
		System.out.print("Content(멈추려면 /stop) : ");
		StringBuilder st = new StringBuilder();

		while (true) {
			String str = sc.nextLine();
			if (str.startsWith("/stop")) {
				break;
			}
			st.append(str);
		}

		String content = st.toString();
		r.setArt_num(art_num);
		r.setContent(content);
		dao.insert(r);
	}

	@Override
	public void printAll(ArrayList<Rep> list) {
		for (Rep r : list) {
			System.out.println(r);
		}
	}

	@Override
	public void editRep(Scanner sc) {
		boolean flag = true;
		Rep r = new Rep();
		System.out.println("==== 댓글 수정 ====");
		printAll(dao.selectByWriter(PlayerServiceImpl.getLogin_id()));
		ArrayList<Rep> list = dao.selectByWriter(PlayerServiceImpl.getLogin_id());
		while (flag) {
			System.out.print("수정할 댓글을 선택하세요 : ");
			int index = sc.nextInt();
			if (index > list.size()) {
				System.out.println("없는 댓글입니다. 다시 선택하세요");
			} else {
				r = list.get(index - 1);
			}
		}

		System.out.print("New Content(멈추려면 /stop) : ");
		StringBuilder st = new StringBuilder();

		while (true) {
			String str = sc.nextLine();
			if (str.startsWith("/stop")) {
				break;
			}
			st.append(str + "\n");
		}
		String content = st.toString();
		r.setContent(content);
		dao.update(r);
	}

	@Override
	public void delRep(Scanner sc) {
		boolean flag = true;
		System.out.println("==== 댓글 삭제 ====");
		printAll(dao.selectByWriter(PlayerServiceImpl.getLogin_id()));
		ArrayList<Rep> list = dao.selectByWriter(PlayerServiceImpl.getLogin_id());
		while (flag) {
			System.out.print("삭제할 댓글을 선택하세요 : ");
			int index = sc.nextInt();
			if (index > list.size()) {
				System.out.println("없는 댓글입니다. 다시 선택하세요");
			} else {
				dao.delete(list.get(index - 1).getRep_num());
				break;
			}
		}
	}

}
