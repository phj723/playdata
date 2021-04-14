package forumRep;

import java.util.ArrayList;

public interface RepDao {
	void insert(Rep r);
	ArrayList<Rep> selectByArtNum(int art_num);
	ArrayList<Rep> selectByWriter(String writer);
	void update(Rep r);
	void delete(int rep_num);
}
