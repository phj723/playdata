package hangman;

import java.util.ArrayList;
import java.util.Scanner;

public class WordServiceImpl implements WordService {
	private WordDao wordDao;

	public WordServiceImpl() {
		wordDao = new WordDaoImpl();
	}

	@Override
	public void inputWord(Scanner sc) {
		System.out.println("카테고리를 입력하세요.");
		String cate = sc.next();
		System.out.println("단어를 입력하세요. 여러 개 입력 시 띄어쓰기없이 콤마(,)로 연결해주세요.");
		String words = sc.next();
		String[] wArr = words.split(",");
		int cntN = 0;
		int cntY = 0;
		for (String w : wArr) {
			ArrayList<String> allWord = wordDao.selectAllWord();
			if(allWord.contains(w)){
				cntN++;
				System.out.print(w+" ");
			}else {
				Word word = new Word(cate, w);
				wordDao.insert(word);
				cntY++;
			}
		}
		if(cntN==0) {
			System.out.println(cntY+"개의 단어가 추가되었습니다.");
		}else if(cntN>0 && cntY==0){
			System.out.println("은(는) 중복입니다.");
		}else {
			System.out.println("\n중복인 위의 단어 "+cntN+"개를 제외하고 "+cntY+"개의 단어가 추가되었습니다.");
		}
		
	}

	@Override
	public void editWord(Scanner sc) {
		boolean flagOld = true;
		while (flagOld) {
			System.out.println("수정할 기존의 단어를 입력하세요.");
			String oldWord = sc.next();
			ArrayList<String> allWord = wordDao.selectAllWord();
			if (allWord.contains(oldWord)) {
				boolean flagNew = true;
				while (flagNew) {
					System.out.println("수정할 새로운 단어를 입력하세요.");
					String newWord = sc.next();
					if (allWord.contains(newWord)) {
						System.out.println("이미 리스트에 존재하는 단어입니다.");
					} else {
						wordDao.update(oldWord, newWord);
						System.out.println("단어가 수정되었습니다.");
						return;
					}
				}
			} else {
				System.out.println("리스트에 존재하지 않는 단어입니다.");
				boolean flag = true;
				while (flag) {
					System.out.println("1.다시 입력하기 2.메뉴로 돌아가기");
					String s = sc.next();
					switch (s) {
					case "1":
						flag = false;
						break;
					case "2":
						return;
					default:
						System.out.println("다시 입력해주세요.");
					}
				}
			}
		}
	}

	@Override
	public void deleteWord(Scanner sc) {
		boolean flag = true;
		while (flag) {
			System.out.println("삭제할 단어를 입력하세요. 여러 개 입력 시 띄어쓰기없이 콤마(,)로 연결해주세요.");
			String words = sc.next();
			String[] wArr = words.split(",");
			int cntN = 0;
			int cntY = 0;
			for (String w : wArr) {
				ArrayList<String> allWord = wordDao.selectAllWord();
				if (allWord.contains(w)) {
					wordDao.deleteWord(w);
					cntY++;
				} else {
					System.out.print(w + " ");
					cntN++;
				}
			}
			if (cntN == 0) {
				System.out.println(cntY + "개의 단어가 삭제되었습니다.");
				flag = false;
				break;
			} else if (cntN > 0 && cntY == 0) {
				System.out.println("은(는) 존재하지 않는 단어입니다.");
				boolean flag2 = true;
				while (flag2) {
					System.out.println("1.다시 입력하기 2.메뉴로 돌아가기");
					String s = sc.next();
					switch (s) {
					case "1":
						flag2 = false;
						break;
					case "2":
						return;
					default:
						System.out.println("다시 입력해주세요.");
					}
				}
			} else {
				System.out.println("\n리스트에 없는 위의 단어 " + cntN + "개를 제외하고 " + cntY + "개의 단어가 삭제되었습니다.");
				flag = false;
				break;
			}
		}
	}

	@Override
	public void deleteCate(Scanner sc) {
		System.out.println("삭제할 카테고리를 입력하세요.");
		boolean flag = true;
		while (flag) {
			ArrayList<String> allCate = wordDao.selectAllCate();
			for (int i = 0; i < allCate.size(); i++) {
				System.out.print((i + 1) + "." + allCate.get(i) + " ");
			}
			System.out.println((allCate.size() + 1) + ".취소");
			int cateNum = sc.nextInt();
			if (cateNum < 1 || cateNum > allCate.size()) {
				if (cateNum == (allCate.size() + 1)) {
					flag = false;
					break;
				}
				System.out.println("번호를 다시 입력해주세요");
			} else {
				String c = allCate.get(cateNum - 1);
				wordDao.deleteCate(c);
				System.out.println("카테고리가 삭제되었습니다.");
				flag = false;
			}
		}
	}

	@Override
	public void getAllWordByCate(Scanner sc) {
		System.out.println("단어 목록을 검색할 카테고리를 선택하세요.");
		boolean flag = true;
		while (flag) {
			ArrayList<String> allCate = wordDao.selectAllCate();
			for (int i = 0; i < allCate.size(); i++) {
				if (i != allCate.size() - 1) {
					System.out.print((i + 1) + "." + allCate.get(i) + " ");
				} else {
					System.out.print((i + 1) + "." + allCate.get(i) + "\n");
				}
			}
			int cateNum = sc.nextInt();
			if (cateNum < 1 || cateNum > allCate.size()) {
				System.out.println("번호를 다시 입력해주세요");
			} else {
				String c = allCate.get(cateNum - 1);
				System.out.println(wordDao.selectWordByCate(c));
				flag = false;
			}
		}
	}

	@Override
	public int getConutWordByCate(String category) {
		int wordCnt = 0;
		ArrayList<String> wordList = wordDao.selectWordByCate(category);
		wordCnt = wordList.size();
		return wordCnt;
	}

}
