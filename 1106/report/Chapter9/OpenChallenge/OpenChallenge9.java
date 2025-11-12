package OpenChallenge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenChallenge9 extends JFrame {

    private String text = "I can't help falling in love with you";
    private String[] words;
    private JLabel[] wordLabels;

    private JPanel centerPanel;

    public OpenChallenge9() {
        setTitle("단어 조립 게임!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        
        Container c = getContentPane();
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(new JLabel("단어 조립 게임! 순서대로 단어를 클릭하세요~"));
        JButton newTextButton = new JButton("New Text");
        northPanel.add(newTextButton);
        c.add(northPanel, BorderLayout.NORTH);
        northPanel.setBackground(Color.LIGHT_GRAY);

        JPanel southPanel = new JPanel(new FlowLayout());
        southPanel.add(new JLabel("이름"));
        southPanel.add(new JTextField(15));
        c.add(southPanel, BorderLayout.SOUTH);
        southPanel.setBackground(Color.YELLOW);

        centerPanel = new JPanel();
        centerPanel.setLayout(null);

        words = text.split(" ");
        wordLabels = new JLabel[words.length];

        for (int i = 0; i < words.length; i++) {
            wordLabels[i] = new JLabel(words[i]);
            centerPanel.add(wordLabels[i]);
        }
        
        setRandomPositions(); 
        
        c.add(centerPanel, BorderLayout.CENTER);

        newTextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRandomPositions();
            }
        });

        setVisible(true);
    }

    private void setRandomPositions() {
        for (JLabel label : wordLabels) {
            int x = (int) (Math.random() * 351);
            int y = (int) (Math.random() * 181);
            Dimension size = label.getPreferredSize();
            label.setBounds(x, y, size.width, size.height);
        }
        centerPanel.repaint();
    }

    public static void main(String[] args) {
        new OpenChallenge9();
    }
}