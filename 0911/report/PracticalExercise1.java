package Basic;

import java.util.Scanner;

class PracticalExercise1 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("$1=1200원입니다. 달러를 입력하세요>>");
		int dollar = scn.nextInt();
		System.out.println("$"+dollar+"는 "+dollar*1200+"원입니다.");
		
	}
}
