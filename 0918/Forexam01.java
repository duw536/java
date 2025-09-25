package control;

import java.util.Scanner;

class Forexam01{
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);		
		
		int i = 0, sum = 0, number;
		System.out.print("정수 입력 : ");
		number = scn.nextInt();
		for (i=1;i<number ;i++) {
			sum += i;
			if (i == 10) {
				break;
				
			} 
		}
		System.out.println(sum);
	}
}