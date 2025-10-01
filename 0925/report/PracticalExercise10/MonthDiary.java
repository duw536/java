package PracticalExercise10;

import java.util.Scanner;

public class MonthDiary {
    private int year;
    private int month;
    private DayDiary[] days;
    private Scanner scanner;

    public MonthDiary(int year, int month) {
        this.year = year;
        this.month = month;
        this.days = new DayDiary[30];
        for (int i = 0; i < days.length; i++) {
            days[i] = new DayDiary();
        }
        this.scanner = new Scanner(System.in);
    }

    private int getMenu() {
        System.out.print("기록:1, 보기:2, 종료:3>>");
        return scanner.nextInt();
    }

    private void write() {
        System.out.print("날짜(1~30)와 텍스트(빈칸없이 4글자이하)>>");
        int date = scanner.nextInt();
        String memo = scanner.next();
        days[date - 1].setMemo(memo);
        scanner.nextLine();
    }

    private void show() {
        for (int i = 0; i < days.length; i++) {
            if (i % 7 == 0) {
                System.out.println();
            }
            
            String memo = days[i].getMemo();
            
            if (memo == null) {
                System.out.print("***   \t\t");
            } else {

                System.out.print(memo + "\t\t");
            }
        }
        System.out.println("\n");
    }

    public void run() {
        System.out.println("***** " + this.year + "년 " + this.month + "월 다이어리 *****");
        
        while (true) {
            int choice = getMenu();
            switch (choice) {
                case 1:
                    write();
                    break;
                case 2:
                    show();
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    scanner.nextLine(); 
            }
        }
    }


}


