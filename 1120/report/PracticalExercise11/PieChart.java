package PracticalExercise11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PieChart extends JFrame {
    String[] fruitNames = {"apple", "cherry", "strawberry", "prune"};
    Color[] colors = {Color.RED, Color.BLUE, Color.MAGENTA, Color.ORANGE};

    int[] quantities = {0, 0, 0, 0}; 

    JTextField[] tf = new JTextField[4]; 
    ChartPanel chartPanel = new ChartPanel();

    public PieChart() {
        setTitle("파이 차트 그리기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.LIGHT_GRAY);
        
        for(int i=0; i<4; i++) {
            inputPanel.add(new JLabel(fruitNames[i]));
            tf[i] = new JTextField("0", 5);
            tf[i].addActionListener(new MyActionListener());
            inputPanel.add(tf[i]);
        }
        c.add(inputPanel, BorderLayout.NORTH);
        c.add(chartPanel, BorderLayout.CENTER);

        setSize(600, 500);
        setVisible(true);
    }

    class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(int i=0; i<4; i++) {
                try {
                    quantities[i] = Integer.parseInt(tf[i].getText());
                } catch (NumberFormatException ex) {
                    quantities[i] = 0;
                }
            }
            chartPanel.repaint();
        }
    }

    class ChartPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            int total = 0;
            for(int q : quantities) {
                total += q;
            }

            if(total == 0) return;

            int startAngle = 0;
 
            for(int i=0; i<4; i++) {
                double percentage = (double)quantities[i] / total * 100;
                int arcAngle = (int)Math.round((double)quantities[i] / total * 360);

                g.setColor(colors[i]);
                g.fillArc(150, 150, 250, 250, startAngle, arcAngle);

                String text = fruitNames[i] + " " + String.format("%.0f%%", percentage);
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString(text, 50 + (i * 130), 100); 

                startAngle += arcAngle;
            }
        }
    }

    public static void main(String[] args) {
        new PieChart();
    }
}