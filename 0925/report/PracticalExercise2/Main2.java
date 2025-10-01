package PracticalExercise2;

public class Main2 {
	public static void main(String[] args) {
		Cube cube = new Cube(1, 2, 3);
		System.out.println("큐브의 부피는 " + cube.getVolume());
		cube.increase(1, 2, 3);
		System.out.println("큐브의 부피는 " + cube.getVolume());
		if (cube.isZero()) {
			System.out.println("큐브의 부피는 0");
		}
		else {
			System.out.println("큐브의 부피는 0이 아님");
		}
	}
}

