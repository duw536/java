package Basic;

import java.util.Random;
import java.util.Scanner;

public class OpenChallenge {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Random r = new Random();
		
		while(true) {
			int k = r.nextInt(100);
			int min = 0;
			int max = 99;
			int count = 1;
			
			System.out.println("수를 결정하였습니다. 맞추어 보세요");
			
			
			while (true) {
				System.out.println(min + "-" + max);
				System.out.print(count + ">>");
				
				int a = scn.nextInt();
				
				if (a > k) {
					System.out.println("더 낮게");
					
					if (a < max) {
						max = a;
					}
					
				}
				else if (a < k) {
					System.out.println("더 높게");
					
					if (a > min) {
						min = a;
					}
				}
				else {
					System.out.println("맞았습니다.");
					break;
				}
				
				count++;
			}
			System.out.print("다시하시겠습니까(y/n)>>");
		    String choice = scn.next();

		    if (choice.equalsIgnoreCase("n")) {
		        break;
		}
		
		}
			
	}
	
}
