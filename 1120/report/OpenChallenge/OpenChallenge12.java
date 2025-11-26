package OpenChallenge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OpenChallenge12 extends JFrame {
    
    public OpenChallenge12() {
        setTitle("Open Challenge 12");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new ImagePanel());
        
        setSize(400, 400);
        setVisible(true);
    }

    class ImagePanel extends JPanel {
        private Image img;
        private int clipX = 0;
        private int clipY = 0;
        
        public ImagePanel() {
            ImageIcon icon = new ImageIcon("coffee.png");
            img = icon.getImage();    
            this.setFocusable(true);
            this.requestFocus();
            this.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch(e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            clipY -= 10;
                            break;
                        case KeyEvent.VK_DOWN:
                            clipY += 10;
                            break;
                        case KeyEvent.VK_LEFT:
                            clipX -= 10;
                            break;
                        case KeyEvent.VK_RIGHT:
                            clipX += 10;
                            break;
                    }
                    repaint(); 
                }
            });
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            g.setClip(clipX, clipY, 50, 50);

            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

        }
    }

    public static void main(String[] args) {
        new OpenChallenge12();
    }
}