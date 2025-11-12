package PracticalExercise5;

import javax.swing.*;
import java.awt.*;

public class GridLayoutColorFrame extends JFrame {

    public GridLayoutColorFrame() {
        setTitle("4x4 Color 프레임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new GridLayout(4, 4));
        Color[] colors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.CYAN, Color.BLUE, Color.MAGENTA, Color.DARK_GRAY,
            Color.PINK, Color.LIGHT_GRAY, Color.WHITE, Color.GRAY,
            new Color(128, 0, 0),
            new Color(0, 128, 0),
            new Color(0, 0, 128),
            new Color(128, 128, 0)
        };

        for (int i = 0; i < 16; i++) {
            JLabel label = new JLabel(Integer.toString(i));
            label.setBackground(colors[i]);
            label.setOpaque(true); 
            c.add(label);
        }

        setSize(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GridLayoutColorFrame();
    }
}
