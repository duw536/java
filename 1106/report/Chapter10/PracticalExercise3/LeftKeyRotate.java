package PracticalExercise3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LeftKeyRotate extends JFrame {

    private JLabel textLabel;

    public LeftKeyRotate() {
        setTitle("Left 키로 한 문자씩 왼쪽으로 회전");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout(FlowLayout.CENTER));

        textLabel = new JLabel("Love Java");
        textLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        textLabel.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    String currentText = textLabel.getText();
                    String firstChar = currentText.substring(0, 1);
                    String restOfString = currentText.substring(1);

                    textLabel.setText(restOfString + firstChar);
                }
            }
        });

        contentPane.add(textLabel);
        setVisible(true);

        textLabel.setFocusable(true);
        textLabel.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LeftKeyRotate());
    }
}