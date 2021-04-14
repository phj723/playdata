package forum;

import java.util.ArrayList;
import java.util.Scanner;

import forumRep.Rep;
import forumRep.RepDao;
import forumRep.RepDaoImpl;
import player.PlayerServiceImpl;

public class ForumServiceImpl implements ForumService {
	private ForumDao dao;
	private RepDao rdao;

	public ForumServiceImpl() {
		dao = new ForumDaoImpl();
		rdao = new RepDaoImpl();
	}

	@Override
	public void addBoard(Scanner sc) {
		boolean flag = true;
		int num = 0;
		String category = "";
		System.out.println("==== 글 작성 ====");

		Forum f = new Forum();
		f.setWriter(PlayerServiceImpl.getLogin_id());

		while (flag) {
			System.out.println("1.자유게시판 | 2.유러피언 룰렛 | 3.레인보우 홀덤 | 4.행맨");
			System.out.print("작성할 카테고리 : ");
			sc.nextLine();
			num = sc.nextInt();
			if (num >= 1 && num <= 4) {
				switch (num) {
				case 1:
					category = "자유게시판";
					break;
				case 2:
					category = "유러피언 룰렛";
					break;
				case 3:
					category = "레인보우 홀덤";
					break;
				case 4:
					category = "행맨";
					break;
				}
				flag = false;
			} else {
				System.out.println("카테고리를 다시 입력하세요");
			}
		}

		System.out.print("제목 : ");
		sc.nextLine();
		String title = sc.nextLine();

		System.out.print("내용(멈추려면 /stop) : ");
		StringBuilder st = new StringBuilder();

		while (true) {
			String str = sc.nextLine();
			if (str.startsWith("/stop")) {
				break;
			}
			st.append(str);
		}

		String content = st.toString();
		f.setTitle(title);
		f.setContent(content);
		f.setCategory(category);
		dao.insert(f);
	}

	@Override
	public void printAll(ArrayList<Forum> list) {
		for (Forum f : list) {
			if (f == null) {
				continue;
			}
			System.out.println(f);
		}
	}

	@Override
	public ArrayList<Forum> getAll() {
		System.out.println("==== 게시글 목록 ====");
		ArrayList<Forum> list = dao.selectAll();
		return list;
	}

	@Override
	public void getByNum(Scanner sc) {
		System.out.println("==== 글 번호로 검색 ====");
		System.out.print("검색할 글번호 : ");
		int num = sc.nextInt();
		Forum f = dao.selectByNum(num);
		if (f == null) {
			System.out.println("없는 글 번호");
		} else {
			System.out.println(f);
			if (rdao.selectByArtNum(num) != null) {
				for (Rep r : rdao.selectByArtNum(num)) {
					System.out.println(r);
				}
			}
		}
	}

	@Override
	public ArrayList<Forum> getByWriter(Scanner sc) {
		System.out.println("==== 글 작성자로 검색 ====");
		System.out.print("검색할 작성자 아이디 : ");
		String writer = sc.next();
		ArrayList<Forum> list = dao.selectByWriter(writer);
		return list;
	}

	@Override
	public ArrayList<Forum> getByWriter(String writer) {
		ArrayList<Forum> list = dao.selectByWriter(writer);
		return list;
	}

	@Override
	public ArrayList<Forum> getByTitle(Scanner sc) {
		System.out.println("==== 글 제목으로 검색 ====");
		System.out.print("검색할 단어 : ");
		String title = sc.next();
		ArrayList<Forum> list = dao.selectByTitle(title);
		return list;

	}

	@Override
	public ArrayList<Forum> getByCategory(Scanner sc) {
		System.out.println("==== 카테고리로 검색 ====");
		boolean flag = true;
		int num = 0;
		String category = "";
		while (flag) {
			System.out.println("1.자유게시판 | 2.유러피언 룰렛 | 3.레인보우 홀덤 | 4.행맨 | 5.공지사항");
			System.out.print("검색할 카테고리 : ");
			num = sc.nextInt();
			if (num >= 1 && num <= 5) {
				switch(num) {
				case 1:
					category = "자유게시판";
					break;
				case 2:
					category = "유러피언 룰렛";
					break;
				case 3:
					category = "레인보우 홀덤";
					break;
				case 4:
					category = "행맨";
					break;
				case 5:
					category = "공지사항";
					break;
				}
				break;
			} else {
				System.out.println("카테고리를 다시 입력하세요");
			}
		}
		ArrayList<Forum> list = dao.selectByCategory(category);
		return list;
	}

