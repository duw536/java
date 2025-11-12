package PracticalExercise4;

import javax.swing.*;
import java.awt.*;

public class ButtonColorExample extends JFrame {

    public ButtonColorExample() {
        setTitle("배경색을 가진 10개의 버튼");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new GridLayout(1, 10));
        Color[] colors = {
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.CYAN, Color.BLUE, Color.MAGENTA, Color.DARK_GRAY,
            Color.PINK, Color.LIGHT_GRAY
        };
        
        for (int i = 0; i < 10; i++) {
            String text = Integer.toString(i);
            JButton button = new JButton(text);
            button.setBackground(colors[i]);
            button.setOpaque(true);

            c.add(button);
        }

        setSize(800, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ButtonColorExample();
    }
}