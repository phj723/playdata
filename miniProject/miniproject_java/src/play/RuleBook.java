package play;

import java.util.Scanner;

public class RuleBook {
	
	public RuleBook() {}
	
	public void rule(Scanner sc) {
		boolean flag = true;
		while (flag) {
			System.out.println("1.유러피언 룰렛 | 2.레인보우 홀덤 | 3.행맨 | 4.메인 메뉴");
			int m = sc.nextInt();
			switch (m) {
			case 1:
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("유러피언 룰렛 규칙");
				System.out.println("베팅은 먼저 룰렛 테이블 \"안\"에 칩을 두어 베팅하는 Inside Bet과, 룰렛 테이블 \"밖\"에 칩을 두어 베팅하는 Outside Bet으로 나뉘며, 베팅 난이도에 따라 당첨 금액도 달라집니다.");
				System.out.print("\n");
				System.out.println("1. Inside Bet에는 Straight Bet, Split Bet, Street Bet, Corner Bet, Line Bet이 있으며 승리 시 배당은 베팅금액의 각 35배, 17배, 11배, 8배, 5배 입니다.");
				System.out.println("1-1. Straight Bet은 0~36 사이의 수 하나에 (Straight-Up) 베팅을 하는 방법이며, 승리 확률이 낮은 만큼 배당 또한 높습니다.");
				System.out.println("1-2. Split Bet은 룰렛 테이블의 인접한 수 두 곳을 나눠서 (Split) 베팅을 하는 방법입니다.");
				System.out.println("           예를 들어 1과 2, 또는 5와 8 등 룰렛 테이블 상 붙어 있는 숫자 두개에 베팅 하는 것을 뜻합니다.");
				System.out.println("     0의 경우, 0과 1, 0과 2, 0과 3 모두 베팅 가능한 Split Bet 입니다.");
				System.out.println("1-3. Street Bet은 룰렛 테이블의 한 행에 (Street) 연결 된 수 세 곳에 베팅을 하는 방법입니다.");
				System.out.println("           예를 들어 1,2,3 또는 10,11,12 등 가로로 이어진 숫자 세개에 베팅 하는 것을 뜻합니다.");
				System.out.println("     0의 경우, 0,1,2 와 0,2,3 에 베팅 하실 수 있습니다.");
				System.out.println("1-4. Corner Bet은 룰렛 테이블의 모서리가 (Corner) 인접한 수 네 곳에 베팅을 하는 방법입니다.");
				System.out.println("           예를 들어 1,2,4,5 또는 20,21,23,24 등 룰렛 테이블 상 서로 붙어 있는 숫자 네개에 베팅 하는 것을 뜻합니다.");
				System.out.println("     0의 경우, 0,1,2,3 에 베팅 하실 수 있습니다.");
				System.out.println("1-5. Line Bet은 룰렛 테이블의 두 행에 (Line) 베팅을 하는 방법입니다.");
				System.out.println("           예를 들어 1 ~ 6, 또는 19 ~ 24 등 행 두개로 이루어진 숫자 여섯개에 베팅 하는 것을 뜻합니다.");
				System.out.println("     Line Bet의 경우 0에 베팅하실 수 없습니다.");
				System.out.print("\n");
				System.out.println("2. Outside Bet에는 Column Bet, Dozens Bet, Color Bet, Even or Odd Bet, Low or High Bet이 있으며 Outside Bet은 0에 베팅하실 수 없습니다.");
				System.out.println("      승리 시 배당은 베팅금액의 각 2배, 2배, 1배, 1배, 1배 입니다.");
				System.out.println("2-1. Column Bet은 룰렛 테이블의 한 열에 (Column) 베팅을 하는 방법입니다.");
				System.out.println("           예를 들어 1,4,7... 로 이어지는 열이나 3,6,9... 열 등 세로로 이어진 숫자 12개에 베팅 하는 것을 뜻합니다.");
				System.out.println("2-2. Dozens Bet은 룰렛 테이블의 숫자 12개 (Dozen) 마다 베팅을 하는 방법입니다.");
				System.out.println("           예를 들어 1 ~ 12, 13 ~ 24, 25 ~ 36 등 이어진 숫자 12개에 베팅 하는 것을 뜻합니다.");
				System.out.println("2-3. Color Bet은 룰렛 테이블의 한 색에 (Color) 베팅을 하는 방법입니다.");
				System.out.println("           룰렛 테이블의 숫자는 각 색이 Black (B) 또는 Red (R) 로 마킹이 되어 있습니다.");
				System.out.println("           예를 들어 1R, 3R, 5R 등 R 이 적힌 숫자가 Red 색이며, R에 베팅할 경우 빨간색 숫자 18개에 베팅 하는 것을 뜻합니다.");
				System.out.println("2-4. Even or Odd Bet은 룰렛 테이블의 짝수 또는 홀수에 (Even or Odd) 베팅을 하는 방법입니다.");
				System.out.println("           예를 들어 2,4,6... 또는 1,3,5... 등 짝수 또는 홀수 숫자 18개에 베팅하는 것을 뜻합니다.");
				System.out.println("2-5. Low or High Bet은 룰렛 테이블의 낮은 숫자 18개나 높은 숫자 18개에 (Low or High) 베팅을 하는 방법입니다.");
				System.out.println("           예를 들어 1 ~ 18 또는 19 ~ 36에 베팅 하는 것을 뜻합니다.");
				System.out.println("-------------------------------------------------------------------------");
				break;
			case 2:
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("레인보우 홀덤 규칙");
				System.out.println("레인보우 홀덤은 개인카드 3장과 공유카드 4장에서 중복되는 숫자를 제외한 합이 적은 플레이어가 승리하는 게임입니다.");
				System.out.println("카드의 수는 1부터 10까지로 이루어져 있으며 각 카드의 개수는 카드의 숫자와 동일합니다.");
				System.out.println("ex) 1번 카드는 1장, 3번 카드는 3장, 9번 카드는 9장");
				System.out.print("\n");
				System.out.println("1. 플레이어는 3장의 카드를 받습니다.");
				System.out.println("2. 첫 번째 공유카드가 공개되면 플레이어는 오픈할 카드를 선택합니다.");
				System.out.println("3. 개인카드의 오픈이 종료되면 배팅을 시작합니다.");
				System.out.println("4. 위 과정을 3번 반복합니다.");
				System.out.println("5. 공유카드 4장이 모두 공개되면 각 플레이어의 카드 합계를 계산합니다.");
				System.out.println("6. 카드의 합이 적은 플레이어가 승리합니다.");
				System.out.println("7. 만약 합이 같을 경우 플레이어가 패배합니다.");
				System.out.print("\n");
				System.out.println("***레인보우***");
				System.out.println("만약 개인카드와 공유카드 7장이 중복없이 모두 다르면 레인보우가 되며 무조건 승리합니다.");
				System.out.println("만약 두 플레이어가 모두 레인보우일 경우 합이 적은 플레이어가 승리하며 합이 같을 경우 플레이어가 패배합니다.");
				System.out.print("\n");
				System.out.println("배팅 용어");
				System.out.println("1. Call : 상대가 배팅한 금액과 같은 금액을 배팅합니다.");
				System.out.println("2. Raise : 상대가 배팅한 금액보다 높은 금액을 배팅합니다.");
				System.out.println("-------------------------------------------------------------------------");
				break;
			case 3:
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("행맨 규칙");
				System.out.println("영어 단어를 맞혀서 행맨을 구하는 게임입니다.");
				System.out.println("영어 알파벳을 한 글자씩 입력하면서 틀릴 때마다 행맨의 몸이 잘려나갑니다.");
				System.out.println("행맨이 죽기 전에 단어를 맞히면 승리하고 행맨이 죽으면 패배하게 됩니다.");
				System.out.print("\n");
				System.out.println("1. 카테고리를 선택하기");
				System.out.println("선택한 카테고리에 해당하는 영어 단어가 랜덤으로 제시됩니다.");
				System.out.println("제시된 단어의 길이만큼 빈칸으로 표시됩니다.");
				System.out.println("2. 알파벳을 소문자로 한 글자씩 입력하기");
				System.out.println("맞는 답을 입력하면 빈칸에 표시됩니다.");
				System.out.println("틀린 답을 입력하면 '오답 리스트'에 추가되며 기회가 한번 차감됩니다.");
				System.out.println("틀릴 수 있는 기회는 총 7번 주어집니다.");
				System.out.println("3. 승리");
				System.out.println("7번 넘게 틀리지 않고 정답을 맞히면 행맨을 구하고 승리하게 됩니다.");
				System.out.println("승리 시 20포인트를 얻게 됩니다.");
				System.out.println("4. 패배");
				System.out.println("7번 넘게 틀리면 행맨은 죽고 패배하게 됩니다.");
				System.out.println("패배 시 포인트는 차감되지 않습니다.");
				System.out.println("5. 힌트");
				System.out.println("현재 빈칸인 곳의 답을 하나 알려줍니다.");
				System.out.println("힌트 사용 시 15포인트가 차감됩니다.");
				System.out.println("6. 되돌리기");
				System.out.println("가장 최근에 입력한 오답을 삭제하고 기회가 한번 늘어납니다.");
				System.out.println("되돌리기 사용 시 15포인트가 차감됩니다.");
				System.out.println("-------------------------------------------------------------------------");
				break;
			case 4:
				flag = false;
				break;
			}
		}
	}
}
