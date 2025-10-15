package PracticalExercise13;

class Div extends Calc {
    @Override
    public int calculate() {
        if (b == 0) {
            errorMsg = "0으로 나눌 수 없음. ";
            return 0;
        }
        return a / b;
    }
}