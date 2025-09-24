package Basic;

//(3)for문을 이용하여 동일하게 실행되는 ForLoop 클래스를 작성하라

public class ForLoop {
	public static void main(String[] args) {
		int sum = 0;
		
		for (int i = 1; i < 50; i+=3) {
			sum = sum + i;
		}
		System.out.println(sum);
	}
}
