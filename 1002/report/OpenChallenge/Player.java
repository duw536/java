package OpenChallenge;

abstract public class Player {
	protected String bet[] = {"묵", "찌", "빠"};
	protected String name;
	protected String lastBet = null;
	
	protected Player(String name) { this.name = name; }
	public String getName() { return name; }
	public String getBet() { return lastBet; }
	abstract public String next(); 
	
}
