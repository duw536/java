package Book;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class BookSearchPanel extends JPanel {
    LibraryMainGUI mainFrame;
    JTextField tfSearch = new JTextField(20);
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID","제목","저자","출판사","카테고리","재고"}, 0);
    
    public BookSearchPanel(LibraryMainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        JPanel top = new JPanel(); top.add(new JLabel("검색어:")); top.add(tfSearch);
        JButton btnSearch = new JButton("검색"); top.add(btnSearch);
        add(top, BorderLayout.NORTH);
        add(new JScrollPane(new JTable(model)), BorderLayout.CENTER);
        
        JButton btnBack = new JButton("뒤로가기");
        JPanel bot = new JPanel(); bot.add(btnBack); add(bot, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> mainFrame.showCard("HOME"));
        btnSearch.addActionListener(e -> search());
    }
    void search() {
        ArrayList<BookDTO> list = new BookDAO().searchBooks(tfSearch.getText());
        model.setRowCount(0);
        for(BookDTO b : list) model.addRow(new Object[]{b.getBookId(), b.getTitle(), b.getAuthor(), b.getPublisher(), b.getCategory(), b.getAvailableStock()});
    }
}