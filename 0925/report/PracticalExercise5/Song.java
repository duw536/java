package PracticalExercise5;

public class Song {
	private String title;
	private String singer;
	private int year;
	private String lang;
	
	public Song(String title, String singer, int year, String lang) {
		this.title = title;
		this.singer = singer;
		this.year = year;
		this.lang = lang;
	}

	public void show() {
        System.out.println(year + "년 " + lang + "의 " + singer + "가 부른 " + title);
    }
}
	

