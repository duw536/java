package Lib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BookSearchGUI extends JFrame {

    JTextField tfSearch = new JTextField(20);
    JButton btnSearch = new JButton("검색");
    JButton btnBack = new JButton("뒤로가기");
    
    String[] colNames = {"ID", "제목", "저자", "출판사", "카테고리", "잔여재고"};
    DefaultTableModel model = new DefaultTableModel(colNames, 0) {
        @Override public boolean isCellEditable(int row, int col) { return false; }
    };
    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);

    public BookSearchGUI() {
        setTitle("도서 검색");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // [중요]
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 상단 검색
        JPanel pnlNorth = new JPanel();
        pnlNorth.add(new JLabel("검색어: "));
        pnlNorth.add(tfSearch);
        pnlNorth.add(btnSearch);
        add(pnlNorth, BorderLayout.NORTH);

        // 중앙 테이블
        add(scrollPane, BorderLayout.CENTER);

        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnBack);
        add(pnlSouth, BorderLayout.SOUTH);

        // --- 이벤트 ---
        btnSearch.addActionListener(e -> searchAction());
        tfSearch.addActionListener(e -> searchAction());
        btnBack.addActionListener(e -> dispose());

        setVisible(true);
    }

    public void searchAction() {
        String keyword = tfSearch.getText();
        BookDAO dao = new BookDAO();
        ArrayList<BookDTO> list = dao.searchBooks(keyword);
        model.setRowCount(0);
        for (BookDTO b : list) {
            Object[] row = { b.getBookId(), b.getTitle(), b.getAuthor(), b.getPublisher(), b.getCategory(), b.getAvailableStock() };
            model.addRow(row);
        }
        if(list.size() == 0) JOptionPane.showMessageDialog(null, "결과 없음");
    }
}