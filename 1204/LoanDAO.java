package Lib;

import java.sql.*;
import java.util.ArrayList;

public class LoanDAO {

    private final String URL = "jdbc:mysql://localhost:3306/Lms";
    private final String ID = "root";
    private final String PW = "1234";

    // DB 연결
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, ID, PW);
    }

    // 자원 해제
    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {}
    }

    // ---------------------------------------------------------
    // 도서 대출 처리 (트랜잭션 적용)
    // 성공 시 1, 재고 부족 시 0, 에러 시 -1 반환
    // ---------------------------------------------------------
    public int loanBook(int bookId, int memberId) {
        Connection conn = null;
        PreparedStatement pstmtCheck = null;
        PreparedStatement pstmtInsert = null;
        PreparedStatement pstmtUpdate = null;
        ResultSet rs = null;
        
        int result = 0;

        try {
            conn = getConnection();
            
            // [중요] 트랜잭션 시작 (자동 커밋 끄기)
            conn.setAutoCommit(false); 

            // 1. 재고 확인 (available_stock이 0보다 큰지)
            // 오타난 컬럼명(avaliable_stock) 유지
            String checkSql = "SELECT avaliable_stock FROM Books WHERE book_id = ?";
            pstmtCheck = conn.prepareStatement(checkSql);
            pstmtCheck.setInt(1, bookId);
            rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                int stock = rs.getInt(1);
                if (stock <= 0) {
                    return 0; // 재고 없음
                }
            } else {
                return -1; // 책 ID 자체가 없음
            }

            // 2. 대출 기록 생성 (Loans 테이블 INSERT)
            String insertSql = "INSERT INTO Loans (member_id, book_id, loan_date) VALUES (?, ?, NOW())";
            pstmtInsert = conn.prepareStatement(insertSql);
            pstmtInsert.setInt(1, memberId);
            pstmtInsert.setInt(2, bookId);
            pstmtInsert.executeUpdate();

            // 3. 책 재고 감소 (Books 테이블 UPDATE)
            String updateSql = "UPDATE Books SET avaliable_stock = avaliable_stock - 1 WHERE book_id = ?";
            pstmtUpdate = conn.prepareStatement(updateSql);
            pstmtUpdate.setInt(1, bookId);
            pstmtUpdate.executeUpdate();

            // [중요] 모든 과정이 성공했으므로 커밋(저장)
            conn.commit(); 
            result = 1;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                // 에러 발생 시 롤백(취소)
                if (conn != null) conn.rollback(); 
            } catch (SQLException se) {}
            result = -1; // 에러 발생
        } finally {
            // 자원 해제 (순서대로 닫기 어렵다면 각각 try-catch하거나 헬퍼 메서드 사용)
            try {
                if (rs != null) rs.close();
                if (pstmtCheck != null) pstmtCheck.close();
                if (pstmtInsert != null) pstmtInsert.close();
                if (pstmtUpdate != null) pstmtUpdate.close();
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
        
        return result;
    }
    
 // ---------------------------------------------------------
    // 도서 반납 처리
    // 성공 시 1, 대출 내역 없음 0, 에러 -1
    // ---------------------------------------------------------
    public int returnBook(int bookId, int memberId) {
        Connection conn = null;
        PreparedStatement pstmtCheck = null;
        PreparedStatement pstmtUpdateLoan = null;
        PreparedStatement pstmtUpdateBook = null;
        ResultSet rs = null;
        
        int result = 0;

        try {
            conn = getConnection();
            conn.setAutoCommit(false); // 트랜잭션 시작

            // 1. 현재 대출 중인 기록이 있는지 확인 (반납일이 NULL인 기록)
            String checkSql = "SELECT loan_id FROM Loans WHERE book_id=? AND member_id=? AND return_date IS NULL";
            pstmtCheck = conn.prepareStatement(checkSql);
            pstmtCheck.setInt(1, bookId);
            pstmtCheck.setInt(2, memberId);
            rs = pstmtCheck.executeQuery();

            if (rs.next()) {
                int loanId = rs.getInt("loan_id");

                // 2. 대출 테이블 업데이트 (반납일 = 현재시간)
                String updateLoanSql = "UPDATE Loans SET return_date = NOW() WHERE loan_id = ?";
                pstmtUpdateLoan = conn.prepareStatement(updateLoanSql);
                pstmtUpdateLoan.setInt(1, loanId);
                pstmtUpdateLoan.executeUpdate();

                // 3. 책 재고 증가 (+1)
                // 오타 컬럼명 avaliable_stock 유지
                String updateBookSql = "UPDATE Books SET avaliable_stock = avaliable_stock + 1 WHERE book_id = ?";
                pstmtUpdateBook = conn.prepareStatement(updateBookSql);
                pstmtUpdateBook.setInt(1, bookId);
                pstmtUpdateBook.executeUpdate();

                conn.commit(); // 성공 시 커밋
                result = 1; // 성공
            } else {
                result = 0; // 빌린 기록이 없음 (이미 반납했거나 안 빌림)
            }

        } catch (Exception e) {
            e.printStackTrace();
            try { if (conn != null) conn.rollback(); } catch (SQLException se) {}
            result = -1; // 에러
        } finally {
            // 자원 해제 (null 체크 포함)
            try {
                if (rs != null) rs.close();
                if (pstmtCheck != null) pstmtCheck.close();
                if (pstmtUpdateLoan != null) pstmtUpdateLoan.close();
                if (pstmtUpdateBook != null) pstmtUpdateBook.close();
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
        
        return result;
    }
    
 // LoanDAO.java 맨 아래에 import java.util.ArrayList; 확인 필요

    // ---------------------------------------------------------
    // [추가] 전체 대출/반납 이력 조회 (JOIN 사용)
    // ---------------------------------------------------------
    public ArrayList<LoanDTO> getLoanHistory() {
        ArrayList<LoanDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // [핵심] 3개의 테이블을 연결하는 쿼리
        // l: Loan, m: Member, b: Book
        String sql = "SELECT l.loan_id, l.member_id, l.book_id, l.loan_date, l.return_date, "
                   + "       m.name AS member_name, b.title AS book_title "
                   + "FROM Loans l "
                   + "JOIN Members m ON l.member_id = m.member_id "
                   + "JOIN Books b ON l.book_id = b.book_id "
                   + "ORDER BY l.loan_id DESC"; // 최신순 정렬

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                LoanDTO dto = new LoanDTO();
                dto.setLoanId(rs.getInt("loan_id"));
                dto.setMemberId(rs.getInt("member_id"));
                dto.setBookId(rs.getInt("book_id"));
                dto.setLoanDate(rs.getDate("loan_date"));
                dto.setReturnDate(rs.getDate("return_date"));
                
                // JOIN으로 가져온 이름과 제목 담기
                dto.setMemberName(rs.getString("member_name"));
                dto.setBookTitle(rs.getString("book_title"));

                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return list;
    }
}