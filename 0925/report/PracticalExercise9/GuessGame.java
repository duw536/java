package PracticalExercise9;

import java.util.Scanner;
import java.util.Random;

public class GuessGame {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("*** 예측 게임을 시작합니다. ***");
        System.out.print("게임에 참여할 선수 수>>");
        int numPlayers = scanner.nextInt();
        Player[] players = new Player[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            System.out.print("선수 이름>>");
            String name = scanner.next();
            players[i] = new Player(name);
        }

        while (true) {
            System.out.println("\n1~100 사이의 숫자가 결정되었습니다. 선수들은 맞추어 보세요.");
            int hiddenAnswer = (int)(Math.random() * 100 + 1);

            int[] guesses = new int[numPlayers];
            for (int i = 0; i < numPlayers; i++) {
                System.out.print(players[i].getName() + ">>");
                guesses[i] = scanner.nextInt();
            }

            int minDiff = 101;
            int winnerIndex = -1;

            for (int i = 0; i < numPlayers; i++) {
                int guessNumber = guesses[i]; 
                int diff = Math.abs(hiddenAnswer - guessNumber);
                
                if (diff < minDiff) {
                    minDiff = diff;
                    winnerIndex = i;
                }
            }

            System.out.println("정답은 " + hiddenAnswer + ". " + players[winnerIndex].getName() + "이 이겼습니다. 승점 1점 확보!");
            players[winnerIndex].addScore();

            System.out.print("계속하려면 yes 입력>> ");
            String continueChoice = scanner.next();
            if (!continueChoice.equalsIgnoreCase("yes")) {
                break;
            }
        }

        int maxScore = -1;
        String finalWinner = "";
        for (int i = 0; i < numPlayers; i++) {
            System.out.print(players[i].getName() + ": " + players[i].getScore());
            if (players[i].getScore() > maxScore) {
                maxScore = players[i].getScore();
                finalWinner = players[i].getName();
            }
        }
        System.out.println();
        System.out.println(finalWinner + "이 최종 승리하였습니다.");

        scanner.close();
    }
}