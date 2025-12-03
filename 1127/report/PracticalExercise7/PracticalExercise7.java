package PracticalExercise7;

import java.sql.*;

public class PracticalExercise7 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookdb", "root", "1111");

            stmt = conn.createStatement();

            String sql = "DELETE FROM Book WHERE author = '제인 오스틴'";

            int count = stmt.executeUpdate(sql);
            System.out.println("삭제 완료: " + count + "개의 레코드가 삭제되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {}
        }
    }
}