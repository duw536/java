package PracticalExercise6;

import javax.swing.*;
import java.awt.*;

public class GridFrame extends JFrame {
    public GridFrame() {
        setTitle("격자 그리기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GridPanel());
        setSize(300, 300);
        setVisible(true);
    }

    class GridPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);        
            int w = getWidth();
            int h = getHeight();

            for (int i = 1; i < 10; i++) {
                g.drawLine(i * w / 10, 0, i * w / 10, h);
                g.drawLine(0, i * h / 10, w, i * h / 10);
            }
        }
    }

    public static void main(String[] args) {
        new GridFrame();
    }
}
