package PracticalExercise3;

import java.util.Scanner;

public class Main3 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("이름, 자바, 웹프로그래밍, 운영체제 순으로 점수 입력>>");
		String name = scn.next();
		int java = scn.nextInt();
		int web = scn.nextInt();
		int os = scn.nextInt();
		
		Grade st = new Grade(name, java, web, os);
		System.out.println(st.getName() + "의 평균은 " + st.getAverage());
		scn.close();
	}
}


