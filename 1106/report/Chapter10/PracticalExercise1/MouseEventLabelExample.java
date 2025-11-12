package PracticalExercise1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseEventLabelExample extends JFrame {

    private JLabel textLabel;

    public MouseEventLabelExample() {
        setTitle("마우스 올리기 내리기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));

        textLabel = new JLabel("사랑해 자바");

        textLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));

        textLabel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                textLabel.setText("Love Java");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                textLabel.setText("사랑해 자바");
            }
        });

        contentPane.add(textLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MouseEventLabelExample();
            }
        });
    }
}