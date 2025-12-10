package main;

import javax.swing.*;
import java.awt.*;
import dto.Account;

public class MainGUI extends JFrame {

    public CardLayout cardLayout;
    public JPanel mainPanel;
    public Account session; // 로그인 정보

    public MainGUI(Account session) {
        this.session = session;

        // 1. 창 기본 설정
        setTitle("연구 자료 관리 시스템");
        setSize(900, 600); // 창 크기 넉넉하게
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙

        // 2. ★ 핵심: 레이아웃과 패널을 가장 먼저 생성해야 함! ★
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 3. 각 화면(패널) 생성 및 등록
        
        // (A) 대시보드 (메인 메뉴)
        DashboardPanel dashboard = new DashboardPanel(this);
        mainPanel.add(dashboard, "DASHBOARD");

        // (B) 계정 관리 (어드민용)
        AccountManagePanel accountManage = new AccountManagePanel(this);
        mainPanel.add(accountManage, "ACCOUNT_MANAGE");

        // (C) 프로젝트 관리 (생성/업로드/삭제)
        ProjectManagePanel projectManage = new ProjectManagePanel(this);
        mainPanel.add(projectManage, "PROJECT_MANAGE");
        
        // (D) 프로젝트 조회 (검색/다운로드) - ★ 새로 추가됨
        ProjectViewPanel projectView = new ProjectViewPanel(this);
        mainPanel.add(projectView, "PROJECT_VIEW");

        // 4. 메인 패널을 프레임에 부착
        add(mainPanel);
        setVisible(true);
    }
    
    // 화면 전환 메서드
    public void showCard(String cardName) {
        cardLayout.show(mainPanel, cardName);
    }
}