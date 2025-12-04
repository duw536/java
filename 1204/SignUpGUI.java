package Book;

import javax.swing.*;
import java.awt.*;

public class SignUpGUI extends JFrame {

    JTextField tfName = new JTextField();
    JTextField tfId = new JTextField();
    JPasswordField pfPw = new JPasswordField();
    JTextField tfPhone = new JTextField();

    JButton btnJoin = new JButton("가입하기");
    JButton btnBack = new JButton("취소");

    public SignUpGUI() {
        setTitle("회원가입");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창만 닫기
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 입력 패널
        JPanel pnlCenter = new JPanel(new GridLayout(4, 2, 10, 10));
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCenter.add(new JLabel("이름:"));     pnlCenter.add(tfName);
        pnlCenter.add(new JLabel("아이디:"));   pnlCenter.add(tfId);
        pnlCenter.add(new JLabel("비밀번호:")); pnlCenter.add(pfPw);
        pnlCenter.add(new JLabel("전화번호:")); pnlCenter.add(tfPhone);

        add(pnlCenter, BorderLayout.CENTER);

        // 버튼 패널
        JPanel pnlSouth = new JPanel();
        pnlSouth.add(btnJoin);
        pnlSouth.add(btnBack);
        add(pnlSouth, BorderLayout.SOUTH);

        // --- 이벤트 연결 ---
        btnBack.addActionListener(e -> dispose()); // 창 닫기

        btnJoin.addActionListener(e -> {
            String name = tfName.getText();
            String id = tfId.getText();
            String pw = new String(pfPw.getPassword());
            String phone = tfPhone.getText();

            if(name.isEmpty() || id.isEmpty() || pw.isEmpty()) {
                JOptionPane.showMessageDialog(this, "필수 항목을 입력하세요.");
                return;
            }

            // 가입 시 기본 권한은 'USER'
            MemberDTO dto = new MemberDTO(0, name, id, pw, phone, "ACTIVE", "USER");
            MemberDAO dao = new MemberDAO();
            
            int result = dao.insertMember(dto);
            
            if (result > 0) {
                JOptionPane.showMessageDialog(this, "🎉 회원가입 성공! 로그인 해주세요.");
                dispose(); // 성공하면 창 닫기
            } else if (result == -1) {
                JOptionPane.showMessageDialog(this, "이미 사용 중인 아이디입니다.");
            } else {
                JOptionPane.showMessageDialog(this, "가입 실패 (시스템 오류)");
            }
        });

        setVisible(true);
    }
}