package PracticalExercise8;

import javax.swing.*;
import java.awt.*;

public class CardGameFrame extends JFrame {

    private static final Color CARD_COLOR = new Color(0, 128, 0);

    public CardGameFrame() {
        setTitle("16장의 카드의 뒷면...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout(5, 5));
        c.setBackground(Color.LIGHT_GRAY);
     
        JPanel northPanel = new JPanel();
        northPanel.add(new JLabel("숨겨진 이미지 찾기"));
        c.add(northPanel, BorderLayout.NORTH);
        northPanel.setBackground(Color.YELLOW);
     
        JPanel southPanel = new JPanel();
        southPanel.add(new JButton("실행 시작"));
        c.add(southPanel, BorderLayout.SOUTH);
        southPanel.setBackground(Color.YELLOW);
    
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 4, 3, 3));

        for (int i = 0; i < 16; i++) {
            JLabel cardLabel = new JLabel(Integer.toString(i + 1));
            cardLabel.setBackground(CARD_COLOR);
            cardLabel.setForeground(Color.WHITE);
            cardLabel.setHorizontalAlignment(JLabel.CENTER);
            cardLabel.setOpaque(true);
            cardLabel.setPreferredSize(new Dimension(80, 80));
            centerPanel.add(cardLabel);
        }
        c.add(centerPanel, BorderLayout.CENTER);
        JLabel westMargin = createMarginLabel();
        c.add(westMargin, BorderLayout.WEST);  
        westMargin.setBackground(Color.LIGHT_GRAY);
        JLabel eastMargin = createMarginLabel();
        c.add(eastMargin, BorderLayout.EAST);
        eastMargin.setBackground(Color.LIGHT_GRAY);

        pack();
        setResizable(false);
        setVisible(true);
    }

    private JLabel createMarginLabel() {
        JLabel margin = new JLabel(" ");
        margin.setBackground(CARD_COLOR);
        margin.setOpaque(true);
        margin.setPreferredSize(new Dimension(20, 0));
        return margin;
    }

    public static void main(String[] args) {
        new CardGameFrame();
    }
}
