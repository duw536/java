package Basic;

//(3)While 문을 이용하여 동일하게 실행되는 WhileLoopArray 클래스를 작성하라

public class WhileLoopArray {
	public static void main(String[] args) {
		int n[] = {1, -2, 6, 20, 5, 72, -16, 256};
		int i = 0;
		
		while (i < n.length) {
			if (n[i] > 0 && n[i] % 4 == 0) {
				System.out.printf(n[i] + " ");
			}
			i++;
		}
	}
}
