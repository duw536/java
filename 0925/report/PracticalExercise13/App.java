package PracticalExercise13;
import java.util.Scanner;

public class App {
    private Scanner scanner;
    private SeatGroup[] seatGroups;

    public App() {
        scanner = new Scanner(System.in);
        seatGroups = new SeatGroup[3]; 
        seatGroups[0] = new SeatGroup('S', 10);
        seatGroups[1] = new SeatGroup('A', 10);
        seatGroups[2] = new SeatGroup('B', 10);
    }

    private int getMenu() {
        System.out.print("\n예약:1, 조회:2, 취소:3, 끝내기:4>> ");
        return scanner.nextInt();
    }

    private void reserve() {
        System.out.print("좌석구분 S(1), A(2), B(3)>> ");
        int typeIndex = scanner.nextInt() - 1;
        if (typeIndex < 0 || typeIndex > 2) {
            System.out.println("잘못된 좌석 구분입니다.");
            return;
        }
        seatGroups[typeIndex].show();
        System.out.print("이름>> ");
        String name = scanner.next();
        System.out.print("번호>> ");
        int seatNum = scanner.nextInt();
        
        seatGroups[typeIndex].reserve(seatNum, name);
    }
    
    private void show() {
        for (SeatGroup sg : seatGroups) {
            sg.show();
        }
        System.out.println("<<<조회를 완료하였습니다.>>>");
    }
    
    private void cancel() {
        System.out.print("좌석구분 S(1), A(2), B(3)>> ");
        int typeIndex = scanner.nextInt() - 1;
        if (typeIndex < 0 || typeIndex > 2) {
            System.out.println("잘못된 좌석 구분입니다.");
            return;
        }
        seatGroups[typeIndex].show();
        System.out.print("이름>> ");
        String name = scanner.next();
        
        boolean canceled = seatGroups[typeIndex].cancel(name);
        if (canceled) {
            System.out.println("<<<취소를 완료하였습니다.>>>");
        } else {
            System.out.println("예약된 사람을 찾을 수 없습니다.");
        }
    }

    public void run() {
        System.out.println("명품콘서트홀 예약 시스템입니다.");
        while (true) {
            int choice = getMenu();
            switch (choice) {
                case 1: reserve(); break;
                case 2: show(); break;
                case 3: cancel(); break;
                case 4: 
                    scanner.close();
                    return;
                default: System.out.println("잘못된 입력입니다.");
            }
        }
    }

}