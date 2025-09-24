package Basic;

import java.util.Scanner;

public class PracticalExercise3 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int num;
		
		while (true) {
			System.out.print("양의 정수 입력>>");
			num = scn.nextInt();
			if (num > 0) {
				break;
			}
			
	}
		for (int height = 1; height <= num; height++) {
			
			for (int width = 1; width <= num - height +1; width++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
	}
}
