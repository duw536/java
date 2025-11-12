package PracticalExercise3;

import javax.swing.*;
import java.awt.*;

public class GridLayoutExample extends JFrame {

    public GridLayoutExample() {
        setTitle("GridLayout으로 10개의 버튼을 배치한 프레임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new GridLayout(1, 10));
        for (int i = 0; i < 10; i++) {
            String text = Integer.toString(i);
            JButton button = new JButton(text);
            c.add(button);
        }
        setSize(800, 100);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GridLayoutExample();
    }
}
