package PracticalExercise3_7;
//PracticalExercise4
public class ColorPoint2 extends Point {
	
	private String color;
	
	public ColorPoint2(int x, int y, String color) {
		super(x, y);
		this.color = color;
	}

	public ColorPoint2() {
		this(0, 0, "WHITE");
	}
	
	public ColorPoint2(int x, int y) {
        super(x, y);
        this.color = "BLACK";
    }
	
	public void set(String color) {
		this.color = color;
	}
	
	public void set(int x, int y) {
		move(x, y);
    }
	
	public double getDistance(ColorPoint2 p) {
        long x1 = this.getX();
        long y1 = this.getY();
        
        long x2 = p.getX();
        long y2 = p.getY();

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
	
	@Override
    public String toString() {
        return color + "색의 (" + getX() + "," + getY() + ")의 점";
    }
		
	public static void main(String[] args) {
		ColorPoint2 zeroPoint = new ColorPoint2(); 
		System.out.println(zeroPoint.toString() + "입니다");
		
		ColorPoint2 cp = new ColorPoint2(10, 10, "RED");
		
		cp.set("BLUE");
		cp.set(10, 20);
		System.out.println(cp.toString() + "입니다");
		ColorPoint2 thresholdPoint = new ColorPoint2(100, 100);
		
		System.out.println("cp에서 임계점까지의 거리는 " + cp.getDistance(thresholdPoint));
	}
}
