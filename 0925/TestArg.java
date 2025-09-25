package Intro;

public class TestArg {
	int a, b, c, d;
	public TestArg(int a, int b, int c, int d) {
		this.a = a; this.b= b; this.c = c; this.d = d;
		
	}
	
	public TestArg(int a, int b, int c) {
		this(a, b, c, 10);
		
	}
	
	public TestArg(int a, int b) {
		this(a, b, 20, 10);
		
	}
	
	public TestArg(int a) {
		this(a, 30, 20, 10);
		
	}
	
	public TestArg() {
		this(10, 30, 20, 10);
		
	}
	public static void main(String[] args) {
		TestArg ta1 = new TestArg();
		TestArg ta2 = new TestArg(ta1.a);
		
	}
	
		
}
