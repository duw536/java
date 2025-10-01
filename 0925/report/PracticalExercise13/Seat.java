package PracticalExercise13;

public class Seat {
    private String name;

    public void reserve(String name) {
        this.name = name;
    }

    public void cancel() {
        this.name = null;
    }

    public boolean isOccupied() {
        return name != null;
    }
    
    public String getReservationName() {
        return name;
    }
}