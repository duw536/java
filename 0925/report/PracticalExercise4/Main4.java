package PracticalExercise4;

public class Main4 {
	public static void main(String[] args) {
		Average avg = new Average();
		avg.put(10);
		avg.put(15);
		avg.put(100);
		
		avg.showAll();
		System.out.println("평균은 " + avg.getAvg());
	}
}


