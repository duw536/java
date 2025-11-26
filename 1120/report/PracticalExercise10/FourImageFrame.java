package PracticalExercise10;

import javax.swing.*;
import java.awt.*;

public class FourImageFrame extends JFrame {

    public FourImageFrame() {
        setTitle("그래픽 이미지 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new ImagePanel());   
        setSize(400, 400);
        setVisible(true);
    }

    class ImagePanel extends JPanel {
        private Image img;

        public ImagePanel() {
            img = new ImageIcon("apple.png").getImage(); 
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (img == null) return;

            int panelWidth = this.getWidth();
            int panelHeight = this.getHeight();
            int drawW = (panelWidth - 30) / 2;
            int drawH = (panelHeight - 30) / 2;
            int srcW = img.getWidth(this);
            int srcH = img.getHeight(this);
            int srcHalfW = srcW / 2;
            int srcHalfH = srcH / 2;

            g.drawImage(img, 
                    10, 10, 10 + drawW, 10 + drawH, 
                    0, 0, srcHalfW, srcHalfH, 
                    this);

            g.drawImage(img, 
                    20 + drawW, 10, 20 + drawW * 2, 10 + drawH, 
                    srcHalfW, 0, srcW, srcHalfH, 
                    this);

            g.drawImage(img, 
                    10, 20 + drawH, 10 + drawW, 20 + drawH * 2, 
                    0, srcHalfH, srcHalfW, srcH, 
                    this);

            g.drawImage(img, 
                    20 + drawW, 20 + drawH, 20 + drawW * 2, 20 + drawH * 2, 
                    srcHalfW, srcHalfH, srcW, srcH, 
                    this);
        }
    }

    public static void main(String[] args) {
        new FourImageFrame();
    }
}
