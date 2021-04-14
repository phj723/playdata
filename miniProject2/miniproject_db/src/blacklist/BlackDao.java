package blacklist;

import java.util.ArrayList;

public interface BlackDao {
	void insert(BlackList b);
	ArrayList<BlackList> selectAll();
	BlackList select(String id);
	void delete(BlackList b);
}
