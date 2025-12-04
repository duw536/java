package Book;

public class BookDTO {
    private int bookId;      // int (PK)
    private String title;       
    private String author;      
    private String publisher;   
    private String category;    
    private int totalStock;     
    private int availableStock; 

    public BookDTO() {}

    public BookDTO(int bookId, String title, String author, String publisher, 
                   String category, int totalStock, int availableStock) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.totalStock = totalStock;
        this.availableStock = availableStock;
    }

    // Getter, Setter 생략 (그대로 사용)
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getTotalStock() { return totalStock; }
    public void setTotalStock(int totalStock) { this.totalStock = totalStock; }
    public int getAvailableStock() { return availableStock; }
    public void setAvailableStock(int availableStock) { this.availableStock = availableStock; }
}