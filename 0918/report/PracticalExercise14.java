package Basic;

import java.util.Random;
import java.util.Scanner;

public class PracticalExercise14 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Random rnd = new Random();
		
		System.out.println("***** 겜블링 게임을 시작합니다. *****");
		
		while (true) {
			System.out.print("엔터키 입력>>");
			String enter = scn.nextLine();
			
			int num1 = rnd.nextInt(3); 
            int num2 = rnd.nextInt(3); 
            int num3 = rnd.nextInt(3); 
            
            System.out.println(num1 + "  " + num2 + "  " + num3);
            
            if (num1 == num2 && num2 == num3) {
                System.out.println("성공. 대박났어요!");
                System.out.print("계속하시겠습니까?(yes/no)>>");
                String rePlay = scn.next();
                
                if (rePlay.equals("no")) {
					break;
				}
            }
		}
		System.out.println("게임을 종료합니다.");
	}
}
