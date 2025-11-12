package PracticalExercise4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MoveBlockByKey extends JFrame {

    private final int GRID_CELL_SIZE = 50;
    private int gridX = 2;
    private int gridY = 2;
    
    private JLabel instructionLabel;
    private GridPanel floorPanel;
    private JLabel blockLabel;

    public MoveBlockByKey() {
        setTitle("상하좌우 키로 블록 이동");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        instructionLabel = new JLabel("상하좌우 키로 블록을 이동시킬 수 있습니다.", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        contentPane.add(instructionLabel, BorderLayout.NORTH);

        floorPanel = new GridPanel();
        floorPanel.setLayout(null);
        floorPanel.setBackground(Color.WHITE);
        contentPane.add(floorPanel, BorderLayout.CENTER);

        blockLabel = new JLabel();
        blockLabel.setBackground(Color.BLUE);
        blockLabel.setOpaque(true);

        blockLabel.setBounds(gridX * GRID_CELL_SIZE, gridY * GRID_CELL_SIZE, 
                             GRID_CELL_SIZE, GRID_CELL_SIZE);

        floorPanel.add(blockLabel);
        floorPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int maxGridX = (floorPanel.getWidth() / GRID_CELL_SIZE) - 1;
                int maxGridY = (floorPanel.getHeight() / GRID_CELL_SIZE) - 1;

                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        if (gridY > 0) gridY--;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (gridY < maxGridY) gridY++;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (gridX > 0) gridX--;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (gridX < maxGridX) gridX++;
                        break;
                }
                
                blockLabel.setLocation(gridX * GRID_CELL_SIZE, gridY * GRID_CELL_SIZE);
            }
        });

        setVisible(true);
        
        floorPanel.setFocusable(true);
        floorPanel.requestFocus();
    }

    class GridPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.red);
            g2d.setStroke(new BasicStroke(1));
            
            int width = getWidth();
            int height = getHeight();

            for (int x = 0; x < width; x += GRID_CELL_SIZE) {
                g2d.drawLine(x, 0, x, height);
            }

            for (int y = 0; y < height; y += GRID_CELL_SIZE) {
                g2d.drawLine(0, y, width, y);
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MoveBlockByKey());
    }
}