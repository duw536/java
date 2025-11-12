package PracticalExercise1;

import java.util.Scanner;
import java.util.Vector;

public class MinVector {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Vector<Integer> v = new Vector<Integer>();
		
		System.out.print("정수 입력(-1이면 끝)>>");
		
		while (true) {
			int num = sc.nextInt();
			
			if (num == -1) {
				break;
			}
			if(num > 0) {
				v.add(num);
			}
		}
		
		int min = v.get(0);
		for (int i = 1; i < v.size(); i++) {
			if (v.get(i) < min) {
				min = v.get(i);
			}
		}
		System.out.println("가장 작은 수는 " + min);
	}
}
