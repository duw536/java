package PracticalExercise13;

public class SeatGroup {
    private char type; 
    private Seat[] seats;

    public SeatGroup(char type, int numSeats) {
        this.type = type;
        this.seats = new Seat[numSeats];
        for (int i = 0; i < seats.length; i++) {
            seats[i] = new Seat();
        }
    }

    public boolean reserve(int seatNum, String name) {
        if (seatNum < 1 || seatNum > seats.length) {
            System.out.println("잘못된 좌석 번호입니다.");
            return false;
        }
        if (seats[seatNum - 1].isOccupied()) {
            System.out.println("이미 예약된 좌석입니다.");
            return false;
        }
        seats[seatNum - 1].reserve(name);
        return true;
    }

    public boolean cancel(String name) {
        for (int i = 0; i < seats.length; i++) {
            if (seats[i].isOccupied() && seats[i].getReservationName().equals(name)) {
                seats[i].cancel();
                return true;
            }
        }
        return false; 
    }

    public void show() {
        System.out.print(type + ">> ");
        for (Seat s : seats) {
            if (s.isOccupied()) {
                System.out.print(s.getReservationName() + " ");
            } else {
                System.out.print("--- ");
            }
        }
        System.out.println();
    }
}