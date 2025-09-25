package Intro;

public class Circle {
	int radius;
	String name;
	
	double getArea() {
		return Math.PI * radius * radius;
	}
	public static void main(String[] args) {
		Circle c1 = new Circle();
		c1.radius = 5;
		double c1Area = c1.getArea();
		System.out.println("원의 면적 =" + c1Area);
	}
	
	
//	int radius; 
//	String name;
//	
//	public double getArea() { 
//		return 3.14*radius*radius;
//		}
//	
//	public static void main(String[] args) {
//		Circle pizza;
//		pizza = new Circle(); 
//		pizza.radius = 10; 
//		pizza.name = "자바피자"; 
//		double area = pizza.getArea();
//		System.out.println(pizza.name + "의 면적은 " + area);
//		Circle donut = new Circle();
//		donut.radius = 2; 
//		donut.name = "자바도넛"; 
//		area = donut.getArea(); 
//		System.out.println(donut.name + "의 면적은 " + area);
//	}
}
	


