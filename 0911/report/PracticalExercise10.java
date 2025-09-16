package Basic;

import java.util.Scanner;

public class PracticalExercise10 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("(x1, y1), (x2, y2)의 좌표 입력>>");
		int x1 = scn.nextInt();
		int y1 = scn.nextInt();
		int x2 = scn.nextInt();
		int y2 = scn.nextInt();
		
		if (x1>=10 && x1<=200 && y1>=10 && y1<=300 && x2>=10 && x2<=200 && y2>=10 && y2<=300) {
			System.out.println("("+x1+", "+y1+") ("+x2+", "+y2+") 사각형은 (10, 10) (200, 300) 사각형에 포함된다.");
		}
		else {
			System.out.println("("+x1+", "+y1+") ("+x2+", "+y2+") 사각형은 (10, 10) (200, 300) 사각형에 포함되지 않는다.");
		}
	}
}
