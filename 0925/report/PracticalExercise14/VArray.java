package PracticalExercise14;

public class VArray {
    private int[] arr; 
    private int size;  

    public VArray(int initialCapacity) {
        this.arr = new int[initialCapacity];
        this.size = 0;
    }
    
    public int capacity() {
        return arr.length;
    }

    public int size() {
        return size;
    }

    public void add(int value) {
        if (size == arr.length) {
            doubleCapacity();
        }
        arr[size] = value;
        size++;
    }

    public void insert(int index, int value) {
        if (index < 0 || index > size) {
            return; 
        }
        if (size == arr.length) {
            doubleCapacity();
        }
        for (int i = size - 1; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = value;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            return; 
        }
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }

    public void printAll() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    private void doubleCapacity() {
        int[] newArr = new int[arr.length * 2];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        this.arr = newArr;
    }
}