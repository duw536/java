package Basic;

import java.util.Scanner;

public class PracticalExercise12 {
	public static void main(String[] args) {
		String boyMiddleList [] = {"기", "만", "용", "종", "현", "진", "재", "승", "소", "상", "지"};
		String boyLastList [] = {"태", "진", "광", "혁", "우", "철", "빈", "준", "구", "호", "석"};
		String girlMiddleList [] = {"은", "원", "경", "수", "현", "예", "여", "송", "서", "채", "하"};
		String girlLastList [] = {"진", "연", "경", "서", "리", "숙", "미", "원", "린", "희", "수"};
		
		Scanner scn = new Scanner(System.in);
		System.out.println("***** 작명 프로그램이 시작됩니다. *****");
	    
	    while(true) {
            System.out.print("남/여 선택>>");
            String gender = scn.next();
            

            if (gender.equals("그만")) {
                break;
            } 
            
            else if (gender.equals("남")) {
            	System.out.print("성 입력>>");
        	    String fName = scn.next();
                int middleIndex = (int)(Math.random() * boyMiddleList.length);
                String mName = boyMiddleList[middleIndex];
                
                int lastIndex = (int)(Math.random() * boyLastList.length);
                String lName = boyLastList[lastIndex];
                
                String fullName = fName + mName + lName;
                System.out.println("추천 이름: " + fullName);
            } 
            
            else if (gender.equals("여")) {
            	System.out.print("성 입력>>");
        	    String fName = scn.next();
                int middleIndex = (int)(Math.random() * girlMiddleList.length);
                String mName = girlMiddleList[middleIndex];
                
                int lastIndex = (int)(Math.random() * girlLastList.length);
                String lName = girlLastList[lastIndex];
                
                String fullName = fName + mName + lName;
                System.out.println("추천 이름: " + fullName);
            } 
            
            else {
            	System.out.println("남/여 그만 중에서 입력하세요!");
            }

        }
	}
}
