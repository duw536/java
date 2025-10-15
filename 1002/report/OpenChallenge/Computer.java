package OpenChallenge;

import java.util.Random;

public class Computer extends Player {
	private Random rnd;
	
	public Computer(String name) {
		super(name);
		rnd = new Random(); 
	}
	
	@Override
    public String next() {
        int index = rnd.nextInt(3);
        this.lastBet = bet[index];
        System.out.println(this.name + ">> 결정하였습니다.");
        return this.lastBet;
    }
}
