package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.DBConnection;
import dto.Account;

public class AccountDAO {

    // 1. 회원가입
    public boolean join(Account account) {
        String sql = "INSERT INTO account (username, password, name, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, account.getUsername());
            pstmt.setString(2, account.getPassword());
            pstmt.setString(3, account.getName());
            pstmt.setString(4, account.getRole()); 
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("회원가입 실패: " + e.getMessage());
            return false;
        }
    }

    // 2. 로그인
    public Account login(String username, String password) {
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
        Account member = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    member = new Account();
                    member.setId(rs.getInt("id"));
                    member.setUsername(rs.getString("username"));
                    member.setPassword(rs.getString("password"));
                    member.setName(rs.getString("name"));
                    member.setRole(rs.getString("role"));
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return member;
    }

    // 3. 관리용 목록 조회 (어드민, 게스트 제외)
    public List<Account> getManageableAccounts() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM account WHERE role NOT IN ('ADMIN', 'GUEST') ORDER BY id ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setName(rs.getString("name"));
                a.setRole(rs.getString("role"));
                list.add(a);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // ★ 4. 계정 삭제 (자료 주인 이전 기능 추가)
    public boolean deleteAccount(int targetId) {
        Connection conn = null;
        PreparedStatement pstmtFindAdmin = null;
        PreparedStatement pstmtUpdate = null;
        PreparedStatement pstmtDelete = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            // 트랜잭션 시작 (중간에 실패하면 다 취소하기 위해 자동 저장 끔)
            conn.setAutoCommit(false); 

            // (1) 관리자(admin)의 ID 찾기
            int adminId = -1;
            String findAdminSql = "SELECT id FROM account WHERE username = 'admin'";
            pstmtFindAdmin = conn.prepareStatement(findAdminSql);
            rs = pstmtFindAdmin.executeQuery();
            
            if (rs.next()) {
                adminId = rs.getInt("id");
            } else {
                // 관리자가 없으면 삭제 진행 불가 (자료를 넘길 곳이 없음)
                return false; 
            }

            // (2) 삭제될 사람(targetId)의 자료를 관리자(adminId) 앞으로 명의 변경
            String updateMaterialSql = "UPDATE material SET account_id = ? WHERE account_id = ?";
            pstmtUpdate = conn.prepareStatement(updateMaterialSql);
            pstmtUpdate.setInt(1, adminId);  // 새 주인: 관리자
            pstmtUpdate.setInt(2, targetId); // 옛 주인: 삭제될 사람
            pstmtUpdate.executeUpdate();

            // (3) 이제 안전하게 계정 삭제
            String deleteSql = "DELETE FROM account WHERE id = ?";
            pstmtDelete = conn.prepareStatement(deleteSql);
            pstmtDelete.setInt(1, targetId);
            int result = pstmtDelete.executeUpdate();

            // 성공하면 커밋(저장)
            conn.commit(); 
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback(); // 에러 나면 원상복구
            } catch (SQLException ex) { ex.printStackTrace(); }
            return false;
        } finally {
            // 자원 해제 (순서대로)
            try {
                if (rs != null) rs.close();
                if (pstmtFindAdmin != null) pstmtFindAdmin.close();
                if (pstmtUpdate != null) pstmtUpdate.close();
                if (pstmtDelete != null) pstmtDelete.close();
                if (conn != null) {
                    conn.setAutoCommit(true); // 설정 복구
                    conn.close();
                }
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}