package forumRep;

import java.util.ArrayList;
import java.util.Scanner;

public interface RepService {
	void addRep(Scanner sc);
	void printAll(ArrayList<Rep> list);
	void editRep(Scanner sc);
	void delRep(Scanner sc);
}
