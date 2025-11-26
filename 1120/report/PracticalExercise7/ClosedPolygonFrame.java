package PracticalExercise7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ClosedPolygonFrame extends JFrame {
    public ClosedPolygonFrame() {
        setTitle("마우스로 폐다각형 그리기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        setSize(400, 300);
        setVisible(true);
    }

    class MyPanel extends JPanel {
        private Vector<Point> v = new Vector<Point>();

        public MyPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    Point p = e.getPoint();
                    v.add(p);
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            if (v.size() == 0) return;
            int[] x = new int[v.size()];
            int[] y = new int[v.size()];
            for (int i = 0; i < v.size(); i++) {
                Point p = v.get(i);
                x[i] = p.x;
                y[i] = p.y;
            }
            g.drawPolygon(x, y, v.size());
        }
    }

    public static void main(String[] args) {
        new ClosedPolygonFrame();
    }
}
