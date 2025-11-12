package PracticalExercise7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClickCounterFrame extends JFrame {

    public ClickCounterFrame() {
        setTitle("클릭 횟수 카운트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource();

                int count = Integer.parseInt(button.getText());
                count++;

                button.setText(Integer.toString(count));
            }
        };

        for (int i = 0; i < 5; i++) {
            JButton btn = new JButton("0");
            btn.addActionListener(listener);
            c.add(btn);
        }

        setSize(400, 150);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ClickCounterFrame();
    }
}