	public boolean checkMyArticle(int num) {
		Forum f = dao.selectByNum(num);
		if (f != null && f.getWriter().equals(PlayerServiceImpl.getLogin_id())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void editBoard(Scanner sc) {
		System.out.println("==== 글 수정 ====");
		printAll(dao.selectByWriter(PlayerServiceImpl.getLogin_id()));
		System.out.print("수정할 글 번호 : ");
		int num = sc.nextInt();
		boolean flag1 = checkMyArticle(num);
		if (flag1) {
			System.out.print("새 제목 : ");
			String title = sc.next();
			System.out.print("내용(멈추려면 /stop) : ");
			StringBuilder st = new StringBuilder();

			while (true) {
				String str = sc.nextLine();
				if (str.startsWith("/stop")) {
					break;
				}
				st.append(str + "\n");
			}
			String content = st.toString();

			dao.update(new Forum(num, "", null, title, content, null));
		} else {
			System.out.println("없는 글이거나 자기 글이 아닙니다.");
		}
	}

	@Override
	public void editCatBoard(Scanner sc) {
		System.out.println("==== 글 수정 ====");
		printAll(dao.selectByWriter(PlayerServiceImpl.getLogin_id()));
		System.out.print("수정할 글 번호 : ");
		int num = sc.nextInt();
		boolean flag1 = checkMyArticle(num);
		boolean flag2 = true;
		int num2 = 0;
		String category = "";
		if (flag1) {
			while (flag2) {
				System.out.println("1.자유게시판 | 2.유러피언 룰렛 | 3.레인보우 홀덤 | 4.행맨");
				System.out.print("카테고리 : ");
				sc.nextLine();
				num2 = sc.nextInt();
				if (num2 >= 1 && num2 <= 4) {
					switch (num2) {
					case 1:
						category = "자유게시판";
						break;
					case 2:
						category = "유러피언 룰렛";
						break;
					case 3:
						category = "레인보우 홀덤";
						break;
					case 4:
						category = "행맨";
						break;
					}
					flag2 = false;
				} else {
					System.out.println("카테고리를 다시 입력하세요");
				}
			}
			dao.update(new Forum(num, "", null, "", "", category));
		} else {
			System.out.println("없는 글이거나 자기 글이 아닙니다.");
		}

	}

	@Override
	public void delBoard(Scanner sc) {
		System.out.println("==== 글 삭제 ====");
		printAll(dao.selectByWriter(PlayerServiceImpl.getLogin_id()));
		System.out.print("삭제할 글 번호 : ");
		int num = sc.nextInt();
		boolean flag = checkMyArticle(num);
		if (flag) {
			dao.delete(num);
		} else {
			System.out.println("없는 글이거나 자기 글이 아닙니다.");
		}
	}

	// manager 전용 method
	@Override
	public void mEditBoard(Scanner sc) {
		System.out.println("==== 글 수정 ====");
		System.out.print("수정할 글 번호 : ");
		int num = sc.nextInt();
		String category = "";
		if (dao.selectByNum(num) != null) {
			boolean flag2 = true;
			int num2 = 0;
			while (flag2) {
				System.out.println("1.자유게시판 | 2.유러피언 룰렛 | 3.레인보우 홀덤 | 4.행맨 | 5.공지사항");
				System.out.print("카테고리 : ");
				sc.nextLine();
				num2 = sc.nextInt();
				if (num2 >= 1 && num2 <= 5) {
					switch (num) {
					case 1:
						category = "자유게시판";
						break;
					case 2:
						category = "유러피언 룰렛";
						break;
					case 3:
						category = "레인보우 홀덤";
						break;
					case 4:
						category = "행맨";
						break;
					case 5:
						category = "공지사항";
						break;
					}
					flag2 = false;
				} else {
					System.out.println("카테고리를 다시 입력하세요");
				}
			}
			dao.updateCat(new Forum(num, "", null, null, null, category));
		} else {
			System.out.println("없는 글입니다.");
		}
	}

	@Override
	public void mDelBoard(Scanner sc) {
		System.out.println("==== 글 삭제 ====");
		System.out.print("삭제할 글 번호 : ");
		int num = sc.nextInt();
		if (dao.selectByNum(num) != null) {
			dao.delete(num);
		} else {
			System.out.println("없는 글입니다.");
		}
	}

	@Override
	public void mAddBoard(Scanner sc) {
		boolean flag = true;
		int num = 0;
		String category = "";
		System.out.println("==== 글 작성 ====");

		Forum f = new Forum();
		f.setWriter(PlayerServiceImpl.getLogin_id());

		while (flag) {
			System.out.println("1.자유게시판 | 2.유러피언 룰렛 | 3.레인보우 홀덤 | 4.행맨 | 5.공지사항");
			System.out.print("카테고리 : ");
			sc.nextLine();
			num = sc.nextInt();
			if (num >= 1 && num <= 5) {
				switch (num) {
				case 1:
					category = "자유게시판";
					break;
				case 2:
					category = "유러피언 룰렛";
					break;
				case 3:
					category = "레인보우 홀덤";
					break;
				case 4:
					category = "행맨";
					break;
				case 5:
					category = "공지사항";
					break;
				}
				flag = false;
			} else {
				System.out.println("카테고리를 다시 입력하세요");
			}
		}

		System.out.print("새 제목 : ");
		sc.nextLine();
		String title = sc.nextLine();

		System.out.print("내용(멈추려면 /stop) : ");
		StringBuilder st = new StringBuilder();

		while (true) {
			String str = sc.nextLine();
			if (str.startsWith("/stop")) {
				break;
			}
			st.append(str + "\n");
		}

		String content = st.toString();
		f.setTitle(title);
		f.setContent(content);
		f.setCategory(category);
		dao.insert(f);
	}

}
