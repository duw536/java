package Lib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookInsertGUI extends JFrame {

    JTextField tfTitle = new JTextField();
    JTextField tfAuthor = new JTextField();
    JTextField tfPublisher = new JTextField();
    JTextField tfCategory = new JTextField();
    JTextField tfStock = new JTextField();

    JButton btnInsert = new JButton("도서 등록");
    JButton btnBack = new JButton("뒤로가기");

    public BookInsertGUI() {
        setTitle("도서 등록");
        setSize(400, 350); // 버튼 공간 때문에 세로 조금 늘림
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout()); // 전체 레이아웃 변경

        // 1. 입력 양식 패널 (중앙)
        JPanel pnlCenter = new JPanel(new GridLayout(5, 2, 10, 10));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCenter.add(new JLabel("도서 제목:"));     pnlCenter.add(tfTitle);
        pnlCenter.add(new JLabel("저자:"));          pnlCenter.add(tfAuthor);
        pnlCenter.add(new JLabel("출판사:"));        pnlCenter.add(tfPublisher);
        pnlCenter.add(new JLabel("카테고리:"));      pnlCenter.add(tfCategory);
        pnlCenter.add(new JLabel("재고 수량:"));     pnlCenter.add(tfStock);

        add(pnlCenter, BorderLayout.CENTER);

        // 2. 버튼 패널 (하단)
        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnInsert);
        pnlSouth.add(btnBack);
        
        add(pnlSouth, BorderLayout.SOUTH);

        // --- 이벤트 ---
        
        // 등록 버튼
        btnInsert.addActionListener(e -> insertBookAction());
        btnBack.addActionListener(e -> dispose());

        setVisible(true);
    }

    public void insertBookAction() {
        try {
            String title = tfTitle.getText();
            String author = tfAuthor.getText();
            String publisher = tfPublisher.getText();
            String category = tfCategory.getText();
            String stockStr = tfStock.getText();

            if (title.isEmpty() || stockStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "필수 항목 누락!");
                return;
            }

            int stock = Integer.parseInt(stockStr);
            BookDTO dto = new BookDTO(0, title, author, publisher, category, stock, stock);
            BookDAO dao = new BookDAO();
            
            if (dao.insertBook(dto) > 0) {
                JOptionPane.showMessageDialog(null, "등록 성공!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "등록 실패");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "재고는 숫자만 입력하세요.");
        }
    }
}