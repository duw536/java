package PracticalExercise7;

import javax.swing.*;
import java.awt.*;

public class CalculatorFrame extends JFrame {

    public CalculatorFrame() {
        setTitle("간단 스윙 계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel northPanel = new JPanel();
        northPanel.setBackground(Color.LIGHT_GRAY);
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JLabel("수식"));
        northPanel.add(new JTextField(15));
        c.add(northPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonTitles = {
            "C", "UN", "BK", "/",
            "7", "8", "9", "x",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", "%"
        };

        for (int i = 0; i < buttonTitles.length; i++) {
            JButton btn = new JButton(buttonTitles[i]);

            if (buttonTitles[i].equals("=")) {
                btn.setBackground(Color.cyan);
                btn.setOpaque(true);
            }

            centerPanel.add(btn);
        }
        c.add(centerPanel, BorderLayout.CENTER);
        JPanel southPanel = new JPanel();
        southPanel.setBackground(Color.YELLOW);
        southPanel.setLayout(new FlowLayout());
        southPanel.add(new JLabel("계산 결과"));
        southPanel.add(new JTextField(15));
        c.add(southPanel, BorderLayout.SOUTH);

        pack();
        setSize(300, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorFrame();
    }
}