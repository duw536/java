package PracticalExercise11;

public class ArrayUtil {
	
    public static int[] concat(int[] a, int[] b) {
        int newLength = a.length + b.length;
        int[] result = new int[newLength];

        for (int i = 0; i < a.length; i++) {
            result[i] = a[i];
        }

        for (int i = 0; i < b.length; i++) {
            result[a.length + i] = b[i];
        }
        
        return result;
    }

    public static void print(int[] a) {
        System.out.print("[ ");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println("]");
    }
}


