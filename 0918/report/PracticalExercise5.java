package Basic;

import java.util.Scanner;

public class PracticalExercise5 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] num = new int[10];
		
		System.out.print("양의 정수 10개 입력>>");
		
		for (int i = 0; i < num.length; i++) {
            num[i] = scn.nextInt();
        }
		
		System.out.print("3의 배수는...");
		
		for (int j : num) {
            if (j % 3 == 0) {
                System.out.print(j + " ");
            }
        }
	}
}
