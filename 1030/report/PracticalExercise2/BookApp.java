package PracticalExercise2;

public class BookApp {
	public static void main(String[] args) {
		Book a = new Book("황기태", "명품자바", "김하진");
		Book b = new Book("황기태", "명품자바", "김하진");
		System.out.println(a);
		System.out.println(b);
		if (a.equals(b)) {
			System.out.println("같은 책");
		}
		else {
			System.out.println("다른 책");
		}
	}
}
