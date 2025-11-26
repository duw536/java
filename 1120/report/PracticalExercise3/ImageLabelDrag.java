package PracticalExercise3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImageLabelDrag extends JFrame {
    
    public ImageLabelDrag() {
        setTitle("이미지 레이블 드래깅 연습");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(null); 

        ImageIcon icon = new ImageIcon("apple.png");

        JLabel imageLabel = new JLabel(icon);

        imageLabel.setSize(icon.getIconWidth(), icon.getIconHeight());
        imageLabel.setLocation(100, 100);

        MyMouseListener listener = new MyMouseListener();
        imageLabel.addMouseListener(listener);
        imageLabel.addMouseMotionListener(listener);

        c.add(imageLabel);
        
        setSize(400, 400);
        setVisible(true);
    }

    class MyMouseListener extends MouseAdapter {
        private Point startPoint = null;
        
        @Override
        public void mousePressed(MouseEvent e) {
            startPoint = e.getPoint();
        }
        
        @Override
        public void mouseDragged(MouseEvent e) {
            Component c = (Component) e.getSource();
            Point location = c.getLocation();

            int newX = location.x + e.getX() - startPoint.x;
            int newY = location.y + e.getY() - startPoint.y;

            c.setLocation(newX, newY);
        }
    }

    public static void main(String[] args) {
        new ImageLabelDrag();
    }
}
