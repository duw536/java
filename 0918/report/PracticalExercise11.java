package Basic;

import java.util.Random;
import java.util.Scanner;

public class PracticalExercise11 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int wrong = 0;
		System.out.println("***** 구구단을 맞추는 퀴즈입니다. *****");
		while (wrong < 3) {
			int num1 = (int)(Math.random() * 9 + 1);
            int num2 = (int)(Math.random() * 9 + 1);
            
            System.out.printf(num1+ "x" +num2 + "=");
            int answer = scn.nextInt();
            
            if (answer == num1 * num2) {
            	System.out.println("정답입니다. 잘했습니다");
            } else {
            	wrong++;
            	if (wrong > 2) {
					break;
				}
            	System.out.println(wrong+"번 틀렸습니다. 분발하세요.");
                
            }
		}
		       
		System.out.println(wrong+"번 틀렸습니다. 퀴즈를 종료합니다.");
	
	}
}