package PracticalExercise13;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        while (true) {
            System.out.print("두 정수와 연산자를 입력하시오>>");
            if (!scn.hasNextInt()) {
                break;
            }
            int x = scn.nextInt();

            if (!scn.hasNextInt()) {
                break;
            }
            int y = scn.nextInt();

            if (!scn.hasNext()) {
                break;
            }
            String op = scn.next();

            Calc opObj = null;
            switch (op) {
                case "+":
                    opObj = new Add();
                    break;
                case "-":
                    opObj = new Sub();
                    break;
                case "*":
                case "x":
                case "X":
                    opObj = new Mul();
                    break;
                case "/":
                    opObj = new Div();
                    break;
                default:
                    System.out.println("알 수 없는 연산자입니다: " + op);
                    continue;
            }

            opObj.setValue(x, y);
            int result = opObj.calculate();

            if (opObj.getErrorMsg() != null) {
                System.out.print(opObj.getErrorMsg());
                break; 
            } else {
                System.out.println("계산 결과 " + result);
            }
        }

        scn.close();
        System.out.print("프로그램 종료");
    }
}