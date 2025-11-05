package PracticalExercise2;

public class Book {
	String author;
	String title;
	String buyerName;

	public Book(String author, String title, String buyerName) {
		this.author = author;
		this.title = title;
		this.buyerName = buyerName;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			Book s = (Book) obj;
			return this.title.equals(s.title);
		}
		return false;
	}

	@Override
	public String toString() {
		return buyerName + "이 구입한 도서 " + author + "의 " + title; 
	}
	
}
