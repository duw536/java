package PracticalExercise9_10;
//PracticalExercise9
import java.util.Scanner;

public class BinaryArray extends BaseArray{
	private int threshold;
	
	public BinaryArray(int size, int threshold) {
        super(size); 
        this.threshold = threshold;
    }
	
	@Override
    public void print() {
        for(int i = 0; i < length(); i++) {
            if (array[i] > this.threshold) {
                System.out.print("1 ");
            } else {
                System.out.print("0 ");
            }
        }
        System.out.println(); 
    }
	
	public static void main(String[] args) {
		int threshold = 50;
		BinaryArray bArray = new BinaryArray(10, threshold);
		
		Scanner scanner = new Scanner(System.in);
		System.out.print(">>");
		for(int i = 0; i < bArray.length(); i++) {
			int n = scanner.nextInt();
			bArray.add(n);
		}
		bArray.print();
		scanner.close();
	}
}
