package PracticalExercise2;

public class Cube {
	private int width;
	private int length;
	private int height;
	
	public Cube(int width, int lenght, int height) {
		this.width = width;
		this.length = lenght;
		this.height = height;
	}
	
	public int getVolume() {
		return width * length * height;
	}
	
	public void increase(int w, int l, int h) {
        this.width += w;
        this.length += l;
        this.height += h;
    }
	
	public boolean isZero() {
        if (getVolume() == 0) {
            return true;
        } else {
            return false;
        }
    }
}

