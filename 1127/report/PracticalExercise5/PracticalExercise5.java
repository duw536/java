package PracticalExercise5;

import java.sql.*;

public class PracticalExercise5 {
    public static void main (String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); 

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookdb", "root", "1111");
            
            System.out.println("DB 연결 완료");

            stmt = conn.createStatement();

            String sql = "INSERT INTO book (id, title, publisher, author) VALUES "
                       + "(0, 'Harry Potter', 'Bloomsbury', 'J. K. Rowling'), "
                       + "(1, 'The Lord of the Rings', 'Allen & Unwin', 'J. R. R. Tolkein'), "
                       + "(2, 'Pride and Prejudice', 'T. Egerton Kingdom', 'Jane Austen')";

            int count = stmt.executeUpdate(sql);
            System.out.println("데이터 삽입 성공! 총 " + count + "건 입력됨.");

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러 (jar 파일 연결 확인 필요)");
        } catch (SQLException e) {
            System.out.println("DB 연결 또는 쿼리 에러 (DB이름이 bookdb가 맞는지 확인하세요!)");
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {}
        }
    }
}