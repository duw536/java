package PracticalExercise10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BlockDragGame extends JFrame {

    private Container contentPane;

    public BlockDragGame() {
        setTitle("블록 생성 및 드래그 배치");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);

        contentPane = getContentPane();
        contentPane.setLayout(null);
        contentPane.setBackground(Color.WHITE);
        contentPane.addKeyListener(new MyKeyListener());
        contentPane.setFocusable(true);
        contentPane.requestFocus();

        setVisible(true);
    }

    private void createBlock() {
        JLabel block = new JLabel();
        block.setSize(80, 80);
        block.setLocation(100, 100);

        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);
        block.setBackground(new Color(r, g, b));
        block.setOpaque(true);

        MyMouseListener listener = new MyMouseListener();
        block.addMouseListener(listener);
        block.addMouseMotionListener(listener);

        contentPane.add(block);
        contentPane.repaint();
    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyChar() == 'm') {
                createBlock();
            }
        }
    }

    class MyMouseListener extends MouseAdapter {
        private Point offset; 

        @Override
        public void mousePressed(MouseEvent e) {
            Component label = (Component) e.getSource();
            offset = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // 드래그 중인 라벨(Component)을 가져옴
            Component label = (Component) e.getSource();

            int newX = label.getX() + e.getX() - offset.x;
            int newY = label.getY() + e.getY() - offset.y;

            label.setLocation(newX, newY);
        }
    }

    public static void main(String[] args) {
        new BlockDragGame();
    }
}
