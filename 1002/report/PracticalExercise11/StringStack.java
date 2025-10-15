package PracticalExercise11;

import java.util.Scanner;

class StringStack implements IStack {
    private String[] stack;
    private int top;
    private int capacity;

    public StringStack(int capacity) {
        this.capacity = capacity;
        this.stack = new String[capacity];
        this.top = 0;
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public int length() {
        return this.top;
    }

    @Override
    public boolean push(String val) {
        if (top < capacity) {
            stack[top] = val;
            top++;
            return true;
        }
        return false;
    }

    @Override
    public String pop() {
        if (top > 0) {
            top--;
            return stack[top];
        }
        return null; 
    }
}

class StackApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("스택 용량>>");
        int stackCapacity = scanner.nextInt();
        
        IStack stack = new StringStack(stackCapacity);

        while (true) {
            System.out.print("문자열 입력>>");
            String input = scanner.next();

            if (input.equals("그만")) {
                break;
            }

            if (!stack.push(input)) {
                System.out.println("스택이 꽉 차서 디지털 저장 불가");
            }
        }
        
        System.out.print("스택에 저장된 문자열 팝 : ");
        
        while (stack.length() > 0) {
            System.out.print(stack.pop() + " ");
        }
        
        scanner.close();
    }
}