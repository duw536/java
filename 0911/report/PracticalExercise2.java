package Basic;

import java.util.Scanner;

class PracticalExercise2 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("생일 입력 하세요>>");
		int birthday = scn.nextInt();
		System.out.println(birthday/10000+"년 "+ ((birthday / 100) % 100)+"월 "+ birthday % 100+"일");
	}
}
