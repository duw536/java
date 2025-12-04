package Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginGUI extends JFrame {

    JTextField tfId = new JTextField();
    JPasswordField pfPw = new JPasswordField();
    JButton btnLogin = new JButton("로그인");
    JButton btnJoin = new JButton("회원가입");

    public LoginGUI() {
        setTitle("도서관 시스템 - 로그인");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 입력 패널
        JPanel pnlInput = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlInput.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlInput.add(new JLabel("아이디:"));
        pnlInput.add(tfId);
        pnlInput.add(new JLabel("비밀번호:"));
        pnlInput.add(pfPw);

        add(pnlInput, BorderLayout.CENTER);

        // 버튼 패널
        JPanel pnlBtn = new JPanel();
        pnlBtn.add(btnLogin);
        pnlBtn.add(btnJoin);
        add(pnlBtn, BorderLayout.SOUTH);

        // 이벤트 연결
        btnLogin.addActionListener(e -> loginAction());
        tfId.addActionListener(e -> loginAction());     
        pfPw.addActionListener(e -> loginAction());     
        
        // [수정됨] MemberInsertPanel 대신 SignUpGUI를 실행!
        btnJoin.addActionListener(e -> new SignUpGUI()); 

        setVisible(true);
    }

    private void loginAction() {
        String id = tfId.getText();
        String pw = new String(pfPw.getPassword());

        if (id.isEmpty() || pw.isEmpty()) {
            JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요.");
            return;
        }

        MemberDAO dao = new MemberDAO();
        MemberDTO member = dao.login(id, pw);

        if (member != null) {
            JOptionPane.showMessageDialog(null, member.getName() + "님 환영합니다! (" + member.getRole() + ")");
            dispose(); // 로그인 창 닫기
            
            // 메인 화면 열기
            new LibraryMainGUI(member); 
        } else {
            JOptionPane.showMessageDialog(null, "로그인 실패: 아이디나 비번을 확인하세요.");
        }
    }

    public static void main(String[] args) {
        new LoginGUI(); 
    }
}