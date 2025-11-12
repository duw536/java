package PracticalExercise5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Problem5 extends JFrame {

    public Problem5() {
        setTitle("클릭 연습용 응용프로그램");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(null);

        JLabel label = new JLabel("C");
        label.setBounds(100, 100, 50, 20);

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int frameWidth = c.getWidth();
                int frameHeight = c.getHeight();
                int labelWidth = label.getWidth();
                int labelHeight = label.getHeight();
                int x = (int) (Math.random() * (frameWidth - labelWidth));
                int y = (int) (Math.random() * (frameHeight - labelHeight));

                label.setLocation(x, y);
            }
        });

        c.add(label);
        setSize(500, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Problem5();
    }
}