package Book;

public class MemberDTO {
    private int memberId;
    private String name;
    private String loginId;
    private String password;
    private String phone;
    private String status;
    private String role; // [추가됨] 권한 (ADMIN 또는 USER)

    public MemberDTO() {}

    public MemberDTO(int memberId, String name, String loginId, String password, String phone, String status, String role) {
        this.memberId = memberId;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.role = role;
    }

    // Getter & Setter
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLoginId() { return loginId; }
    public void setLoginId(String loginId) { this.loginId = loginId; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}