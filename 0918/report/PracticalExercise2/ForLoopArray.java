package Basic;

//int n[] = {1, -2, 6, 20, 5, 72, -16, 256};
//for(int i = 0; i < n.length; i++) {
//	if(n[i] > 0 && n[i] % 4 == 0) {
//		System.out.printf(n[i] + " ");
//	}
//}
//(1)무엇을 계산하는 코드인가? 실행 결과 출력되는 내용은?
//0보다 크고 4로 나누어 떨어지는 숫자들을 찾아 출력, 결과는 20 72 256

public class ForLoopArray {
	public static void main(String[] args) {
		int n[] = {1, -2, 6, 20, 5, 72, -16, 256};
		int i;
		for(i = 0; i < n.length; i++) {
			if(n[i] > 0 && n[i] % 4 == 0) {
				System.out.printf(n[i] + " ");
			}
		}
		
	}
}
