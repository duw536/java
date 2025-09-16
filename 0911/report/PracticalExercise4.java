package Basic;

import java.util.Scanner;

class PracticalExercise4 {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.print("여행지>>");
		String travelDestination = scn.nextLine();
		System.out.print("인원수>>");
		int personnel = scn.nextInt();
		System.out.print("숙박일>>");
		int accommodationDate = scn.nextInt();
		System.out.print("1인당 항공료>>");
		int airfare = scn.nextInt();
		System.out.print("1방 숙박비>>");
		int accommodationFee = scn.nextInt();
		int room = 0;
		
		room = personnel /2;
		if (personnel % 2 != 0) {
			room++;
		}
		
		System.out.println(personnel+"명의 "+travelDestination+ accommodationDate+"박 "+(accommodationDate+1)+"일 여행에는 방이 "+room+"개 필요하며 경비는 "+ ((personnel*airfare)+(room*accommodationFee*accommodationDate)));
	}
}
