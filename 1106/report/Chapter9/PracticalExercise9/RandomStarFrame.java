package PracticalExercise9;

import javax.swing.*;
import java.awt.*;

public class RandomStarFrame extends JFrame {

    public RandomStarFrame() {
        setTitle("랜덤한 별을 가진 프레임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.setBackground(Color.LIGHT_GRAY); 
        northPanel.add(new JLabel("랜덤한 별을 가진 프레임"));
        northPanel.add(new JTextField(8)); 
        northPanel.add(new JButton("별 만들기")); 
        c.add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
        southPanel.setBackground(Color.yellow);
        southPanel.add(new JButton("Exit")); 
        c.add(southPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(null);

        JButton refreshBtn = new JButton("Refresh");
        refreshBtn.setBounds(10, 10, 80, 25); 
        centerPanel.add(refreshBtn);

        for (int i = 0; i < 15; i++) {
            JLabel star = new JLabel("*");
            star.setForeground(Color.RED); 

            int x = (int) (Math.random() * 281); 
            int y = (int) (Math.random() * 181); 
            
            star.setBounds(x, y, 10, 10); 
            
            centerPanel.add(star);
        }
        c.add(centerPanel, BorderLayout.CENTER);

        setSize(350, 300); 
        setVisible(true);
    }

    public static void main(String[] args) {
        new RandomStarFrame();
    }
}