package PracticalExercise7;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("년도 입력(-1이면 종료)>>");
        int year = sc.nextInt();

        if (year < 0) {
            return;
        }

        for (int month = 0; month < 12; month++) {
            printMonthCalendar(year, month);
        }

        sc.close();
    }

    public static void printMonthCalendar(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1); 

        int startDay = cal.get(Calendar.DAY_OF_WEEK); 
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); 

        System.out.println();
        System.out.println(year + "년 " + (month + 1) + "월");
        System.out.println("일 월 화 수 목 금 토");

        for (int i = 1; i < startDay; i++) {
            System.out.print("   ");
        }

        for (int day = 1; day <= lastDay; day++) {
            System.out.printf("%2d ", day);

            if ((day + startDay - 1) % 7 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}