package control;

public class MissionExam01 {
	public static void main(String[] args) {
		int h = 5;
		for (int i =1; i <= h; i++) {
			
			for (int j = 1; j <= i; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		for (int i = 1; i <= h; i++) {
			
			for (int j = 1; j <= h - i; j++) {
				System.out.print(" ");
			}
			for (int n = 1; n <= i; n++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		h = 4;
		
		for (int i = 0; i <= h; i++) {
			
			for (int j = 0; j < h - i; j++) {
				System.out.print(" ");
			}
			for (int n = 0; n < 2 * i + 1; n++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}

		