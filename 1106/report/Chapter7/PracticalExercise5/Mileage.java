package PracticalExercise5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mileage {

    public static void main(String[] args) {
        HashMap<String, Integer> mileageMap = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("*** 마일리지 관리 프로그램입니다. ***");

        while (true) {
            System.out.print("이름과 마일리지>>");
            String name = sc.next();

            if (name.equals("그만")) {
                break;
            }

            int mileage = sc.nextInt();

            int newTotalMileage = mileageMap.getOrDefault(name, 0) + mileage;
            mileageMap.put(name, newTotalMileage);
        }

        System.out.println("---------------------------");

        String allCustomersOutput = "";
        int maxMileage = -1;
        String topCustomer = "";

        for (Map.Entry<String, Integer> entry : mileageMap.entrySet()) {
            String name = entry.getKey();
            int mileage = entry.getValue();

            allCustomersOutput += "(" + name + ": " + mileage + ") ";

            if (mileage > maxMileage) {
                maxMileage = mileage;
                topCustomer = name;
            }
        }

        System.out.println(allCustomersOutput.trim());
        System.out.println("가장 마일리지가 높은 고객은 " + topCustomer + "입니다.");

        System.out.println("프로그램을 종료합니다.");
        sc.close();
    }
}