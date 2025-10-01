package OpenChallenge;

import java.util.Scanner;

public class Player {
	String name;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
        return name;
    }
	
	public String getWord() {
        Scanner scn = new Scanner(System.in);
        System.out.print(name + "님, 단어를 입력하세요>> ");
        String word = scn.next();
        return word;
    }
}


