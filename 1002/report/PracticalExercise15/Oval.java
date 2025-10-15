package PracticalExercise15;

class Oval implements Shape {
    private int a, b;

    public Oval(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void draw() {
        System.out.println(a + "x" + b + "에 내접하는 타원");
    }

    @Override
    public double getArea() {
        return PI * a * b / 4.0;
    }
}