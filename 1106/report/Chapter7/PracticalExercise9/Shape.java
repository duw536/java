package PracticalExercise9;

abstract class Shape {
 public abstract void draw(); 
}

class Line extends Shape {
 
 @Override 
 public void draw() {
     System.out.println("Line");
 }
}

class Rect extends Shape {
 
 @Override 
 public void draw() {
     System.out.println("Rect");
 }
}

class Circle extends Shape {
 
 @Override 
 public void draw() {
     System.out.println("Circle");
 }
}
