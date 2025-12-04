package Book;
import javax.swing.*;
import java.awt.*;

public class ReturnPanel extends JPanel {
    LibraryMainGUI mainFrame;
    JTextField tfBook = new JTextField(), tfMem = new JTextField();

    public ReturnPanel(LibraryMainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel cen = new JPanel(new GridLayout(2,2,10,10));
        cen.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        cen.add(new JLabel("책 ID:")); cen.add(tfBook);
        cen.add(new JLabel("회원 ID:")); cen.add(tfMem);
        add(cen, BorderLayout.CENTER);

        JPanel bot = new JPanel();
        JButton btnDo = new JButton("반납"), btnBack = new JButton("뒤로가기");
        bot.add(btnDo); bot.add(btnBack); add(bot, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> mainFrame.showCard("HOME"));
        btnDo.addActionListener(e -> {
            try {
                int res = new LoanDAO().returnBook(Integer.parseInt(tfBook.getText()), Integer.parseInt(tfMem.getText()));
                if(res==1) JOptionPane.showMessageDialog(this, "반납성공");
                else JOptionPane.showMessageDialog(this, "실패(대출기록 없음)");
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "ID는 숫자만"); }
        });
    }
}