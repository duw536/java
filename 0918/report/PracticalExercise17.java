package Basic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PracticalExercise17 {
	public static void main(String[] args) {
        
        String[] coffee = {"핫아메리카노", "아이스아메리카노", "카푸치노", "라떼"};
        int[] price = {3000, 3500, 4000, 5000};
         Scanner scn = new Scanner(System.in);
         System.out.print(coffee[0]);
      
         for (int i = 1; i < coffee.length; i++) {
            System.out.print(", " + coffee[i]);
        }
        System.out.println(" 있습니다.");
       
        while (true) {
            System.out.print("주문>>");
            String coffeeMenu = scn.next();

            if (coffeeMenu.equals("그만")) {
                break;
            }

            try {
                int quantity = scn.nextInt();
                boolean coffeeFound = false; 

                for (int i = 0; i < coffee.length; i++) {
                    if (coffeeMenu.equals(coffee[i])) {
                        int totalPrice = price[i] * quantity;
                        System.out.println("가격은 " + totalPrice + "원입니다.");
                        coffeeFound = true;
                        break; 
                    }
                }

                if (!coffeeFound) {
                    System.out.println(coffeeMenu + "은 없는 메뉴입니다.");
                }

            } catch (InputMismatchException e) {
                System.out.println("잔 수는 양의 정수로 입력해주세요!");
                scn.nextLine(); 
            }
        }
        
    }
}