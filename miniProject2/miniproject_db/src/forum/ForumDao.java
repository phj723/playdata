package forum;

import java.util.ArrayList;

public interface ForumDao {
	void insert(Forum f);
	ArrayList<Forum> selectAll();
	Forum selectByNum(int num);
	ArrayList<Forum> selectByWriter(String writer);
	ArrayList<Forum> selectByTitle(String title);
	ArrayList<Forum> selectByCategory(String category);
	void update(Forum f); //번호로 찾아서 날짜, 제목, 내용  수정
	void updateCat(Forum f); // 카테고리 수정
	void delete(int num);
}
