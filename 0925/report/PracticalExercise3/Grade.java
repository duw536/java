package PracticalExercise3;

public class Grade {
	private String name;
	private int java;
	private int web;
	private int os;

	public Grade(String name, int java, int web, int os) {
        this.name = name;
        this.java = java;
        this.web = web;
        this.os = os;
    }
	
	public String getName() {
        return name;
    }
	
	public int getAverage() {
        return (java + web + os) / 3;
    }
}
