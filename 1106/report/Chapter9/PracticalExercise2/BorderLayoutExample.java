package PracticalExercise2;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutExample extends JFrame {

    public BorderLayoutExample() {
        setTitle("BorderLayout 배치 관리자 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setBackground(Color.yellow);
        c.setLayout(new BorderLayout(5, 7));
        c.add(new JButton("North"), BorderLayout.NORTH);
        c.add(new JButton("West"), BorderLayout.WEST);
        c.add(new JButton("Center"), BorderLayout.CENTER);
        c.add(new JButton("East"), BorderLayout.EAST);
        c.add(new JButton("South"), BorderLayout.SOUTH);
        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BorderLayoutExample();
    }
}