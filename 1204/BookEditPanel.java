package Book;
import javax.swing.*;
import java.awt.*;

public class BookEditPanel extends JPanel {
    LibraryMainGUI mainFrame;
    JTextField tfSearch=new JTextField(10), tfTitle=new JTextField(), tfAuthor=new JTextField(), tfPub=new JTextField(), tfCat=new JTextField(), tfTot=new JTextField(), tfAvail=new JTextField();
    int curId = -1;

    public BookEditPanel(LibraryMainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel top = new JPanel(); top.add(new JLabel("제목검색:")); top.add(tfSearch);
        JButton btnSearch = new JButton("조회"); top.add(btnSearch);
        add(top, BorderLayout.NORTH);

        JPanel cen = new JPanel(new GridLayout(6,2,5,5));
        cen.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        cen.add(new JLabel("제목")); cen.add(tfTitle); cen.add(new JLabel("저자")); cen.add(tfAuthor);
        cen.add(new JLabel("출판사")); cen.add(tfPub); cen.add(new JLabel("카테고리")); cen.add(tfCat);
        cen.add(new JLabel("총재고")); cen.add(tfTot); cen.add(new JLabel("현재재고")); cen.add(tfAvail);
        add(cen, BorderLayout.CENTER);

        JPanel bot = new JPanel();
        JButton btnUp = new JButton("수정"), btnDel = new JButton("삭제"), btnBack = new JButton("뒤로가기");
        bot.add(btnUp); bot.add(btnDel); bot.add(btnBack); add(bot, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> mainFrame.showCard("HOME"));
        btnSearch.addActionListener(e -> {
            BookDTO b = new BookDAO().getBookByTitle(tfSearch.getText());
            if(b != null) { curId=b.getBookId(); tfTitle.setText(b.getTitle()); tfAuthor.setText(b.getAuthor()); tfPub.setText(b.getPublisher()); tfCat.setText(b.getCategory()); tfTot.setText(b.getTotalStock()+""); tfAvail.setText(b.getAvailableStock()+""); }
            else JOptionPane.showMessageDialog(this, "없음");
        });
        btnUp.addActionListener(e -> {
            if(curId!=-1) new BookDAO().updateBook(new BookDTO(curId, tfTitle.getText(), tfAuthor.getText(), tfPub.getText(), tfCat.getText(), Integer.parseInt(tfTot.getText()), Integer.parseInt(tfAvail.getText())));
            JOptionPane.showMessageDialog(this, "수정완료");
        });
        btnDel.addActionListener(e -> {
            if(curId!=-1) new BookDAO().deleteBook(curId);
            JOptionPane.showMessageDialog(this, "삭제완료"); tfTitle.setText(""); curId=-1;
        });
    }
}