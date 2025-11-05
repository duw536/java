package PracticalExercise8;

import java.util.Calendar;
import java.util.Scanner;

public class CalendarDay {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Calendar today = Calendar.getInstance();
        int todayYear = today.get(Calendar.YEAR);
        int todayMonth = today.get(Calendar.MONTH) + 1;
        int todayDay = today.get(Calendar.DAY_OF_MONTH);
        int todayDayOfYear = today.get(Calendar.DAY_OF_YEAR);

        System.out.println("오늘은 " + todayYear + "년 " + todayMonth + "월 " + todayDay + "일");

        while (true) {
            System.out.print("생일 입력(년 월 일)>>");
            String input = sc.nextLine();
            if (input.equals("그만")) break;

            String[] parts = input.split(" ");
            int birthYear = Integer.parseInt(parts[0]);
            int birthMonth = Integer.parseInt(parts[1]) - 1;
            int birthDay = Integer.parseInt(parts[2]);

            Calendar birth = Calendar.getInstance();
            birth.set(birthYear, birthMonth, birthDay);

            int birthDayOfYear = birth.get(Calendar.DAY_OF_YEAR);
            int days = 0;

            if (birthYear == todayYear) {
                days = todayDayOfYear - birthDayOfYear + 1;
            } else {
                int endOfBirthYear = birth.getActualMaximum(Calendar.DAY_OF_YEAR);
                days += (endOfBirthYear - birthDayOfYear + 1);

                for (int y = birthYear + 1; y < todayYear; y++) {
                    Calendar temp = Calendar.getInstance();
                    temp.set(y, 0, 1);
                    days += temp.getActualMaximum(Calendar.DAY_OF_YEAR);
                }

                days += todayDayOfYear;
            }
            days -= 1;

            System.out.println("오늘까지 " + days + "일 살아왔습니다.");
        }

        sc.close();
    }
}