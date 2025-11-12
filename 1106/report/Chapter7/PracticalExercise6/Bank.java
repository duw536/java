package PracticalExercise6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Bank {

    public static void main(String[] args) {
        HashMap<String, Integer> accounts = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("*** 명품 은행에 오신 것을 환영합니다. ***");

        while (true) {
            System.out.print("입금:1, 출금:2, 조회:3, 전체 조회:4, 종료:5>>");
            int choice = sc.nextInt();
            
            String name;
            int amount;

            switch (choice) {
                case 1:
                    System.out.print("계좌명과 액수>>");
                    name = sc.next();
                    amount = sc.nextInt();
                    int newBalance = accounts.getOrDefault(name, 0) + amount;
                    accounts.put(name, newBalance);
                    break;

                case 2:
                    System.out.print("계좌명과 액수>>");
                    name = sc.next();
                    amount = sc.nextInt();
                    int currentBalance = accounts.getOrDefault(name, 0);
                    
                    if (currentBalance < amount) {
                        System.out.println("잔액이 부족하여 출금할 수 없음!!");
                    } else {
                        accounts.put(name, currentBalance - amount);
                    }
                    break;

                case 3:
                    System.out.print("계좌명>>");
                    name = sc.next();
                    System.out.println("(" + name + ": " + accounts.getOrDefault(name, 0) + "원)");
                    break;

                case 4:
                    for (Map.Entry<String, Integer> entry : accounts.entrySet()) {
                        System.out.print("(" + entry.getKey() + ": " + entry.getValue() + "원)");
                    }
                    System.out.println();
                    break;

                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;

                default:
                    System.out.println("잘못된 입력입니다. 1~5 사이의 숫자를 입력하세요.");
            }
        }
    }
}