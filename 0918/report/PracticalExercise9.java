package Basic;

public class PracticalExercise9 {
	public static void main(String[] args) {
		int[][] num = new int[4][4];
		System.out.println("4x4 배열에 랜덤한 값을 저장한 후 출력합니다.");
		
		for (int i = 0; i < num.length; i++) { 
            for (int j = 0; j < num[i].length; j++) { 
                num[i][j] = (int)(Math.random() * 256);
            }
        }
		
		for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < num[i].length; j++) {
                System.out.print(num[i][j] + "	");
            }
            System.out.println();
        }
	}
}
