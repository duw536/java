package main;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private MainGUI mainFrame;

    public DashboardPanel(MainGUI mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        // 1. 환영 문구
        JLabel welcomeLabel = new JLabel(
            mainFrame.session.getName() + "님 (" + mainFrame.session.getRole() + ") 환영합니다."
        );
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        welcomeLabel.setBounds(0, 80, 900, 40);
        add(welcomeLabel);

        // 2. 버튼 패널
        JPanel btnPanel = new JPanel(new GridLayout(0, 1, 0, 15));
        btnPanel.setBounds(300, 160, 300, 260); 
        add(btnPanel);

        String role = mainFrame.session.getRole();

        // [1] 계정 관리 (ADMIN만)
        if ("ADMIN".equals(role)) {
            JButton btnAccount = new JButton("계정 관리 (업로더 관리)");
            btnAccount.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            btnAccount.addActionListener(e -> {
                AccountManagePanel panel = (AccountManagePanel) getPanelByName("ACCOUNT_MANAGE");
                if(panel != null) panel.loadData();
                mainFrame.showCard("ACCOUNT_MANAGE");
            });
            btnPanel.add(btnAccount);
        }

        // [2] 프로젝트 관리 (ADMIN, UPLOADER)
        if ("ADMIN".equals(role) || "UPLOADER".equals(role)) {
            JButton btnProject = new JButton("프로젝트 관리 (생성/업로드)");
            btnProject.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            btnProject.addActionListener(e -> {
                ProjectManagePanel panel = (ProjectManagePanel) getPanelByName("PROJECT_MANAGE");
                if(panel != null) panel.loadData();
                mainFrame.showCard("PROJECT_MANAGE");
            });
            btnPanel.add(btnProject);
        }

        // [3] 프로젝트 조회 (모든 유저)
        JButton btnView = new JButton("프로젝트 조회 (자료 검색/다운)");
        btnView.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        // ★ 조회 화면으로 연결
        btnView.addActionListener(e -> {
            ProjectViewPanel panel = (ProjectViewPanel) getPanelByName("PROJECT_VIEW");
            if(panel != null) panel.loadData(); // 최신 목록 갱신
            mainFrame.showCard("PROJECT_VIEW");
        });
        btnPanel.add(btnView);


        // [4] 로그아웃
        JButton btnLogout = new JButton("로그아웃");
        btnLogout.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        btnLogout.setBackground(new Color(255, 200, 200));
        btnLogout.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(mainFrame, "로그아웃 하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                new LoginGUI();
                mainFrame.dispose();
            }
        });
        btnPanel.add(btnLogout);
    }
    
    // 패널 찾기 헬퍼 메서드
    private Component getPanelByName(String name) {
        for (Component comp : mainFrame.mainPanel.getComponents()) {
            if (name.equals("ACCOUNT_MANAGE") && comp instanceof AccountManagePanel) return comp;
            if (name.equals("PROJECT_MANAGE") && comp instanceof ProjectManagePanel) return comp;
            // ★ 조회 패널 찾기 추가
            if (name.equals("PROJECT_VIEW") && comp instanceof ProjectViewPanel) return comp;
        }
        return null;
    }
}