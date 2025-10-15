package PracticalExercise15;

class Rect implements Shape {
    private int a, b;

    public Rect(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void draw() {
        System.out.println(a + "x" + b + "크기의 사각형");
    }

    @Override
    public double getArea() {
        return a * b;
    }
}