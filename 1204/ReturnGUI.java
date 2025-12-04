package Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ReturnGUI extends JFrame {

    JTextField tfBookId = new JTextField();
    JTextField tfMemberId = new JTextField();
    
    JButton btnReturn = new JButton("반납 실행");
    JButton btnBack = new JButton("뒤로가기");

    public ReturnGUI() {
        setTitle("도서 반납");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 중앙 입력 패널
        JPanel pnlCenter = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        pnlCenter.add(new JLabel("책 ID (번호):"));
        pnlCenter.add(tfBookId);
        
        pnlCenter.add(new JLabel("회원 ID (번호):"));
        pnlCenter.add(tfMemberId);
        
        add(pnlCenter, BorderLayout.CENTER);

        // 하단 버튼 패널
        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnReturn);
        pnlSouth.add(btnBack);
        add(pnlSouth, BorderLayout.SOUTH);

        // 이벤트 연결
        btnReturn.addActionListener(e -> returnAction());
        btnBack.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void returnAction() {
        try {
            String bookIdStr = tfBookId.getText();
            String memberIdStr = tfMemberId.getText();

            if (bookIdStr.isEmpty() || memberIdStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "책 ID와 회원 ID를 입력하세요.");
                return;
            }

            int bookId = Integer.parseInt(bookIdStr);
            int memberId = Integer.parseInt(memberIdStr);

            // DAO 호출
            LoanDAO dao = new LoanDAO();
            int result = dao.returnBook(bookId, memberId);

            if (result == 1) {
                JOptionPane.showMessageDialog(null, "👍 반납 처리가 완료되었습니다!");
                tfBookId.setText("");
                tfMemberId.setText("");
            } else if (result == 0) {
                JOptionPane.showMessageDialog(null, "❌ 대출 중인 기록을 찾을 수 없습니다.\n(이미 반납했거나 정보를 잘못 입력했습니다.)");
            } else {
                JOptionPane.showMessageDialog(null, "❌ 시스템 에러가 발생했습니다.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "ID는 숫자만 입력하세요.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}