package dto;

public class Category {
    private int id;
    private String name;

    public Category() {}
    
    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // ★ 콤보박스에서 이름만 보이게 하기 위해 필수!
    @Override
    public String toString() {
        return name;
    }

    // Getter & Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}