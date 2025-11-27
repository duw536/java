package jdbc;

import java.sql.*;
import java.util.Scanner;

public class JDBC_Ex1 {
    public static void main (String[] args) {
        Connection conn = null; // 연결 객체를 try 블록 밖에서 선언
        Statement stmt = null;
        ResultSet rs = null;
        
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sampledb", "root", "1234"); 
            System.out.println("DB 연결 완료");
            
            // --- 반복 시작 ---
            while (true) {
                // Statement와 ResultSet은 루프 내에서 다시 생성되므로 루프 시작 시 null로 초기화합니다.
                stmt = null;
                rs = null;
                
                System.out.println("\n------------------------------------------------");
                System.out.println("기능을 선택하세요 (1: 모든 필드 조회, 2: 모든 dept를 Computer로 업데이트, 3: Hong 학생 데이터 삽입, 4: SW 학과 학생 삭제, 0: 종료): ");
                
                // 사용자 입력 처리
                int selection = scanner.nextInt();
                
                if (selection == 0) {
                    System.out.println("프로그램을 종료합니다.");
                    break; // 0 입력 시 루프를 종료하고 finally 블록으로 이동
                }
                
                // 1. SELECT 기능 (조회)
                if (selection == 1) {
                    // Statement 객체 생성
                    stmt = conn.createStatement();
                    
                    String sql = "SELECT * FROM student";
                    rs = stmt.executeQuery(sql);
                    
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();
                    
                    // --- 헤더 출력 ---
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(rsmd.getColumnName(i) + "\t");
                    }
                    System.out.println("\n" + "=".repeat(columnCount * 8)); // 구분선
                    
                    // --- 데이터 출력 ---
                    while (rs.next()) {
                        for (int i = 1; i <= columnCount; i++) {
                            System.out.print(rs.getString(i) + "\t");
                        }
                        System.out.println();
                    }
                
                // 2. UPDATE 기능 (수정)
                } else if (selection == 2) {
                    // Statement 객체 생성
                    stmt = conn.createStatement();
                    
                    String updateSql = "UPDATE student SET dept = 'Computer'";
                    
                    int updateCount = stmt.executeUpdate(updateSql);
                    
                    System.out.println("업데이트 완료! 총 " + updateCount + "개의 레코드가 수정되었습니다.");
                    
                // 3. INSERT 기능 (삽입)
                } else if (selection == 3) {
                    // Statement 객체 생성
                    stmt = conn.createStatement();
                    
                    String insertSql = "INSERT INTO student (id, name, dept) VALUES ('1234', 'Hong', 'SW')";
                    
                    int insertCount = stmt.executeUpdate(insertSql);
                    
                    System.out.println("데이터 삽입 완료! 총 " + insertCount + "개의 레코드가 추가되었습니다.");
                    
                // 4. DELETE 기능 (삭제)
                } else if (selection == 4) {
                    // Statement 객체 생성
                    stmt = conn.createStatement();
                    
                    // SQL DELETE 쿼리 작성: dept가 'SW'인 모든 행을 삭제
                    String deleteSql = "DELETE FROM student WHERE dept = 'SW'";
                    
                    // 쿼리 실행 및 삭제된 행의 수(count) 반환
                    int deleteCount = stmt.executeUpdate(deleteSql);
                    
                    System.out.println("데이터 삭제 완료! 총 " + deleteCount + "개의 레코드가 삭제되었습니다.");
                    
                } else {
                    System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
                }
                
            } // --- 반복 종료 ---

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 에러");
        } catch (SQLException e) {
            System.out.println("DB 연결 또는 쿼리 실행 에러");
            e.printStackTrace();
        } finally {
            // 자원 해제
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (scanner != null) scanner.close();
            } catch (SQLException e) {
                System.out.println("자원 해제 에러");
            }
        }
    }

}
