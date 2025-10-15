package PracticalExercise3_7;
//PracticalExercise7
public class Point3DColor extends Point {
	
	private int z;
	private String color;
	
	public Point3DColor(int x, int y, int z, String color) {
		super(x, y);
		this.z = z;
		this.color = color;
	}
	
	public int getZ() {
		return z;
	}
	
	public void move(Point3DColor targetPoint) {
        super.move(targetPoint.getX(), targetPoint.getY());
        this.z = targetPoint.getZ();
    }
	
	@Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point3DColor)) {
            return false;
        }

        Point3DColor other = (Point3DColor) obj;

        if (this.getX() == other.getX() &&
            this.getY() == other.getY() &&
            this.z == other.z &&
            this.color.equals(other.color)) { 
            return true; 
        }

        return false; 
    }
	
	@Override
    public String toString() {
        return "(" + getX() + "," + getY() + "," + this.z + ")" + color + "의 점";
    }
	
	public static void main(String[] args) {
		Point3DColor p = new Point3DColor(10, 20, 30, "RED");
		System.out.println(p.toString() + "입니다.");
		
		Point3DColor q = new Point3DColor(1, 2, 3, "BLUE");
		System.out.println(q.toString() + "입니다.");
		
		Point3DColor r = new Point3DColor(10, 20, 30, "RED");
		if(p.equals(r)) System.out.println("예. 같은 위치 같은 색깔의 점입니다.");
		else System.out.println("아니오");
		
	}
}
