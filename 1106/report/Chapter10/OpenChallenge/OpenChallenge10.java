package OpenChallenge;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class OpenChallenge10 extends JFrame {

    private int hiddenNumber;
    private String userChoice = null;

    private JPanel contentPane;
    private JLabel numberLabel;
    private JLabel messageLabel;
    private JButton oddButton;
    private JButton evenButton;
    private JButton confirmButton;
    private JButton againButton;

    public OpenChallenge10() {
        setTitle("홀짝 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 230);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        numberLabel = new JLabel("?");
        numberLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        numberLabel.setForeground(Color.WHITE);
        numberLabel.setOpaque(true);
        numberLabel.setBackground(new Color(128, 0, 128));
        numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        numberLabel.setBounds(100, 20, 100, 70);
        contentPane.add(numberLabel);

        messageLabel = new JLabel("무엇일까요?");
        messageLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBounds(50, 100, 200, 30);
        contentPane.add(messageLabel);

        oddButton = new JButton("홀");
        evenButton = new JButton("짝");
        confirmButton = new JButton("확인");
        againButton = new JButton("다시");

        oddButton.setBounds(20, 150, 60, 30);
        evenButton.setBounds(90, 150, 60, 30);
        confirmButton.setBounds(160, 150, 60, 30);
        againButton.setBounds(230, 150, 60, 30);

        contentPane.add(oddButton);
        contentPane.add(evenButton);
        contentPane.add(confirmButton);
        contentPane.add(againButton);

        ButtonClickListener listener = new ButtonClickListener();
        oddButton.addActionListener(listener);
        evenButton.addActionListener(listener);
        confirmButton.addActionListener(listener);
        againButton.addActionListener(listener);

        startNewGame();

        setVisible(true);
    }

    private void startNewGame() {
        hiddenNumber = new Random().nextInt(10) + 1;

        numberLabel.setText("?");
        messageLabel.setText("무엇일까요?");
        userChoice = null;
    }

    class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "홀":
                    userChoice = "홀";
                    messageLabel.setText("'" + userChoice + "'을 선택했습니다.");
                    break;
                case "짝":
                    userChoice = "짝";
                    messageLabel.setText("'" + userChoice + "'을 선택했습니다.");
                    break;
                case "확인":
                    checkAnswer();
                    break;
                case "다시":
                    startNewGame();
                    break;
            }
        }
    }

    private void checkAnswer() {
        if (userChoice == null) {
            messageLabel.setText("홀이냐 짝이냐 먼저 선택!");
            return;
        }

        numberLabel.setText(Integer.toString(hiddenNumber));

        String actualAnswer = (hiddenNumber % 2 == 0) ? "짝" : "홀";

        if (userChoice.equals(actualAnswer)) {
            messageLabel.setText(actualAnswer + "! 맞았어요.");
        } 
        
        else {
            messageLabel.setText(actualAnswer + "! 아쉽군요.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OpenChallenge10();
            }
        });
    }
}
