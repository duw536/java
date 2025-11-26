package PracticalExercise9;

import javax.swing.*;
import java.awt.*;

class BlueLabel extends JLabel {
    
    public BlueLabel(String text) {
        super(text);
        setOpaque(true);
        setBackground(Color.BLUE);
    }

    @Override
    public void setBackground(Color c) {
        super.setBackground(Color.BLUE);
    }
}

public class BlueLabelEx extends JFrame {
    public BlueLabelEx() {
        setTitle("Blue Label 만들기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        BlueLabel label1 = new BlueLabel("hello");
        label1.setFont(new Font("Arial", Font.PLAIN, 10));
        label1.setForeground(Color.YELLOW);

        BlueLabel label2 = new BlueLabel("Big Hello");
        label2.setFont(new Font("Arial", Font.ITALIC, 50));
        label2.setForeground(Color.MAGENTA);

        c.add(label1);
        c.add(label2);

        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new BlueLabelEx();
    }
}
