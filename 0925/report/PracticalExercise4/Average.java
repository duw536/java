package PracticalExercise4;

public class Average {
	private int[] data;
	private int nextIndex;
	
	public Average() {
		data = new int[10];
		nextIndex = 0;
	}
	
	public void put(int value) {
        if (nextIndex < data.length) { 
            data[nextIndex] = value;
            nextIndex++;
        }
    }
	
	public void showAll() {
        for (int i = 0; i < nextIndex; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
	
	public double getAvg() {
        if (nextIndex == 0) {
            return 0; 
        }
        int sum = 0;
        for (int i = 0; i < nextIndex; i++) {
            sum += data[i];
        }
        return (double)sum / nextIndex; 
    }
}


