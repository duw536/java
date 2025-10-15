package PracticalExercise9_10;

import java.util.Scanner;

class SortedArray extends BaseArray {

    private int[] sortedArr;
    private int currentIndex;

    public SortedArray(int capacity) {
        super(capacity);
        this.sortedArr = new int[capacity];
        this.currentIndex = 0;
    }

    @Override
    public void add(int n) {
        if (currentIndex >= this.sortedArr.length) return;

        int i;
        for (i = 0; i < currentIndex; i++) {
            if (sortedArr[i] > n) {
                break;
            }
        }
        
        for (int j = currentIndex; j > i; j--) {
            sortedArr[j] = sortedArr[j - 1];
        }

        sortedArr[i] = n;
        currentIndex++;
    }
   
    public void print() {
        for (int i = 0; i < currentIndex; i++) {
            System.out.print(sortedArr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SortedArray sArray = new SortedArray(10);
        Scanner scanner = new Scanner(System.in);
        System.out.print(">>");

        for (int i = 0; i < sArray.length(); i++) {
            int n = scanner.nextInt();
            sArray.add(n);
        }

        sArray.print();
        scanner.close();
    }
}