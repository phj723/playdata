package forum;

import java.util.ArrayList;
import java.util.Scanner;

public interface ForumService {
	void addBoard(Scanner sc); //글 작성
	void printAll(ArrayList<Forum> list); 
	ArrayList<Forum> getAll(); //글 목록(번호, 제목, 작성자)
	void getByNum(Scanner sc); //글 번호 입력받아 검색
	ArrayList<Forum> getByWriter(Scanner sc); //작성자 입력받아 작성자로 검색
	ArrayList<Forum> getByWriter(String writer);
	ArrayList<Forum> getByTitle(Scanner sc); //제목 일부 입력받아 제목으로 검색
	ArrayList<Forum> getByCategory(Scanner sc); //카테고리 입력받아 제목으로 검색
	void editBoard(Scanner sc); //번호로 찾아서 새제목, 새내용 입력받아 수정
	void editCatBoard(Scanner sc); //카테고리 수정
	void delBoard(Scanner sc); //글 번호 입력받아 삭제
	
	//manager용 method
	void mAddBoard(Scanner sc); //글 작성
	void mEditBoard(Scanner sc); //번호로 찾아서 새카테고리 입력받아 수정
	void mDelBoard(Scanner sc); //글 번호 입력받아 삭제
}
