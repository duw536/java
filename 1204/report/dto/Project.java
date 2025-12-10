package dto;

public class Project {
    private int id;
    private int categoryId;
    private String categoryName; // 화면 표시용 (DB 조인 결과)
    private String projectName;
    private String description;

    public Project() {}

    // 생성용 (ID 없이)
    public Project(int categoryId, String projectName, String description) {
        this.categoryId = categoryId;
        this.projectName = projectName;
        this.description = description;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    
    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    // 혹시 모를 출력용
    @Override
    public String toString() {
        return projectName;
    }
}