package control;

public class DoWhileExam01 {

	public static void main(String[] args) {
		char c ='a'; //'a' : 61h, 97, 'A' : 41h, 65 
		
		do {
			System.out.print(c);
			c = (char)(c + 1);
		} while (c <= 'z');

	}

}
