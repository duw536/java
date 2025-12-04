package Lib;

import java.sql.*;
import java.util.ArrayList;

public class MemberDAO {

    // DB 연결 정보
    private final String URL = "jdbc:mysql://localhost:3306/Lms";
    private final String ID = "root";
    private final String PW = "1234";

    // DB 연결 공통 메서드
    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, ID, PW);
    }

    // 자원 해제 공통 메서드
    private void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------------------
    // 1. 회원 추가 (INSERT)
    // ---------------------------------------------------------
    public int insertMember(MemberDTO member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        // SQL: member_id 제외, status는 보통 가입 시 'ACTIVE'(활동중)로 설정
        String sql = "INSERT INTO members (name, login_id, password, phone, status) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getLoginId());
            pstmt.setString(3, member.getPassword());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getStatus()); // 보통 "ACTIVE"

            result = pstmt.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            // login_id가 UNIQUE이므로 중복되면 이 에러가 발생함
            System.out.println("아이디 중복 에러 발생");
            return -1; // 중복됨을 알리는 코드 -1 반환
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, null);
        }
        return result;
    }
    
 // 맨 위에 import java.util.ArrayList; 추가 확인!

    // ---------------------------------------------------------
    // 2. 전체 회원 목록 조회 (SELECT)
    // ---------------------------------------------------------
    public ArrayList<MemberDTO> getAllMembers() {
        ArrayList<MemberDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 모든 회원을 번호순으로 가져오기
        String sql = "SELECT * FROM members ORDER BY member_id ASC";

        try {
            conn = getConnection(); // 공통 연결 메서드 사용
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MemberDTO m = new MemberDTO();
                m.setMemberId(rs.getInt("member_id"));
                m.setName(rs.getString("name"));
                m.setLoginId(rs.getString("login_id"));
                m.setPassword(rs.getString("password")); // 가져오긴 하지만 화면엔 안 띄울 예정
                m.setPhone(rs.getString("phone"));
                m.setStatus(rs.getString("status"));
                
                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, pstmt, rs); // 자원 해제
        }
        
        return list;
    }
}