package Lib;

public class MemberDTO {
    // 필드 (DB 컬럼과 매칭)
    private int memberId;       // member_id (PK, AI)
    private String name;        // name
    private String loginId;     // login_id (UQ)
    private String password;    // password
    private String phone;       // phone
    private String status;      // status (ACTIVE, BANNED 등)

    public MemberDTO() {}

    public MemberDTO(int memberId, String name, String loginId, String password, String phone, String status) {
        this.memberId = memberId;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.phone = phone;
        this.status = status;
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
}