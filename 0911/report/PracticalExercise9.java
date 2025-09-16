package Basic;

import java.util.Scanner;

class PracticalExercise9 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("점 (x, y)의 좌표 입력>>");
		int x = scn.nextInt();
		int y = scn.nextInt();
		
		if (x>10 && x<200 && y>10 && y<300) {
			System.out.println("("+x+", "+y+")는 사각형 안에 있습니다.");
		}
		else if (x == 10 && x == 200 || y == 300 || y == 10  && x>10 && x<200 || y< 300 && y>10) {
			System.out.println("("+x+", "+y+")는 사각형 선 상에 있습니다.");
		}
		else {
			System.out.println("("+x+", "+y+")는 사각형 밖에 있습니다.");
		}
	}
}
