package PracticalExercise12;

import java.util.Scanner;
import java.util.ArrayList;

public class NumberExpectationGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("게임에 참여할 선수 이름들>>");
        String nameLine = sc.nextLine();
        String[] names = nameLine.split(" ");

        ArrayList<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }

        for (Player p : players) {
            System.out.print("[" + p.name + "] 정수 선택(1~10)>>");
            
            p.chosenNumber = Integer.parseInt(sc.nextLine());
        }

        while (players.size() > 1) {
            System.out.print("Enter 키 입력>> ");
            sc.nextLine();

            int[] matchCounts = new int[players.size()];
            int minCount = 16;

            System.out.print("\t");
            for (int i = 0; i < 15; i++) {
                int randNum = (int) (Math.random() * 10 + 1);
                System.out.print(randNum + " ");

                for (int j = 0; j < players.size(); j++) {
                    if (players.get(j).chosenNumber == randNum) {
                        matchCounts[j]++;
                    }
                }
            }
            System.out.println();

            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                System.out.println("[" + p.name + "] 맞춘 개수: " + matchCounts[i]);
                
                if (matchCounts[i] < minCount) {
                    minCount = matchCounts[i];
                }
            }

            ArrayList<Player> nextRoundPlayers = new ArrayList<>();
            System.out.print("현재 패자들: ");
            for (int i = 0; i < players.size(); i++) {
                if (matchCounts[i] == minCount) {
                    nextRoundPlayers.add(players.get(i));
                    System.out.print(players.get(i).name + " ");
                }
            }
            System.out.println();

            players = nextRoundPlayers;
        
        }

        System.out.println("최종 패자는 " + players.get(0).name + "입니다.");
        
        sc.close();
    }
}