package PracticalExercise2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageCircleDrag extends JFrame {

    public ImageCircleDrag() {
        setTitle("이미지 위에 원 드래깅 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new MyPanel());
        
        setSize(500, 500);
        setVisible(true);
    }

    class MyPanel extends JPanel {
        private Image img = null;
        private int circleX = 100;
        private int circleY = 100;
        private final int RADIUS = 20;

        public MyPanel() {
            ImageIcon icon = new ImageIcon("back.png"); 
            img = icon.getImage();

            this.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    circleX = e.getX();
                    circleY = e.getY();

                    repaint();
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (img != null) {
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            }

            g.setColor(Color.GREEN);
            g.fillOval(circleX - RADIUS, circleY - RADIUS, RADIUS * 2, RADIUS * 2);
        }
    }

    public static void main(String[] args) {
        new ImageCircleDrag();
    }
}
