package Lib;

import java.sql.*;
import java.util.ArrayList; // 리스트 사용을 위한 import

public class BookDAO {
    
    // DB 연결 정보 (본인 환경에 맞게 수정)
    private final String URL = "jdbc:mysql://localhost:3306/Lms";
    private final String ID = "root";
    private final String PW = "1234";

    // =============================================================
    // 1. 도서 등록 (INSERT)
    // =============================================================
    public int insertBook(BookDTO book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "INSERT INTO Books "
                   + "(title, author, publisher, category, total_stock, avaliable_stock) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, book.getTitle());        
            pstmt.setString(2, book.getAuthor());       
            pstmt.setString(3, book.getPublisher());    
            pstmt.setString(4, book.getCategory());     
            pstmt.setInt(5, book.getTotalStock());      
            pstmt.setInt(6, book.getAvailableStock()); 

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
        return result;
    }

    // =============================================================
    // 2. 도서 검색 (키워드 검색 - 리스트 반환)
    // =============================================================
    public ArrayList<BookDTO> searchBooks(String keyword) {
        ArrayList<BookDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM Books WHERE title LIKE ? OR author LIKE ?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");

            rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(mapResultSetToDTO(rs)); // 결과 매핑 메서드 사용
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return list;
    }

    // =============================================================
    // 3. 제목으로 도서 1권 조회 (수정/삭제 화면용)
    // =============================================================
    public BookDTO getBookByTitle(String title) {
        BookDTO book = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        // 제목이 정확히 일치하는 책 찾기
        String sql = "SELECT * FROM Books WHERE title = ?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, title);
            
            rs = pstmt.executeQuery();

            if (rs.next()) {
                book = mapResultSetToDTO(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs);
        }
        return book;
    }

    // =============================================================
    // 4. 도서 정보 수정 (UPDATE - ID 기준)
    // =============================================================
    public int updateBook(BookDTO book) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        // ID를 조건으로 나머지 정보 업데이트
        String sql = "UPDATE Books SET title=?, author=?, publisher=?, "
                   + "category=?, total_stock=?, avaliable_stock=? WHERE book_id=?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getPublisher());
            pstmt.setString(4, book.getCategory());
            pstmt.setInt(5, book.getTotalStock());
            pstmt.setInt(6, book.getAvailableStock());
            pstmt.setInt(7, book.getBookId()); // WHERE book_id = ?

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
        return result;
    }

    // =============================================================
    // 5. 도서 삭제 (DELETE - ID 기준)
    // =============================================================
    public int deleteBook(int bookId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "DELETE FROM Books WHERE book_id = ?";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, bookId);

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
        return result;
    }

    // =============================================================
    // [공통 기능] DB 연결 및 자원 해제, 데이터 매핑
    // =============================================================
    
    // DB 연결 (코드 중복 제거)
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, ID, PW);
    }

    // 자원 해제 (코드 중복 제거)
    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ResultSet -> DTO 변환 (코드 중복 제거)
    private BookDTO mapResultSetToDTO(ResultSet rs) throws SQLException {
        BookDTO book = new BookDTO();
        book.setBookId(rs.getInt("book_id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPublisher(rs.getString("publisher"));
        book.setCategory(rs.getString("category"));
        book.setTotalStock(rs.getInt("total_stock"));
        book.setAvailableStock(rs.getInt("avaliable_stock")); // DB 오타 유지
        return book;
    }
}