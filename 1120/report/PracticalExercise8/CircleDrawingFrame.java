package PracticalExercise8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

class Circle {
    int x, y;
    int radius;

    public Circle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
}

public class CircleDrawingFrame extends JFrame {

    public CircleDrawingFrame() {
        setTitle("마우스로 원 그리기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel()); 
        setSize(800, 500);
        setVisible(true);
    }

    class MyPanel extends JPanel {
        Vector<Circle> v = new Vector<Circle>();

        public MyPanel() {
            addMouseListener(new MyMouseListener());
            addMouseMotionListener(new MyMouseListener());
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.MAGENTA);

            for (Circle c : v) {
                g.drawOval(c.x - c.radius, c.y - c.radius, c.radius * 2, c.radius * 2);
            }
        }

        class MyMouseListener extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                int startX = e.getX();
                int startY = e.getY();
                v.add(new Circle(startX, startY, 0));
            }

            public void mouseDragged(MouseEvent e) {
                Circle c = v.lastElement();

                Point startP = new Point(c.x, c.y);
                Point endP = e.getPoint();
                c.radius = (int) startP.distance(endP);

                repaint(); 
            }

            public void mouseReleased(MouseEvent e) {
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        new CircleDrawingFrame();
    }
}
