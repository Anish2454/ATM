package com.company;

import java.io.Serializable;

/**
 * Created by ANISH on 8/2/2015.
 */
public class BankAccount implements Serializable {
    private int mPin;
    private String mName;
    private int mBalance;

    public BankAccount(String name, int pin, int startingBalance) {
        mName = name;
        mPin = pin;
        mBalance = startingBalance;
    }

    @Override
    public String toString() {
        return String.format("Bank Account by: %s with a balance of %d ", mName, mBalance);
    }

    public boolean remove(int amount) {
        if (amount <= mBalance) {
            mBalance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(BankAccount ba2, int amount) {
        if (remove(amount)) {
            ba2.recieve(amount);
            return true;
        } else {
            return false;
        }
    }

    public void recieve(int amount) {
        mBalance += amount;
    }

    public int getPin() {
        return mPin;
    }

    public String getName() {
        return mName;
    }

    public int getBalance() {
        return mBalance;
    }

}
