package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import dao.AccountDAO;
import dto.Account;

public class LoginGUI extends JFrame {

    private AccountDAO accountDAO = new AccountDAO();

    // 입력 필드 전역 변수 선언
    private JTextField idField;
    private JPasswordField pwField;

    public LoginGUI() {
        // 1. 기본 창 설정
        setTitle("연구 자료 업로드 및 관리 시스템");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙 배치
        setLayout(null); // 좌표로 직접 배치

        // 2. 타이틀 라벨
        JLabel titleLabel = new JLabel("Research System");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 28));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(50, 60, 300, 50);
        add(titleLabel);

        // 3. 아이디 입력
        JLabel idLabel = new JLabel("ID");
        idLabel.setBounds(60, 150, 50, 30);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(100, 150, 200, 30);
        add(idField);

        // 4. 비밀번호 입력
        JLabel pwLabel = new JLabel("PW");
        pwLabel.setBounds(60, 200, 50, 30);
        add(pwLabel);

        pwField = new JPasswordField();
        pwField.setBounds(100, 200, 200, 30);
        add(pwField);

        // 5. 로그인 버튼
        JButton loginBtn = new JButton("로그인");
        loginBtn.setBounds(60, 260, 240, 45);
        loginBtn.setBackground(new Color(100, 150, 255)); // 파란색 계열
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        add(loginBtn);

        // 6. 게스트 입장 버튼 (추가됨)
        JButton guestBtn = new JButton("게스트(Guest)로 접속하기");
        guestBtn.setBounds(60, 320, 240, 35);
        guestBtn.setBackground(new Color(230, 230, 230)); // 회색 계열
        add(guestBtn);


        // --- [이벤트 리스너] 버튼 기능 구현 ---

        // A. 로그인 버튼 클릭 시
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputId = idField.getText();
                String inputPw = new String(pwField.getPassword());

                // 로그인 시도
                tryLogin(inputId, inputPw);
            }
        });

        // B. 게스트 버튼 클릭 시
        guestBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 미리 약속된 게스트 계정으로 로그인 시도
                tryLogin("guest", "1234");
            }
        });

        // 엔터키 치면 로그인 되게 하기 (편의 기능)
        getRootPane().setDefaultButton(loginBtn);

        setVisible(true);
    }

    // 로그인 로직 (공통 사용)
    private void tryLogin(String id, String pw) {
        if (id.isEmpty() || pw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "아이디와 비밀번호를 입력하세요.");
            return;
        }

        Account user = accountDAO.login(id, pw);

        if (user != null) {
            JOptionPane.showMessageDialog(this, 
                user.getName() + "님 환영합니다! (" + user.getRole() + ")");
            
//            메인 화면 열기
            new MainGUI(user);
             dispose(); // 로그인 창 닫기
            
        } else {
            JOptionPane.showMessageDialog(this, "로그인 실패: 아이디나 비밀번호를 확인하세요.");
        }
    }

    public static void main(String[] args) {
        // 실행
        new LoginGUI();
    }
}