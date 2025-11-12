package PracticalExercise4;

import java.util.HashMap;
import java.util.Scanner;

public class ShoppingCart {
	public static void main(String[] args) {
        HashMap<String, Integer> productMap = new HashMap<>();
        productMap.put("고추장", 3000);
        productMap.put("만두", 500);
        productMap.put("새우깡", 1500);
        productMap.put("콜라", 600);
        productMap.put("참치캔", 2000);
        productMap.put("치약", 1000);
        productMap.put("연어", 2500);
        productMap.put("삼겹살", 2500);

        System.out.println("쇼핑 비용을 계산해드립니다. 구입 가능 물건과 가격은 다음과 같습니다.");
        System.out.println(productMap.toString().replace("=", ", ").replace("{", "[").replace("}", "]"));

        Scanner sc = new Scanner(System.in);
        
        while (true) {
            System.out.print("물건과 개수를 입력하세요>>");
            String line = sc.nextLine();

            if (line.equals("그만")) {
                System.out.println("계산을 종료합니다.");
                break;
            }
            
            String[] tokens = line.split(" ");
            
            if (tokens.length % 2 != 0) {
                System.out.println("입력에 문제가 있습니다!");
                continue;
            }
            
            int totalSum = 0;
            boolean errorFound = false;
            
            for (int i = 0; i < tokens.length; i += 2) {
                String item = tokens[i];
                int quantity = Integer.parseInt(tokens[i + 1]);

                Integer price = productMap.get(item);

                if (price == null) {
                    System.out.println(item + "은(는) 없는 상품입니다!");
                    errorFound = true;
                    break;
                } else {
                    totalSum += price * quantity;
                }
            }
            
            if (!errorFound) {
                System.out.println("전체 비용은 " + totalSum + "원입니다.");
            }
        }   
        sc.close();
	}    
}
