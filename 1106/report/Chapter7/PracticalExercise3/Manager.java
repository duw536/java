package PracticalExercise3;

import java.util.HashMap;
import java.util.Scanner;

public class Manager {
	public static void main(String[] args) {
		HashMap<String, Integer> stockMap = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("주식 종목과 주가를 입력하세요(예: 삼송전자 75000");
		
		while (true) {
            System.out.print("종목, 주가>>");
            String name = sc.next();

            if (name.equals("그만")) {
                break; 
            }

            int price = sc.nextInt();
            stockMap.put(name, price);
        }
		
		System.out.println("주가를 검색합니다.");
		
		while (true) {
            System.out.print("종목>>");
            String searchName = sc.next();

            if (searchName.equals("그만")) {
                break;
            }
            
            Integer price = stockMap.get(searchName); 
            
            if (price != null) {
                System.out.println(searchName + "의 주가는 " + price + "원");
            } else {
                System.out.println(searchName + "은(는) 없는 종목입니다.");
            }
		}   
		sc.close();
	}
}
