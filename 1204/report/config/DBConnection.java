package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // DB 접속 정보 (본인 환경에 맞게 수정 필요)
    private static final String URL = "jdbc:mysql://localhost:3306/research_db";
    private static final String USER = "root";      // MySQL 아이디
    private static final String PASSWORD = "1111";  // MySQL 비밀번호

    // DB 연결 객체를 가져오는 메서드
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("DB 연결 실패: " + e.getMessage());
        }
        return conn;
    }

    // 연결 해제 메서드
    public static void close(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ★★★ 테스트용 메인 메소드 추가 ★★★
    // 이 부분이 있어야 'Run'을 했을 때 실행이 됩니다.
    public static void main(String[] args) {
        System.out.println("DB 연결 테스트를 시작합니다...");
        
        Connection conn = getConnection();
        
        if (conn != null) {
            System.out.println("✅ 성공: 데이터베이스에 성공적으로 연결되었습니다!");
            close(conn); // 테스트 끝났으니 연결 끊기
        } else {
            System.out.println("❌ 실패: 데이터베이스 연결에 실패했습니다. 아이디/비번/URL을 확인하세요.");
        }
    }
}