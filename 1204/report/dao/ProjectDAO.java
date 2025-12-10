package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.DBConnection;
import dto.Category;
import dto.Material;
import dto.Project;

public class ProjectDAO {

    // --- [1. 카테고리 기능] ---
    
    public List<Category> getCategoryList() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM category ORDER BY id ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) list.add(new Category(rs.getInt("id"), rs.getString("name")));
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean addCategory(String name) {
        String sql = "INSERT INTO category (name) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    public boolean deleteCategory(int id) {
        String sql = "DELETE FROM category WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }


    // --- [2. 프로젝트 기능] ---

    public List<Project> getProjectList() {
        List<Project> list = new ArrayList<>();
        // ★ 수정됨: ORDER BY p.id ASC (옛날 게 위로, 새 게 아래로)
        String sql = "SELECT p.*, c.name as cat_name FROM project p " +
                     "LEFT JOIN category c ON p.category_id = c.id ORDER BY p.id ASC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Project p = new Project();
                p.setId(rs.getInt("id"));
                p.setCategoryId(rs.getInt("category_id"));
                p.setCategoryName(rs.getString("cat_name"));
                p.setProjectName(rs.getString("project_name"));
                p.setDescription(rs.getString("description"));
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean addProject(Project project) {
        String sql = "INSERT INTO project (category_id, project_name, description) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            if (project.getCategoryId() == 0) pstmt.setNull(1, Types.INTEGER);
            else pstmt.setInt(1, project.getCategoryId());
            pstmt.setString(2, project.getProjectName());
            pstmt.setString(3, project.getDescription());
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }

    public boolean deleteProject(int id) {
        String sql = "DELETE FROM project WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // --- [3. 자료(Material) 기능] ---

    public boolean addMaterial(Material m) {
        String sql = "INSERT INTO material (project_id, account_id, sample_name, csv_file_name, csv_file_path, pptx_file_name, pptx_file_path) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, m.getProjectId());
            pstmt.setInt(2, m.getAccountId());
            pstmt.setString(3, m.getSampleName());
            pstmt.setString(4, m.getCsvFileName());
            pstmt.setString(5, m.getCsvFilePath());
            if(m.getPptxFileName() == null) {
                pstmt.setNull(6, Types.VARCHAR);
                pstmt.setNull(7, Types.VARCHAR);
            } else {
                pstmt.setString(6, m.getPptxFileName());
                pstmt.setString(7, m.getPptxFilePath());
            }
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean deleteMaterial(int id) {
        String sql = "DELETE FROM material WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (Exception e) { return false; }
    }


    // --- [4. 조회 기능 (조인)] ---

    public List<String[]> getProjectWithMaterials() {
        List<String[]> list = new ArrayList<>();

        String sql = "SELECT p.id, c.name as cat_name, p.project_name, " +
                     "m.id as material_id, m.sample_name, " +
                     "m.csv_file_name, m.csv_file_path, " +   // CSV 경로 추가
                     "m.pptx_file_name, m.pptx_file_path, " + // PPTX 경로 추가
                     "a.name as uploader_name " +
                     "FROM project p " +
                     "LEFT JOIN category c ON p.category_id = c.id " +
                     "LEFT JOIN material m ON p.id = m.project_id " +
                     "LEFT JOIN account a ON m.account_id = a.id " +
                     "ORDER BY p.id ASC, m.id ASC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = String.valueOf(rs.getInt("id"));
                String uploader = rs.getString("uploader_name");
                if(uploader == null) uploader = "-";

                String cat = rs.getString("cat_name");
                String pName = rs.getString("project_name");
                String sample = rs.getString("sample_name");
                if(sample == null) sample = "자료 없음";
                
                String csvName = rs.getString("csv_file_name");
                if(csvName == null) csvName = "";
                
                // ★ 파일 경로 가져오기
                String csvPath = rs.getString("csv_file_path");
                if(csvPath == null) csvPath = "";

                String pptxName = rs.getString("pptx_file_name");
                if(pptxName == null) pptxName = "";
                
                String pptxPath = rs.getString("pptx_file_path");
                if(pptxPath == null) pptxPath = "";

                int mId = rs.getInt("material_id"); 
                String materialIdStr = (mId == 0) ? "-1" : String.valueOf(mId);

                // 배열 순서: 
                // 0:ID, 1:업로더, 2:분류, 3:프로젝트명, 4:샘플명, 
                // 5:CSV이름, 6:PPTX이름, 7:MID, 
                // 8:CSV경로(NEW), 9:PPTX경로(NEW)
                list.add(new String[]{
                    id, uploader, cat, pName, sample, 
                    csvName, pptxName, materialIdStr, 
                    csvPath, pptxPath // 경로 정보 추가
                });
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}