package PracticalExercise8;

public class Account {
	
    private int balance;

    public Account(int inBalance) {
        this.balance = inBalance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void deposit(int[] amounts) {
        for (int amount : amounts) {
            this.balance += amount;
        }
    }

    public int withdraw(int amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
            return amount;
        } else {
            int withdrawnAmount = this.balance;
            this.balance = 0;
            return withdrawnAmount;
        }
    }
}