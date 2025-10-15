package PracticalExercise15;

class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
    	System.out.printf("반지름이 %.0f인 원\n", radius);
    }

    @Override
    public double getArea() {
        return PI * radius * radius;
    }
}