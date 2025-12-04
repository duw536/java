package Lib;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;

public class LibraryMainGUI extends JFrame {

    // 콤보박스 3개 선언
    JComboBox<String> comboBook;
    JComboBox<String> comboMember;
    JComboBox<String> comboLoan;

    public LibraryMainGUI() {
        setTitle("도서관 관리 시스템 - 메인");
        setSize(600, 200); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new GridLayout(1, 3, 20, 20)); // 1행 3열
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // -------------------------------------------------
        // 1. 도서 관리 패널
        // -------------------------------------------------
        JPanel pnlBook = new JPanel();
        pnlBook.setBorder(new TitledBorder("도서 관리"));
        // 메뉴 목록
        String[] bookMenu = {"--선택하세요--", "도서 등록", "도서 검색", "도서 정보 수정 및 삭제"};
        comboBook = new JComboBox<>(bookMenu);
        pnlBook.add(comboBook);
        
        // -------------------------------------------------
        // 2. 회원 관리 패널
        // -------------------------------------------------
        JPanel pnlMember = new JPanel();
        pnlMember.setBorder(new TitledBorder("회원 관리"));
        // 메뉴 목록
        String[] memberMenu = {"--선택하세요--", "회원 추가", "회원 목록 조회", "회원 대출 반납 현황 조회"};
        comboMember = new JComboBox<>(memberMenu);
        pnlMember.add(comboMember);

        // -------------------------------------------------
        // 3. 대출/반납 패널
        // -------------------------------------------------
        JPanel pnlLoan = new JPanel();
        pnlLoan.setBorder(new TitledBorder("대출 / 반납"));
        // 메뉴 목록
        String[] loanMenu = {"--선택하세요--", "도서 대출", "도서 반납"};
        comboLoan = new JComboBox<>(loanMenu);
        pnlLoan.add(comboLoan);

        // 패널 붙이기
        add(pnlBook);
        add(pnlMember);
        add(pnlLoan);

        // [중요] 이벤트 리스너 등록 메서드 호출
        addListeners();

        setVisible(true);
    }

    // 콤보박스 선택 시 실행될 기능 연결
    private void addListeners() {
        
        // 1. 도서 관리 이벤트
        comboBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboBook.getSelectedItem();
                
                if (selected.equals("도서 등록")) {
                    new BookInsertGUI(); // 도서 등록 창 열기
                } else if (selected.equals("도서 검색")) {
                    new BookSearchGUI(); // 도서 검색 창 열기
                } else if (selected.equals("도서 정보 수정 및 삭제")) {
                    new BookEditGUI();   // 수정/삭제 창 열기
                }
                
                comboBook.setSelectedIndex(0); // 다시 '--선택하세요--'로 돌려놓기
            }
        });


     // 2. 회원 관리 이벤트
        comboMember.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) comboMember.getSelectedItem();
                
                if (selected.equals("--선택하세요--")) return; 

                if (selected.equals("회원 추가")) {
                    new MemberInsertGUI(); 
                    
                } else if (selected.equals("회원 목록 조회")) {
                     new MemberListGUI();
                     
                } else if (selected.equals("회원 대출 반납 현황 조회")) {
                     new LoanStatusGUI();
                }
                
                comboMember.setSelectedIndex(0); 
            }
        });

        // 3. 대출/반납 이벤트
     comboLoan.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
             String selected = (String) comboLoan.getSelectedItem();
             
             if (selected.equals("--선택하세요--")) return;

             if (selected.equals("도서 대출")) {
                 new LoanGUI();
             } else if (selected.equals("도서 반납")) {
                  new ReturnGUI();
             }
             
             comboLoan.setSelectedIndex(0);
         }
     });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {}

        new LibraryMainGUI();
    }
}

//books 테이블
//book_id int (pk ai)
//title varchar 
//author varchar
//publisher varchar
//category varchar
//total_stock int 
//avaliable_stock int 
//
//members 테이블
//member_id (pk ai)
//name varchar 
//login_id varchar (uq)
//password varchar
//phone varchar
//status varchar
//
//loans 테이블
//loan_id int (pk)
//member_id int (fk)
//book_id int (fk)
//loan_date date
//return_date date 

