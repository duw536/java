package PracticalExercise6;

import java.util.Scanner;

public class Rotate {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("문자열을 입력하세요. 빈 칸이 있어도 되고 영어 한글 모두 됩니다.");
		String str = sc.nextLine();
		
		int le = str.length();
		
		for (int i = 0; i < le; i++) {
			char first = str.charAt(0);
			str = str.substring(1) + first;
			System.out.println(str);
		}
		sc.close();
	}
}
