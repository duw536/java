package PracticalExercise1;

public class StudentApp {
	public static void main(String[] args) {
		Student a = new Student("황기태", 23);
		Student b = new Student("항기태", 77);
		System.out.println(a);
		if (a.equals(b)) {
			System.out.println("같은 학생입니다.");
		}
		else {
			System.out.println("다른 학생입니다.");
		}
		
	}
}
