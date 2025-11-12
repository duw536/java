package PracticalExercise6;

import javax.swing.*;
import java.awt.*;

public class RandomLabelFrame extends JFrame {

    public RandomLabelFrame() {
        setTitle("배치관리자 없는 컴포넌트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null); 
        for (int i = 0; i < 20; i++) {
            JLabel label = new JLabel();
            int r = (int) (Math.random() * 256);
            int g = (int) (Math.random() * 256);
            int b = (int) (Math.random() * 256);
            label.setBackground(new Color(r, g, b));
            label.setOpaque(true);

            int x = (int) (Math.random() * 240) + 10;
            int y = (int) (Math.random() * 240) + 10;
            label.setBounds(x, y, 10, 10);
            c.add(label);
        }

        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new RandomLabelFrame();
    }
}
