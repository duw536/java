package PracticalExercise6;

public class Main6 {
	public static void main(String[] args) {
		Rectangle a = new Rectangle(3, 3, 6, 6); 
        Rectangle b = new Rectangle(4, 4, 2, 3);

        a.show();
        if (a.isSquare()) System.out.println("a는 정사각형입니다.");
        else System.out.println("a는 직사각형입니다.");

        if (a.contains(b)) System.out.println("a는 b를 포함합니다.");
	}
}


