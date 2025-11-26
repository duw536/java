package PracticalExercise5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageZoom extends JFrame {
    
    public ImageZoom() {
        setTitle("그래픽 이미지 확대 축소 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyPanel panel = new MyPanel();
        setContentPane(panel);       
        setSize(400, 400);
        setVisible(true);

        panel.requestFocus();
    }

    class MyPanel extends JPanel {
        private Image img;
        private int width, height;

        public MyPanel() {
            ImageIcon icon = new ImageIcon("apple.png");
            img = icon.getImage();
            width = img.getWidth(this);
            height = img.getHeight(this);

            setFocusable(true);
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    char keyChar = e.getKeyChar();
                    
                    if (keyChar == '+') {
                        width = (int)(width * 1.1);
                        height = (int)(height * 1.1);
                        
                        repaint();
                    } else if (keyChar == '-') {
                        width = (int)(width * 0.9);
                        height = (int)(height * 0.9);
                        
                        repaint();
                    }
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 10, 10, width, height, this);
        }
    }

    public static void main(String[] args) {
        new ImageZoom();
    }
}
