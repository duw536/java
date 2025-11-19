package PracticalExercise6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PracticalExercise6 extends JFrame {
    private JLabel resultLabel;

    public PracticalExercise6() {
        setTitle("다이얼로그 만들기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JButton btn = new JButton("calculate");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CalcDialog dialog = new CalcDialog(PracticalExercise6.this);
                dialog.setVisible(true);
            }
        });

        resultLabel = new JLabel("계산 결과 출력");
        resultLabel.setOpaque(true);
        resultLabel.setBackground(Color.GREEN);

        c.add(btn);
        c.add(resultLabel);

        setSize(300, 200);
        setVisible(true);
    }

    public void updateResult(int result) {
        resultLabel.setText(Integer.toString(result));
    }

    class CalcDialog extends JDialog {
        private JTextField tf1 = new JTextField(10);
        private JTextField tf2 = new JTextField(10);
        private JButton addBtn = new JButton("Add");
        private PracticalExercise6 owner;

        public CalcDialog(PracticalExercise6 owner) {
            super(owner, "Calculation Dialog", true);
            this.owner = owner;
            setLayout(new FlowLayout());

            add(new JLabel("두 수를 더합니다."));
            add(tf1);
            add(tf2);
            add(addBtn);

            addBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        int n1 = Integer.parseInt(tf1.getText());
                        int n2 = Integer.parseInt(tf2.getText());
                        int sum = n1 + n2;

                        owner.updateResult(sum);

                        setVisible(false); 
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "정수만 입력하세요.");
                    }
                }
            });

            setSize(200, 200);
        }
    }

    public static void main(String[] args) {
        new PracticalExercise6();
    }
}
