package Basic;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PracticalExercise18 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] studentID = new int[10];
        int[] scores = new int[10];
        System.out.println("10명 학생의 학번과 점수 입력");
       
        for (int i = 0; i < 10; i++) {
            System.out.print((i + 1) + ">>");
            studentID[i] = scn.nextInt();
            scores[i] = scn.nextInt();
        }
		
        while (true) {
            System.out.print("학번으로 검색: 1, 점수로 검색: 2, 끝내려면 3>>");
            
            try {
                int choice = scn.nextInt();

                if (choice == 1) { 
                    System.out.print("학번>>");
                    int idFind = scn.nextInt();
                    boolean found = false;
                    for (int i = 0; i < studentID.length; i++) {
                        if (studentID[i] == idFind) {
                            System.out.println(scores[i] + "점");
                            found = true;
                            break; 
                        }
                    }
                    if (!found) {
                        System.out.println(idFind + "의 학생은 없습니다.");
                    }
                } else if (choice == 2) {
                    System.out.print("점수>>");
                    int scoreFind = scn.nextInt();
                    String resultID = "";
                    for (int i = 0; i < scores.length; i++) {
                        if (scores[i] == scoreFind) {
                            resultID += studentID[i] + " "; 
                        }
                    }
                    if (resultID.equals("")) {
                        System.out.println("점수가 " + scoreFind + "인 학생은 없습니다.");
                    } else {
                        System.out.println("점수가 " + scoreFind + "인 학생은 " + resultID.trim() + " 입니다.");
                    }
                } else if (choice == 3) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                } else {
                    System.out.println("1, 2, 3 중에서 선택해주세요.");
                }

            } catch (InputMismatchException e) {
                System.out.println("경고!! 점수를 입력하세요.");
                scn.nextLine();
            }
        }
       }
}