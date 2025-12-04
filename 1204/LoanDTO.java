package Book;

import java.sql.Date;

public class LoanDTO {
    private int loanId;
    private int memberId;
    private int bookId;
    private Date loanDate;
    private Date returnDate;
    private String memberName;
    private String bookTitle;

    public LoanDTO() {}

    public LoanDTO(int loanId, int memberId, int bookId, Date loanDate, Date returnDate) {
        this.loanId = loanId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public int getLoanId() { return loanId; }
    public void setLoanId(int loanId) { this.loanId = loanId; }
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public Date getLoanDate() { return loanDate; }
    public void setLoanDate(Date loanDate) { this.loanDate = loanDate; }
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public String getBookTitle() { return bookTitle; }
    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle; }
}