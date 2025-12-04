package Book;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class HomePanel extends JPanel {

    LibraryMainGUI mainFrame;
    JComboBox<String> comboBook, comboMember, comboLoan;

    public HomePanel(LibraryMainGUI mainFrame, MemberDTO user) {
        this.mainFrame = mainFrame;
        boolean isAdmin = user.getRole().equals("ADMIN");

        setLayout(new BorderLayout());

        // 상단: 사용자 정보 및 로그아웃
        JPanel pnlTop = new JPanel(new BorderLayout());
        pnlTop.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        pnlTop.setBackground(Color.LIGHT_GRAY);
        pnlTop.add(new JLabel("접속자: " + user.getName() + " (" + user.getRole() + ")"), BorderLayout.WEST);
        
        JButton btnLogout = new JButton("로그아웃");
        pnlTop.add(btnLogout, BorderLayout.EAST);
        add(pnlTop, BorderLayout.NORTH);

        // 중앙: 메뉴 선택
        JPanel pnlCenter = new JPanel();
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlCenter.setLayout(new GridLayout(1, isAdmin ? 3 : 2, 20, 20));

        // 1. 도서 관리
        JPanel pnlBook = new JPanel(); pnlBook.setBorder(new TitledBorder("도서 관리"));
        comboBook = new JComboBox<>();
        comboBook.addItem("--선택하세요--");
        comboBook.addItem("도서 검색");
        if(isAdmin) { comboBook.addItem("도서 등록"); comboBook.addItem("도서 정보 수정 및 삭제"); }
        pnlBook.add(comboBook);
        pnlCenter.add(pnlBook);

        // 2. 회원 관리 (관리자 전용)
        if(isAdmin) {
            JPanel pnlMember = new JPanel(); pnlMember.setBorder(new TitledBorder("회원 관리"));
            comboMember = new JComboBox<>();
            comboMember.addItem("--선택하세요--");
            comboMember.addItem("회원 추가"); comboMember.addItem("회원 목록 조회"); comboMember.addItem("회원 대출 반납 현황 조회");
            pnlMember.add(comboMember);
            pnlCenter.add(pnlMember);
        }

        // 3. 대출/반납
        JPanel pnlLoan = new JPanel(); pnlLoan.setBorder(new TitledBorder("대출 / 반납"));
        comboLoan = new JComboBox<>(new String[]{"--선택하세요--", "도서 대출", "도서 반납"});
        pnlLoan.add(comboLoan);
        pnlCenter.add(pnlLoan);

        add(pnlCenter, BorderLayout.CENTER);

        // --- 이벤트 ---
        
        // [수정됨] 로그아웃 버튼 클릭 시 -> 로그인 화면으로 이동
        btnLogout.addActionListener(e -> {
            mainFrame.dispose(); // 현재 메인 화면 끄기
            new LoginGUI();      // 로그인 화면 새로 켜기
        });

        // 콤보박스 선택 시 화면 전환 (showCard 사용)
        comboBook.addActionListener(e -> {
            String sel = (String)comboBook.getSelectedItem();
            if(sel.equals("도서 등록")) mainFrame.showCard("BOOK_INSERT");
            else if(sel.equals("도서 검색")) mainFrame.showCard("BOOK_SEARCH");
            else if(sel.equals("도서 정보 수정 및 삭제")) mainFrame.showCard("BOOK_EDIT");
            comboBook.setSelectedIndex(0);
        });

        if(isAdmin) {
            comboMember.addActionListener(e -> {
                String sel = (String)comboMember.getSelectedItem();
                if(sel.equals("회원 추가")) mainFrame.showCard("MEMBER_INSERT");
                else if(sel.equals("회원 목록 조회")) mainFrame.showCard("MEMBER_LIST");
                else if(sel.equals("회원 대출 반납 현황 조회")) mainFrame.showCard("LOAN_STATUS");
                comboMember.setSelectedIndex(0);
            });
        }

        comboLoan.addActionListener(e -> {
            String sel = (String)comboLoan.getSelectedItem();
            if(sel.equals("도서 대출")) mainFrame.showCard("LOAN");
            else if(sel.equals("도서 반납")) mainFrame.showCard("RETURN");
            comboLoan.setSelectedIndex(0);
        });
    }
}