package PracticalExercise10;

import javax.swing.*;
import java.awt.*;

public class WestGridFrame extends JFrame {

    public WestGridFrame() {
        setTitle("West Grid 프레임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel westPanel = new JPanel(new GridLayout(10, 1));

        Color[] colors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.CYAN, Color.BLUE, Color.MAGENTA, Color.DARK_GRAY,
            Color.PINK, Color.LIGHT_GRAY
        };

        for (int i = 0; i < 10; i++) {
            JButton btn = new JButton();
            btn.setBackground(colors[i]);
            btn.setOpaque(true);
            westPanel.add(btn);
        }
        c.add(westPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);
        centerPanel.setBackground(Color.WHITE);

        for (int i = 0; i < 10; i++) {
            JLabel numLabel = new JLabel(Integer.toString(i));
            numLabel.setForeground(Color.MAGENTA);

            int x = (int) (Math.random() * 151) + 50;
            int y = (int) (Math.random() * 151) + 50;
            
            numLabel.setBounds(x, y, 15, 15);
            centerPanel.add(numLabel);
        }
        c.add(centerPanel, BorderLayout.CENTER);

        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new WestGridFrame();
    }
}
