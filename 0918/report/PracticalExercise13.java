package Basic;

import java.util.Scanner;

public class PracticalExercise13 {
	public static void main(String[] args) {
		String course [] = {"C", "C++", "Python", "Java", "HTML5"};
		String grade [] = {"A", "B+", "B", "A+", "D"};

			
		Scanner scn = new Scanner(System.in);
		
		while(true) {
            System.out.print("과목 이름>>");
            String iCourse = scn.next();
            
            
            
            for (int i = 0; i < course.length; i++) {
                if (iCourse.equals(course[i])) {
                    System.out.println(course[i] + "의 학점은 " + grade[i] + "입니다.");
                }
            }
            
            if (iCourse.equals("그만")) {
                break;
            }
            
            else if (iCourse.equals("C") || iCourse.equals("C++") || iCourse.equals("Python") || iCourse.equals("Java") || iCourse.equals("HTML5")) {
				
			}
            
            else {
				System.out.println(iCourse+"는 없는 과목입니다.");
			}
           
        }
		
	}
}
