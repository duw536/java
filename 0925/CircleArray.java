package Intro;

class CricleEx{
	int radius;
	
	public CricleEx(int radius)	{
		this.radius = radius;
	}
	public double gerArea() {
		return 3.14 * radius * radius;
	}
}

public class CircleArray {
	public static void main(String[] args) {
		CricleEx[] ce = new CricleEx[5];
		
		for(int i=0; i<ce.length; i++)
		ce[i] = new CricleEx(i); 
		
	}
}
