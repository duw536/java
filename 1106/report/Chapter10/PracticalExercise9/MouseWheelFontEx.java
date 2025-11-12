package PracticalExercise9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelFontEx extends JFrame {

    private JLabel label;

    public MouseWheelFontEx() {
        setTitle("마우스 휠을 굴려 폰트 크기 조절");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        label = new JLabel("Love Java");
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.addMouseWheelListener(new MouseWheelListener() {

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int n = e.getWheelRotation();
                Font f = label.getFont();
                int size = f.getSize();

                if (n < 0) {
                    size -= 5;
                    if (size < 5) {
                        size = 5;
                    }
                } 
                else {
                    size += 5;
                }

                label.setFont(new Font(f.getName(), f.getStyle(), size));
            }
        });

        c.add(label);

        setSize(350, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MouseWheelFontEx();
    }
}
