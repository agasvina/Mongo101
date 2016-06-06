package com.lucareto.model;

public class BankAccount {
    
    private int total = 0;
    
    public int getTotal() {
        return total;
    }
    public void setTotal(final int total) {
        this.total = total;
    }
    
    public void deposit(final int money) {
        total += money;
    }
    
    public void transfer(BankAccount other,final int money) {
        total -= money;
        other.deposit(money);
    }

}
