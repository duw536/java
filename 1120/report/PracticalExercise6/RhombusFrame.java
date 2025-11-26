package PracticalExercise6;

import javax.swing.*;
import java.awt.*;

public class RhombusFrame extends JFrame {
    public RhombusFrame() {
        setTitle("그래픽 다각형 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new RhombusPanel());
        setSize(300, 300);
        setVisible(true);
    }

    class RhombusPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            int w = getWidth();
            int h = getHeight();

            for (int i = 1; i <= 10; i++) {
                int xRadius = (i * w) / 20;
                int yRadius = (i * h) / 20;
                int[] xPoints = { w/2, w/2 + xRadius, w/2, w/2 - xRadius };
                int[] yPoints = { h/2 - yRadius, h/2, h/2 + yRadius, h/2 };

                g.drawPolygon(xPoints, yPoints, 4);
            }
        }
    }

    public static void main(String[] args) {
        new RhombusFrame();
    }
}
