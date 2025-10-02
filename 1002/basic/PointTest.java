package basic;

public class PointTest {
    public static void main(String[] args) {

        ColorPoint cp1 = new ColorPoint("red");
        cp1.showColorPoint();
    }
}

class Point {

}

class ColorPoint extends Point {
    private String color;

    public ColorPoint() {
        
    }

    public ColorPoint(String color) {
        this.color = color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void showColorPoint() {
        System.out.print(color + " ");
    }
}
