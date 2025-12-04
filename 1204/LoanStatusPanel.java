package Book;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LoanStatusPanel extends JPanel {
    LibraryMainGUI mainFrame;
    DefaultTableModel model = new DefaultTableModel(new String[]{"번호","회원","책","대출일","반납일","상태"}, 0);

    public LoanStatusPanel(LibraryMainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        
        JPanel bot = new JPanel();
        JButton btnLoad = new JButton("새로고침"), btnBack = new JButton("뒤로가기");
        bot.add(btnLoad); bot.add(btnBack); add(bot, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> mainFrame.showCard("HOME"));
        btnLoad.addActionListener(e -> load());
    }
    void load() {
        model.setRowCount(0);
        for(LoanDTO l : new LoanDAO().getLoanHistory()) {
            String status = (l.getReturnDate()==null)?"대출중":"반납완료";
            model.addRow(new Object[]{l.getLoanId(), l.getMemberName(), l.getBookTitle(), l.getLoanDate(), l.getReturnDate(), status});
        }
    }
}