package Basic;

import java.util.Random;
import java.util.Scanner;

public class PracticalExercise8 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Random rnd = new Random();
		
		System.out.print("정수 몇 개를 저장하시겠습니까>>");
        int size = scn.nextInt();
        int[] num = new int[size];
        int sum = 0;
		
        for (int i = 0; i < num.length; i++) {
            num[i] = rnd.nextInt(100) + 1; 
            sum += num[i];
        }
        
        System.out.print("랜덤한 정수들...");
        for (int n : num) {
            System.out.print(n + " ");
        }
        System.out.println();
        
        double avg = (double)sum / num.length;
        System.out.println("평균은 " + avg);
        
	}
}
