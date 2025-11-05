package PracticalExercise5;

import java.util.Scanner;

public class GradeAvgrage {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("여러 과목의 학점을 빈 칸으로 분리 입력>>");
		String line = sc.nextLine();
		
		String[] grades = line.split(" ");
		double sum = 0;
		
		for (String g : grades) {
			switch (g.toUpperCase()) {
			case "A": sum += 100; 
				break;
			case "B": sum += 90; 
				break;
			case "C": sum += 80; 
				break;
			case "D": sum += 70; 
				break;
			case "F": sum += 0; 
				break;

			default:
				System.out.println("입력 오류: " + g);
				sc.close();
				return;
			}
		}
		double avg = sum / grades.length;
		System.out.println("평균은 " + avg);
		sc.close();
	}
}
