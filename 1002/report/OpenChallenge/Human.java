package OpenChallenge;

import java.util.Scanner;

public class Human extends Player {
	private Scanner scn;
	
	public Human(String name) {
        super(name);
        scn = new Scanner(System.in);
    }
	
	@Override
	public String next() {
        System.out.print(this.name + ">>");
        this.lastBet = scn.next();
        return this.lastBet;
    }
}
