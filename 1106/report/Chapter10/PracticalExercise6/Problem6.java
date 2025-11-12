package PracticalExercise6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Problem6 extends JFrame {

    private JPanel centerPanel;
    private JLabel[] numberLabels = new JLabel[11];
    private JComponent[] colorComponents = new JComponent[10];
    private Color[] fixedColors = {
        Color.RED,
        Color.LIGHT_GRAY,
        Color.blue,
        Color.yellow,
        Color.cyan,
        Color.gray,
        Color.pink,
        Color.green,
        Color.orange,
        Color.magenta
    };

    public Problem6() {
        setTitle("West Grid 프레임 (10색 고정)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();

        JPanel westPanel = new JPanel(new GridLayout(10, 1)); 

        ActionListener colorListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent source = (JComponent) e.getSource();
                Color newColor = source.getBackground();

                for (JLabel label : numberLabels) {
                    if (label != null) {
                        label.setForeground(newColor);
                    }
                }
            }
        };

        for (int i = 0; i < colorComponents.length; i++) {
            JButton button = new JButton(); 

            Color bgColor = fixedColors[i]; 
            
            button.setBackground(bgColor);
            button.setOpaque(true); 
            button.setBorderPainted(false); 
            button.addActionListener(colorListener); 
            
            colorComponents[i] = button; 
            westPanel.add(colorComponents[i]); 
        }
        
        c.add(westPanel, BorderLayout.WEST); 

        centerPanel = new JPanel();
        centerPanel.setLayout(null); 
        centerPanel.setBackground(Color.WHITE);

        for (int i = 0; i < numberLabels.length; i++) {
            int num = (int) (Math.random() * 10);
            numberLabels[i] = new JLabel(Integer.toString(num));
            numberLabels[i].setForeground(Color.BLACK); 
            numberLabels[i].setSize(20, 20); 
            
            setRandomPosition(numberLabels[i]);
            
            centerPanel.add(numberLabels[i]);
        }

        centerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (JLabel label : numberLabels) {
                    setRandomPosition(label);
                }
            }
        });

        c.add(centerPanel, BorderLayout.CENTER); 

        setSize(600, 500); 
        setVisible(true);
    }

    private void setRandomPosition(JLabel label) {
        int x = (int) (Math.random() * 151) + 50; 
        int y = (int) (Math.random() * 151) + 50;
        label.setLocation(x, y);
    }

    public static void main(String[] args) {
        new Problem6();
    }
}