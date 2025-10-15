package PracticalExercise1_2;

public class SmartTV extends ColorTV{
	private String address;
	
	public SmartTV(String address, int size, int color) {		
		super(size, color);
		this.address = address;
	}
	
	public void printProperty() {
		System.out.println("나의 SmartTV는 " + this.address + " 주소의 " + getSize() + "인치 " + getColor() + "컬러");
	}
}
