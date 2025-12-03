package PracticalExercise8;

import java.sql.*;
import java.util.Scanner;

public class PracticalExercise8 {
    static final String DB_URL = "jdbc:mysql://localhost:3306/bookdb"; 
    static final String USER = "root";
    static final String PASS = "1111";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            System.out.println(">> 데이터베이스 관리 프로그램 시작");

            while (true) {
                System.out.println("\n================ [ 현재 도서 목록 ] ================");
                String selectSql = "SELECT * FROM Book";
                ResultSet rs = stmt.executeQuery(selectSql);
                
                System.out.printf("%-5s %-25s %-20s %-15s\n", "ID", "Title", "Publisher", "Author");
                System.out.println("-------------------------------------------------------------------");
                
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    String publisher = rs.getString("publisher");
                    String author = rs.getString("author");
                    System.out.printf("%-5d %-25s %-20s %-15s\n", id, title, publisher, author);
                }
                rs.close();
                System.out.println("===================================================");

                System.out.println("기능 선택 >> 1.추가  2.삭제  3.수정  4.끝내기");
                System.out.print("입력: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 4) {
                    System.out.println("프로그램을 종료합니다.");
                    break;
                }

                if (choice == 1) {
                    System.out.print("ID 입력: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    
                    System.out.print("책 제목 입력: ");
                    String title = scanner.nextLine();
                    
                    System.out.print("출판사 입력: ");
                    String publisher = scanner.nextLine();
                    
                    System.out.print("저자 입력: ");
                    String author = scanner.nextLine();

                    String insertSql = "INSERT INTO Book (id, title, publisher, author) VALUES (" 
                            + id + ", '" + title + "', '" + publisher + "', '" + author + "')";
                    
                    try {
                        stmt.executeUpdate(insertSql);
                        System.out.println(">> 레코드 추가 완료!");
                    } catch (SQLException e) {
                        System.out.println(">> 에러: 이미 존재하는 ID이거나 입력 형식이 잘못되었습니다.");
                    }
                }
                else if (choice == 2) {
                    System.out.print("삭제할 책의 ID 입력: ");
                    int targetId = scanner.nextInt();
                    
                    String deleteSql = "DELETE FROM Book WHERE id = " + targetId;
                    int count = stmt.executeUpdate(deleteSql);
                    
                    if (count > 0) System.out.println(">> 삭제 완료!");
                    else System.out.println(">> 해당 ID를 찾을 수 없습니다.");
                }
                else if (choice == 3) {
                    System.out.print("수정할 속성 이름(id, title, publisher, author 중 하나): ");
                    String colName = scanner.nextLine();
                    
                    System.out.print("현재 값(조건): ");
                    String oldValue = scanner.nextLine();
                    
                    System.out.print("새로운 값(변경): ");
                    String newValue = scanner.nextLine();
                    String updateSql = "UPDATE Book SET " + colName + " = '" + newValue + "' " 
                                     + "WHERE " + colName + " = '" + oldValue + "'";
                    
                    try {
                        int count = stmt.executeUpdate(updateSql);
                        if (count > 0) System.out.println(">> 수정 완료! (" + count + "건 변경됨)");
                        else System.out.println(">> 조건에 맞는 레코드가 없습니다.");
                    } catch (SQLException e) {
                        System.out.println(">> 에러: 속성 이름을 잘못 입력했거나 SQL 오류입니다.");
                    }
                }
                else {
                    System.out.println(">> 잘못된 번호입니다. 다시 입력하세요.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
                if (scanner != null) scanner.close();
            } catch (SQLException e) {}
        }
    }
}