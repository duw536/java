package Basic;

import java.util.Random;
import java.util.Scanner;

public class PracticalExercise10 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		Random rnd = new Random();
		int[][] array = new int[4][4];
		
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = rnd.nextInt(256);
            }
        }
        
        System.out.println("4x4 배열에 랜덤한 값을 저장한 후 출력합니다.");
        for (int[] row : array) {
            for (int n : row) {
                System.out.print(n + "	");
            }
            System.out.println();
        }
        
        System.out.print("임계값 입력>>");
        int threshold = scn.nextInt();
		
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] > threshold) {
                    array[i][j] = 255;
                } else {
                    array[i][j] = 0; 
                }
            }
        }
        
        for (int[] row : array) {
            for (int n : row) {
                System.out.print(n + "	");
            }
            System.out.println();
        }
	}
}
