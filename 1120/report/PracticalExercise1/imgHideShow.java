package PracticalExercise1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class imgHideShow extends JFrame {

    public imgHideShow() {
        setTitle("이미지 그리기 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        
        setSize(400, 300);
        setVisible(true);
    }

    class MyPanel extends JPanel {
        private ImageIcon icon = new ImageIcon("back.png"); 
        private Image img = icon.getImage();
        private boolean isShow = true;

        public MyPanel() {
            setLayout(new FlowLayout());

            JButton btn = new JButton("Hide/Show");
            add(btn);

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isShow = !isShow;

                    repaint(); 
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (isShow) {
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        new imgHideShow();
    }
}
