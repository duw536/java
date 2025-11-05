package PracticalExercise1;

public class Student {
	String name;
	int code;
	
	public Student(String name, int code) {
		this.name = name;
		this.code = code;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Student) {
			Student s = (Student) obj;
			return this.name.equals(s.name) && this.code == s.code;
		}
		return false;
	}

	@Override
	public String toString() {
		return "학번이 " + code + "인 " + name;
	}
	
	
}
