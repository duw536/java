package PracticalExercise3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PracticalExercise3 extends JFrame {
    public PracticalExercise3() {
        setTitle("문제 3: 툴바와 종료");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        createToolBar();

        setVisible(true);
    }

    private void createToolBar() {
        JToolBar toolBar = new JToolBar("내 툴바");
        toolBar.setBackground(Color.LIGHT_GRAY);

        JButton exitBtn = new JButton("종료");

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        null, 
                        "정말 종료하시겠습니까?", 
                        "확인", 
                        JOptionPane.YES_NO_OPTION
                );

                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        toolBar.add(exitBtn);

        add(toolBar, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new PracticalExercise3();
    }
}
