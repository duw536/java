package Basic;

import java.util.Scanner;

public class PracticalExercise6 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] num = new int[10];
		
		System.out.print("양의 정수 10개 입력>>");
		
		for (int i = 0; i < num.length; i++) {
            num[i] = scn.nextInt();
        }
		
		System.out.print("자리수의 합이 9인 것은 ...");
		
		for (int onum : num) {
            int i = onum; 
            int sum = 0;
            
            while (i > 0) {
                sum += i % 10; 
                i /= 10;              
            }
            
            if (sum == 9) {
                System.out.print(onum + " ");
            }
		}
		
	}
}
