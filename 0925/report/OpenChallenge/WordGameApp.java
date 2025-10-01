package OpenChallenge;

import java.util.Scanner;

public class WordGameApp {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("끝말잇기 게임을 시작합니다...");
        System.out.print("참가자 인원은 몇명입니까>>");
        int Players = scn.nextInt();
        Player[] players = new Player[Players];
        
        for (int i = 0; i < Players; i++) {
            System.out.print("참가자의 이름을 입력하세요>> ");
            String name = scn.next();
            players[i] = new Player(name); 
        }
        
        System.out.println("시작하는 단어는 아버지입니다");
        String firstWord = "아버지";
        int turn = 0;
        
        while (true) {
        	Player nowPlayer = players[turn % Players];
        	System.out.print(nowPlayer.getName() + "님, 단어를 입력하세요>>");
            String newWord = scn.next();

            char lastChar = firstWord.charAt(firstWord.length() - 1);
            char firstChar = newWord.charAt(0);
            
            if (lastChar == firstChar) {
                firstWord = newWord;
                turn++;
            } 
            else {
                System.out.println(nowPlayer.getName() + "님이 졌습니다.");
                break; 
            }
        }
	}
}


