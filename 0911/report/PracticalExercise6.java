package Basic;

import java.util.Scanner;

class PracticalExercise6 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("나이를 입력하세요>>");
		int age = scn.nextInt();
		int redCandle = age / 10, blueCandle = (age-(redCandle*10)) / 5, yellowCandle = (age-(redCandle*10)-(blueCandle*5)) % 10;
		
		if (age > 0) {
			System.out.println("빨간 초 "+redCandle+"개, 파란 초 "+blueCandle+"개, 노란 초"+yellowCandle+"개. 총 "+(redCandle+blueCandle+yellowCandle)+"개가 필요합니다.");
		}
		else {
			System.out.println("나이는 양수로만 입력하세요.");
		}
	}
}
