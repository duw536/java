package Basic;

import java.util.Scanner;

class PracticalExercise7 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("월을 입력하세요(1~12)>>");
		int month = scn.nextInt();
		
		if (month > 0 && month < 13) {
			switch (month) {
			case 3, 4, 5:
				System.out.println("따뜻한 봄");
				break;
			case 6, 7, 8:
				System.out.println("바다가 즐거운 여름");
				break;
			case 9, 10, 11:
			System.out.println("낙엽이 지는 아름다운 가을");
				break;
			case 1, 2, 12:
			System.out.println("눈 내리는 하얀 겨울");
				break;
		
			
			default:
				break;
			}
		}
		else {
			System.out.println("1~12만 입력하세요.");
		}
	}
}
