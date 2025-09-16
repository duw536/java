package Basic;

import java.util.Scanner;

class PracticalExercise5 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int total = 100, late = 3, absence = 8;
		System.out.print("학생1>>");
		String student1 = scn.next();
		int student1Late = scn.nextInt();
		int student1Absence = scn.nextInt();
		System.out.print("학생2>>");
		String student2 = scn.next();
		int student2Late = scn.nextInt();
		int student2Absence = scn.nextInt();
		System.out.println(student1+"의 감점은 "+((late*student1Late)+(absence*student1Absence))+","+student2+"의 감점은 "+((late*student2Late)+(absence*student2Absence)));
		
		int student1Score = total-((late*student1Late)+(absence*student1Absence)), student2Score = total-((late*student2Late)+(absence*student2Absence));
		if (student1Score == student2Score) {
			System.out.println("점수 동일");
		}
		else if (student1Score > student2Score) {
			System.out.println(student1+"의 출석 점수가 더 높음. "+student1+" 출석 점수는 "+student1Score);
		}
		else {
			System.out.println(student2+"의 출석 점수가 더 높음. "+student2+" 출석 점수는 "+student2Score);
		}
	}
}
