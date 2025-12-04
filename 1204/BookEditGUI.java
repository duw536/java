package Lib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookEditGUI extends JFrame {

    JTextField tfSearchTitle = new JTextField(15); 
    JButton btnSearch = new JButton("제목으로 조회");

    JTextField tfTitle = new JTextField();
    JTextField tfAuthor = new JTextField();
    JTextField tfPublisher = new JTextField();
    JTextField tfCategory = new JTextField();
    JTextField tfTotalStock = new JTextField();
    JTextField tfAvailStock = new JTextField();

    JButton btnUpdate = new JButton("수정");
    JButton btnDelete = new JButton("삭제");
    JButton btnBack = new JButton("뒤로가기");

    int currentBookId = -1; 

    public BookEditGUI() {
        setTitle("도서 수정 및 삭제");
        setSize(400, 480); // 버튼 추가로 세로 약간 늘림
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // [중요]
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 상단 검색
        JPanel pnlNorth = new JPanel();
        pnlNorth.add(new JLabel("책 제목:"));
        pnlNorth.add(tfSearchTitle);
        pnlNorth.add(btnSearch);
        add(pnlNorth, BorderLayout.NORTH);

        // 중앙 입력
        JPanel pnlCenter = new JPanel(new GridLayout(6, 2, 10, 10));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlCenter.add(new JLabel("도서 제목:"));     pnlCenter.add(tfTitle);
        pnlCenter.add(new JLabel("저자:"));          pnlCenter.add(tfAuthor);
        pnlCenter.add(new JLabel("출판사:"));        pnlCenter.add(tfPublisher);
        pnlCenter.add(new JLabel("카테고리:"));      pnlCenter.add(tfCategory);
        pnlCenter.add(new JLabel("총 재고:"));       pnlCenter.add(tfTotalStock);
        pnlCenter.add(new JLabel("현재 재고:"));     pnlCenter.add(tfAvailStock);
        add(pnlCenter, BorderLayout.CENTER);

        // 하단 버튼들
        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnUpdate);
        pnlSouth.add(btnDelete);
        pnlSouth.add(btnBack);
        add(pnlSouth, BorderLayout.SOUTH);

        // --- 이벤트 ---
        btnSearch.addActionListener(e -> searchBookAction());
        tfSearchTitle.addActionListener(e -> searchBookAction());
        
        btnUpdate.addActionListener(e -> updateBookAction());
        btnDelete.addActionListener(e -> deleteBookAction());
        btnBack.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void searchBookAction() {
         String title = tfSearchTitle.getText().trim();
         if (title.isEmpty()) return;
         BookDAO dao = new BookDAO();
         BookDTO book = dao.getBookByTitle(title);
         if (book != null) {
             currentBookId = book.getBookId();
             tfTitle.setText(book.getTitle());
             tfAuthor.setText(book.getAuthor());
             tfPublisher.setText(book.getPublisher());
             tfCategory.setText(book.getCategory());
             tfTotalStock.setText(String.valueOf(book.getTotalStock()));
             tfAvailStock.setText(String.valueOf(book.getAvailableStock()));
         } else {
             JOptionPane.showMessageDialog(null, "책이 없습니다.");
             currentBookId = -1;
         }
    }
    
    private void updateBookAction() {
        if(currentBookId == -1) return;
        try {
            BookDTO book = new BookDTO(currentBookId, tfTitle.getText(), tfAuthor.getText(), tfPublisher.getText(), tfCategory.getText(), Integer.parseInt(tfTotalStock.getText()), Integer.parseInt(tfAvailStock.getText()));
            if(new BookDAO().updateBook(book) > 0) JOptionPane.showMessageDialog(null, "수정 완료");
        } catch(Exception e) {}
    }
    
    private void deleteBookAction() {
        if(currentBookId == -1) return;
        if(JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "경고", 0) == 0) {
            if(new BookDAO().deleteBook(currentBookId) > 0) {
                JOptionPane.showMessageDialog(null, "삭제 완료");
                tfTitle.setText(""); currentBookId = -1;
            }
        }
    }
}