package Basic;

import java.util.Scanner;

class PracticalExercise8 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("연산 입력>>");
		double num1 = scn.nextDouble();
		String elementaryArithmetic = scn.next();
		double num2 = scn.nextDouble();
		double total = 0;
		
		
		if ((elementaryArithmetic.equals("더하기") || (elementaryArithmetic.equals("곱하기")) || (elementaryArithmetic.equals("빼기")) || (elementaryArithmetic.equals("나누기")))) {
			switch (elementaryArithmetic) {
			case "더하기":
				total = num1+num2;
				break;
				
			case "빼기":
				total = num1-num2;
				break;
				
			case "곱하기":
				total = num1*num2;
				break;
				
			case "나누기":
				total = num1/num2;
				break;

			default:
				break;
				
				
			}
			System.out.println(num1+" 곱하기 "+num2+"의 계산 결과는 "+total);
		}
		else {
			System.out.println("사칙연산이 아닙니다.");
		}
	}
}
