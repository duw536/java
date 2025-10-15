package PracticalExercise1_2;

public class ColorTV extends TV{
	private int color;
	
	public ColorTV(int size, int color) {
		super(size);
		this.color = color;
	}
	
	public void printProperty() {
		System.out.println(getSize() + "인치 " + this.color + "컬러");
	}
	
	protected int getColor() { return color; }
}
