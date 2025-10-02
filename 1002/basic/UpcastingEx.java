package basic;

public class UpcastingEx {
	public static void main(String[] args) {
		Person p;
		Student s = new Student("홍길동");
		p = s;
	}
}

class person {
	String name;
	String id;
	public person(String name) {
		this.name = name;
	}
	
}
class Student extends person {
	String grade;
	String deparment;
	
	public Student(String name) {
		super(name);
	}
	
}

