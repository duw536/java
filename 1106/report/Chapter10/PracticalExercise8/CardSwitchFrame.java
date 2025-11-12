package PracticalExercise8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardSwitchFrame extends JFrame {

    private JLabel firstCard = null;
    private JLabel lastSwapped1 = null; 
    private JLabel lastSwapped2 = null;
    private Color defaultColor = Color.YELLOW;
    private Color selectedColor = Color.MAGENTA; 

    public CardSwitchFrame() {
        setTitle("카드 스위치 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new GridLayout(3, 4, 10, 10)); 

        MouseAdapter listener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel clickedCard = (JLabel) e.getSource();

                if (firstCard == null) {
                    if (lastSwapped1 != null) {
                        lastSwapped1.setBackground(defaultColor);
                        lastSwapped2.setBackground(defaultColor);
                        lastSwapped1 = null;
                        lastSwapped2 = null;
                    }

                    firstCard = clickedCard;
                    firstCard.setBackground(selectedColor);

                } 
                else {
                    if (firstCard == clickedCard) {
                        firstCard.setBackground(defaultColor);
                        firstCard = null;
                    } 
                    else {
                        String tempText = firstCard.getText();
                        firstCard.setText(clickedCard.getText());
                        clickedCard.setText(tempText);
                        firstCard.setBackground(selectedColor);
                        clickedCard.setBackground(selectedColor);
                        lastSwapped1 = firstCard;
                        lastSwapped2 = clickedCard;
                        firstCard = null; 
                    }
                }
            }
        };
        
        for (int i = 0; i < 12; i++) {
            JLabel card = new JLabel(Integer.toString(i + 1));
            card.setOpaque(true); 
            card.setBackground(defaultColor);
            card.setHorizontalAlignment(SwingConstants.CENTER); 
            card.setFont(new Font("Garamond", Font.BOLD, 24)); 
            card.addMouseListener(listener); 
            c.add(card); 
        }

        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CardSwitchFrame();
    }
}