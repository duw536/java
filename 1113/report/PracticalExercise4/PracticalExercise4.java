package PracticalExercise4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PracticalExercise4 extends JFrame {

    public PracticalExercise4() {
        setTitle("툴바에 숫자 입력 제어");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();

        c.setLayout(new BorderLayout());

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.add(new JLabel("학번 : "));

        JTextField tf = new JTextField(10);
        toolBar.add(tf);

        tf.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char keyChar = e.getKeyChar();

                if (!Character.isDigit(keyChar) && keyChar != '\b') {
                    e.consume();

                    JOptionPane.showMessageDialog(c, 
                        keyChar + "는 숫자 키가 아닙니다.\n숫자를 입력하세요.", 
                        "경고", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        c.add(toolBar, BorderLayout.SOUTH);

        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PracticalExercise4();
    }
}
