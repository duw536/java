package control;

import java.util.Scanner;

public class Pyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("마름모의 줄 수를 입력하세요 (홀수만 입력): ");
        int n = scanner.nextInt();

        // 홀수만 허용
        if (n % 2 == 0) {
            System.out.println("홀수만 입력 가능합니다.");
            return;
        }

        int mid = n / 2;

        // 윗부분 (중앙 포함)
        for (int i = 0; i <= mid; i++) {
            for (int j = 0; j < mid - i; j++) {
                System.out.print(" ");
            }

            for (int k = 0; k < 2 * i + 1; k++) {
                System.out.print("*");
            }

            System.out.println();
        }

        // 아랫부분
        for (int i = mid - 1; i >= 0; i--) {
            for (int j = 0; j < mid - i; j++) {
                System.out.print(" ");
            }

            for (int k = 0; k < 2 * i + 1; k++) {
                System.out.print("*");
            }

            System.out.println();
        }

        scanner.close();
    }
}
