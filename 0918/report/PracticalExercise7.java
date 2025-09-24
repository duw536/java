package Basic;

public class PracticalExercise7 {
	public static void main(String[] args) {
		int[] num = new int[10];
        int sum = 0;
        
        for (int i = 0; i < num.length; i++) {
        	
            num[i] = (int)(Math.random() * 9) + 11;
            sum += num[i];
            
        }
        System.out.print("랜덤한 정수들...");
        for (int n : num) {
            System.out.print(n + " ");
        }
        System.out.println();
        
        double avg = (double)sum / num.length;
        System.out.println("평균은 " + avg);
	}
}
