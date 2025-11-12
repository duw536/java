package PracticalExercise1;

import javax.swing.*;
import java.awt.*;

public class SwingWindowExample extends JFrame {

    public SwingWindowExample() {
        setTitle("Make a Window using Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setBackground(Color.YELLOW);
        setSize(400, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new SwingWindowExample();
    }
}