package Basic;

import java.util.Scanner;

class PracticalExercise3 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("**** 자바 분식입니다. 주문하면 금액을 알려드립니다. ****");
		System.out.print("떡볶이 몇 인분>>");
		int tteokbokki = scn.nextInt();
		System.out.print("김말이 몇 인분>>");
		int rolledSeaweed = scn.nextInt();
		System.out.print("쫄면 몇 인분>>");
		int jjolmyeon = scn.nextInt();
		
		System.out.println("전체 금액은 "+((tteokbokki*2000)+(rolledSeaweed*1000)+(jjolmyeon*3000))+"원입니다.");
		
		
	}
}
