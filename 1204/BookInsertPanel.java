package Book;
import javax.swing.*;
import java.awt.*;

public class BookInsertPanel extends JPanel {
    LibraryMainGUI mainFrame;
    JTextField tfTitle=new JTextField(), tfAuthor=new JTextField(), tfPub=new JTextField(), tfCat=new JTextField(), tfStock=new JTextField();
    
    public BookInsertPanel(LibraryMainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel pnl = new JPanel(new GridLayout(5,2,10,10));
        pnl.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
        pnl.add(new JLabel("제목:")); pnl.add(tfTitle);
        pnl.add(new JLabel("저자:")); pnl.add(tfAuthor);
        pnl.add(new JLabel("출판사:")); pnl.add(tfPub);
        pnl.add(new JLabel("카테고리:")); pnl.add(tfCat);
        pnl.add(new JLabel("수량:")); pnl.add(tfStock);
        add(pnl, BorderLayout.CENTER);

        JPanel btnPnl = new JPanel();
        JButton btnInsert = new JButton("등록"), btnBack = new JButton("뒤로가기");
        btnPnl.add(btnInsert); btnPnl.add(btnBack);
        add(btnPnl, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> mainFrame.showCard("HOME"));
        btnInsert.addActionListener(e -> {
            try {
                BookDTO dto = new BookDTO(0, tfTitle.getText(), tfAuthor.getText(), tfPub.getText(), tfCat.getText(), Integer.parseInt(tfStock.getText()), Integer.parseInt(tfStock.getText()));
                if(new BookDAO().insertBook(dto) > 0) {
                    JOptionPane.showMessageDialog(this, "등록 성공");
                    tfTitle.setText(""); tfAuthor.setText(""); tfPub.setText(""); tfCat.setText(""); tfStock.setText("");
                }
            } catch(Exception ex) { JOptionPane.showMessageDialog(this, "입력 오류"); }
        });
    }
}