package Lib;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class LoanStatusGUI extends JFrame {

    String[] colNames = {"번호", "회원명(ID)", "도서명(ID)", "대출일", "반납일", "상태"};
    
    DefaultTableModel model = new DefaultTableModel(colNames, 0) {
        @Override public boolean isCellEditable(int row, int col) { return false; }
    };
    
    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);
    JButton btnBack = new JButton("뒤로가기");

    public LoanStatusGUI() {
        setTitle("회원 대출 및 반납 현황");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);

        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnBack);
        add(pnlSouth, BorderLayout.SOUTH);

        btnBack.addActionListener(e -> dispose());

        // 데이터 불러오기
        loadData();

        setVisible(true);
    }

    private void loadData() {
        LoanDAO dao = new LoanDAO();
        ArrayList<LoanDTO> list = dao.getLoanHistory();

        model.setRowCount(0); // 초기화

        for (LoanDTO dto : list) {
            // 반납일이 없으면(null) "대출중", 있으면 "반납완료"
            String status = (dto.getReturnDate() == null) ? "🔵 대출중" : "⚪ 반납완료";
            String returnDateStr = (dto.getReturnDate() == null) ? "-" : dto.getReturnDate().toString();

            Object[] row = {
                dto.getLoanId(),
                dto.getMemberName() + " (" + dto.getMemberId() + ")",
                dto.getBookTitle() + " (" + dto.getBookId() + ")",
                dto.getLoanDate(),
                returnDateStr,
                status
            };
            model.addRow(row);
        }
        
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(null, "대출 이력이 없습니다.");
        }
    }
}