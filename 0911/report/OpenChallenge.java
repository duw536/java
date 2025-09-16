package Basic;

import java.util.Scanner;

class OpenChallenge {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("가위바위보 게임입니다. 가위, 바위, 보 중에서 입력하세요");
		System.out.print("철수 >>");
		String Player1 = scn.next();
		System.out.print("영희 >>");
		String Player2 = scn.next();
		
		if (P1.equals(P2)) {
			System.out.println("비겼습니다.");
		}
		else if ((P1.equals("가위") && P2.equals("보")) || (P1.equals("바위") && P2.equals("가위")) || (P1.equals("보") && P2.equals("바위"))){
			System.out.println("철수가 이겼습니다.");
		}
		else {
			System.out.println("영희가 이겼습니다.");
		}
		
		
	}
	
}
