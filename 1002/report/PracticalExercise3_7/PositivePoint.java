package PracticalExercise3_7;
//PracticalExercise6
public class PositivePoint extends Point {
	
	public PositivePoint() {
		super(1, 1);
	}
	
	public PositivePoint(int x, int y) {
		super(x, y);
		if(getX() <= 0 || getY() <= 0) {
			super.move(1, 1);
		}
	}
	
	@Override
	public void move(int x, int y) {
		if(x > 0 && y > 0) {
			super.move(x, y);
		}
	}
	
	@Override
    public String toString() {
        return "(" + getX() + "," + getY() +  ")의 점";
    }
	
	public static void main(String[] args) {
		PositivePoint p = new PositivePoint(10, 10);
		p.move(5, 5);
		System.out.println(p.toString() + "입니다.");
		
		p.move(2, -2);
		System.out.println(p.toString() + "입니다.");
		
		PositivePoint q = new PositivePoint(-10, 10);
		
		System.out.println(q.toString() + "입니다.");
	}
}
