package PracticalExercise2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DragColorChange extends JFrame {

    private Container contentPane;

    public DragColorChange() {
        setTitle("드래깅동안노랑색으로");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        contentPane = getContentPane();

        contentPane.setBackground(Color.GREEN);

        contentPane.addMouseMotionListener(new MouseMotionAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                contentPane.setBackground(Color.YELLOW);
            }
        });

        contentPane.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseReleased(MouseEvent e) {
                contentPane.setBackground(Color.GREEN);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DragColorChange());
    }
}
