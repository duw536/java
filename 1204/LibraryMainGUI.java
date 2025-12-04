package Book;

import javax.swing.*;
import java.awt.*;

public class LibraryMainGUI extends JFrame {

    public CardLayout cardLayout;
    public JPanel mainContainer;
    
    MemberDTO currentUser;

    public LibraryMainGUI(MemberDTO user) {
        this.currentUser = user;
        setTitle("도서관 관리 시스템 - 메인 (" + user.getName() + ")");
        setSize(900, 600); // 화면이 전환되므로 넉넉하게
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 1. 카드 레이아웃 설정
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);

        // 2. 모든 패널 생성 및 등록 (이름표 붙이기)
        // (this)를 넘기는 이유는 패널들이 메인 프레임에게 "화면 바꿔줘"라고 요청하기 위함입니다.
        
        // 홈 (메뉴)
        mainContainer.add(new HomePanel(this, user), "HOME");

        // 도서 관련 패널
        mainContainer.add(new BookInsertPanel(this), "BOOK_INSERT");
        mainContainer.add(new BookSearchPanel(this), "BOOK_SEARCH");
        mainContainer.add(new BookEditPanel(this), "BOOK_EDIT");

        // 회원 관련 패널
        mainContainer.add(new MemberInsertPanel(this), "MEMBER_INSERT");
        mainContainer.add(new MemberListPanel(this), "MEMBER_LIST");
        mainContainer.add(new LoanStatusPanel(this), "LOAN_STATUS");

        // 대출/반납 관련 패널
        mainContainer.add(new LoanPanel(this), "LOAN");
        mainContainer.add(new ReturnPanel(this), "RETURN");

        add(mainContainer);
        setVisible(true);
    }

    // 화면 전환 메서드
    public void showCard(String name) {
        cardLayout.show(mainContainer, name);
    }
}