package PracticalExercise1;

public class TV {
	private String brand;
	private int inch;
	private int price;
	
	public TV(String brand, int inch, int price) {
		this.brand = brand;
		this.inch = inch;
		this.price = price;
		
	}
	
	public void show() {
		System.out.println(brand + "에서 만든 " + price + "만원짜리 " + inch + "인치 TV");
	}
}

