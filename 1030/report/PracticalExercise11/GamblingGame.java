package PracticalExercise11;

import java.util.Scanner;

public class GamblingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("겜블링 게임에 참여할 선수 숫자>>");
        int numPlayers = sc.nextInt();

        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print((i + 1) + "번째 선수 이름>>");
            String name = sc.next();
            players[i] = new Player(name);
        }

        sc.nextLine();

        boolean gameFinished = false; 

        while (!gameFinished) {

            for (int i = 0; i < numPlayers; i++) {
                System.out.print("[" + players[i].name + "]:<Enter>");
                sc.nextLine();

   
                int n1 = (int) (Math.random() * 3 + 1);
                int n2 = (int) (Math.random() * 3 + 1);
                int n3 = (int) (Math.random() * 3 + 1);

                System.out.printf("\t%d %d %d ", n1, n2, n3);

                if (n1 == n2 && n2 == n3) {
                    System.out.println(players[i].name + "님이 이겼습니다!");
                    gameFinished = true;
                    break;
                } else {
                    System.out.println("아쉽군요!");
                }
            }
        }

        System.out.println("게임을 종료합니다.");
        sc.close();
    }
}