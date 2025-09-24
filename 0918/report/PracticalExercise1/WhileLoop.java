package Basic;

//int sum = 0, i = 1;
//while (true) {
//	if (i > 50) 
//		break;
//	
//		sum = sum + i;
//		i += 3;
//(1) 무엇을 계산하는 코드인가? 실행 결과 출력되는 내용은?
//1부터 시작하여 3씩 증가하는 숫자들을 50 이전까지 더하는 계산, 결과는 425
//(2) 위의 코드를 main()으로 만들고 whileloop 클래스로 완성하라 

public class WhileLoop {
	public static void main(String[] args) {
		int sum = 0, i = 1;
		
		while (true) {
			if (i > 50) 
				break;
			
				sum = sum + i;
				i += 3;
			
		}
		System.out.println(sum);
	}
}
