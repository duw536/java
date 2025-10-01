package PracticalExercise12;

import java.util.Scanner;

public class DicApp {
	public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("한영 단어 검색 프로그램입니다.");

        while (true) {
            System.out.print("한글 단어?");
            String korWord = scn.next();

            if (korWord.equals("그만")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            String engWord = Dictionary.kor2Eng(korWord);

            if (engWord == null) {
                System.out.println(korWord + "는 저의 사전에 없습니다.");
            } else {
                System.out.println(korWord + "은 " + engWord);
            }
        }

        scn.close();
    }
}
