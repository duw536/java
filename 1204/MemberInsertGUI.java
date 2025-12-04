package Lib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemberInsertGUI extends JFrame {

    // 입력 컴포넌트
    JTextField tfName = new JTextField();
    JTextField tfLoginId = new JTextField();
    JPasswordField pfPassword = new JPasswordField(); // 비밀번호는 안 보이게
    JTextField tfPhone = new JTextField();

    JButton btnInsert = new JButton("회원 등록");
    JButton btnBack = new JButton("뒤로가기");

    public MemberInsertGUI() {
        setTitle("신규 회원 등록");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. 입력 패널 (중앙)
        JPanel pnlCenter = new JPanel(new GridLayout(4, 2, 10, 10));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCenter.add(new JLabel("이름:"));       pnlCenter.add(tfName);
        pnlCenter.add(new JLabel("아이디:"));     pnlCenter.add(tfLoginId);
        pnlCenter.add(new JLabel("비밀번호:"));   pnlCenter.add(pfPassword);
        pnlCenter.add(new JLabel("전화번호:"));   pnlCenter.add(tfPhone);

        add(pnlCenter, BorderLayout.CENTER);

        // 2. 버튼 패널 (하단)
        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnInsert);
        pnlSouth.add(btnBack);
        add(pnlSouth, BorderLayout.SOUTH);

        // --- 이벤트 연결 ---
        
        // 등록 버튼
        btnInsert.addActionListener(e -> insertMemberAction());

        // 뒤로가기 버튼
        btnBack.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void insertMemberAction() {
        String name = tfName.getText();
        String loginId = tfLoginId.getText();
        // JPasswordField는 getPassword()로 가져와서 String으로 변환해야 함
        String password = new String(pfPassword.getPassword());
        String phone = tfPhone.getText();

        // 유효성 검사
        if (name.isEmpty() || loginId.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "이름, 아이디, 비밀번호는 필수입니다!");
            return;
        }

        // DTO 생성 (member_id는 0, status는 "ACTIVE"로 고정)
        MemberDTO dto = new MemberDTO(0, name, loginId, password, phone, "ACTIVE");
        
        MemberDAO dao = new MemberDAO();
        int result = dao.insertMember(dto);

        if (result > 0) {
            JOptionPane.showMessageDialog(null, "🎉 회원가입 완료!");
            dispose();
        } else if (result == -1) {
            JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.\n다른 아이디를 사용해주세요.");
        } else {
            JOptionPane.showMessageDialog(null, "회원가입 실패 (DB 오류)");
        }
    }
}