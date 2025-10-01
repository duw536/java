package PracticalExercise7;

public class Memo {
	private String name;
	private String time;
	private String content;
	
	public Memo(String name, String time, String content) {
		this.name = name;
		this.time = time;
		this.content = content;
	}
	
	public boolean isSameName(Memo n) {
		return this.name.equals(n.name);
	}
	
	public String getName() {
        return this.name;
    }
	
	public void show() {
        System.out.println(this.name + ", " + this.time + " " + this.content);
    }
	
	public int length() {
        return this.content.length();
    }
}


