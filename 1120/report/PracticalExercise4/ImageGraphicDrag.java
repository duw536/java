package PracticalExercise4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageGraphicDrag extends JFrame {
    private MyContentPanel contentPanel = new MyContentPanel();

    public ImageGraphicDrag() {
        setTitle("이미지 그래픽 드래깅 연습 (4번 문제)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(contentPanel);

        setSize(400, 400);
        setVisible(true);
    }

    class MyContentPanel extends JPanel {
        private Image img;
        private int imgX = 100, imgY = 100;
        private int imgWidth, imgHeight;

        public MyContentPanel() {
            setLayout(null);

            ImageIcon icon = new ImageIcon("apple.png");
            img = icon.getImage();
            imgWidth = img.getWidth(this);
            imgHeight = img.getHeight(this);

            MyMouseListener listener = new MyMouseListener();
            addMouseListener(listener);
            addMouseMotionListener(listener);
            setFocusable(true);
            requestFocus();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(img, imgX, imgY, this);
        }

        class MyMouseListener extends MouseAdapter {
            private Point startOffset = null;

            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                if (mouseX >= imgX && mouseX <= imgX + imgWidth &&
                    mouseY >= imgY && mouseY <= imgY + imgHeight) {
                	
                    startOffset = new Point(mouseX - imgX, mouseY - imgY);
                } else {
                    startOffset = null;
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (startOffset != null) {
                    imgX = e.getX() - startOffset.x;
                    imgY = e.getY() - startOffset.y;

                    repaint();
                }
            }
        }
    }

    public static void main(String[] args) {
        new ImageGraphicDrag();
    }
}