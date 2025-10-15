package PracticalExercise13;

abstract class Calc {
    protected String errorMsg;
    protected int a, b;

    public void setValue(int a, int b) {
        this.a = a;
        this.b = b;
    }
    abstract public int calculate();
    
    public String getErrorMsg() {
        return errorMsg;
    }
}