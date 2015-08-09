package com.company;

import java.util.Scanner;

/**
 * Created by ANISH on 8/4/2015.
 */
public class ATM {

    private AccountBook mAccountBook;

    private Scanner scanner;

    public ATM(AccountBook ab) {
        scanner = new Scanner(System.in);
        mAccountBook = ab;
    }


    public void run() {
        boolean var = true;
        while (var == true) {
            System.out.printf("Welcome to the ATM! %n%n");
            System.out.printf("Enter: %n");
            System.out.printf("a: Create a new account %n");
            System.out.printf("b: Accsess an existing account %n");
            System.out.printf("c: See all accounts %n");
            System.out.printf("d: Quit %n");
            String choice = scanner.next().toLowerCase();
            switch (choice) {
                case "a":
                    createAccount();
                    break;
                case "b":
                    System.out.printf("Please enter your account name: %n");
                    BankAccount ba = findAccount(scanner.next().toLowerCase());
                    if (ba != null) {
                        if (checkPin(ba)) {
                            afterCheck(ba);
                        } else {
                            System.out.printf("That was the wrong pin %n");
                        }
                    } else {
                        System.out.printf("Not a valid account %n");
                    }
                    break;
                case "c":
                    getList();
                    break;
                case "d":
                    var = false;
                    break;
                default:
                    System.out.printf("That's not a valid choice %n");
            }
        }
    }

    public void createAccount() {
        boolean var = true;
        while (var) {
            System.out.printf("Enter an account name: %n");
            String account = scanner.next().toLowerCase();
            if (findAccount(account) == null) {
                System.out.printf("Enter a pin. It should be one number: %n");
                int pin = scanner.nextInt();
                System.out.printf("Enter a starting balance: %n");
                int balance = scanner.nextInt();
                System.out.printf("Creating Account... %n");
                mAccountBook.add(new BankAccount(account, pin, balance));
                var = false;
            } else {
                System.out.printf("That name is already taken %n");
                System.out.printf("Try Again or enter QUIT to quit %n");
                if (scanner.next().equals("QUIT")) {
                    var = false;
                }
            }
        }
    }

    public boolean checkPin(BankAccount ba) {
        System.out.printf("Enter your pin: %n");
        int pin = scanner.nextInt();
        if (pin == ba.getPin()) {
            return true;
        } else {
            return false;
        }
    }

    public BankAccount findAccount(String name) {
        for (BankAccount ba : mAccountBook.getmBankAccounts()) {
            if (ba.getName().equals(name)) {
                return ba;
            }
        }
        return null;
    }

    public void withdraw(BankAccount ba) {
        System.out.printf("How much would you like to remove? %n");
        int amount = scanner.nextInt();
        System.out.printf("Withdrawing... %n");
        ba.remove(amount);
        getBalance(ba);
    }

    public void deposit(BankAccount ba) {
        System.out.printf("How much would you like to deposit? %n");
        int amount = scanner.nextInt();
        ba.recieve(amount);
        getBalance(ba);

    }

    public void transfer(BankAccount ba1) {
        System.out.printf("Enter the account name of which you would like to transfer %n");
        String ba2 = scanner.next().toLowerCase();
        BankAccount baRecieve = findAccount(ba2);
        if (baRecieve != null) {
            System.out.printf("Enter the amount %n");
            int amount = scanner.nextInt();
            if (ba1.transfer(baRecieve, amount)) {
                System.out.printf("Transaction completed %n");
                getBalance(ba1);
            } else {
                System.out.printf("You did not have enough money %n");
            }
        } else {
            System.out.printf("That's not a valid account %n");
        }

    }

    public void afterCheck(BankAccount ba) {
        boolean var = true;
        while (var == true) {
            System.out.printf("Enter: %n");
            System.out.printf("a: Withdraw %n");
            System.out.printf("b: Deposit %n");
            System.out.printf("c: Transfer %n");
            System.out.printf("d: Log out of this account %n");
            String choice1 = scanner.next().toLowerCase();

            switch (choice1) {
                case "a":
                    withdraw(ba);
                    break;
                case "b":
                    deposit(ba);
                    break;
                case "c":
                    transfer(ba);
                    break;
                case "d":
                    var = false;
                    break;
                default:
                    System.out.printf("That's not a valid choice");
                    afterCheck(ba);
            }
        }
    }

    public void getBalance(BankAccount ba) {
        System.out.printf("Your new balance is: %d %n%n", ba.getBalance());
    }

    public void getList() {
        for (BankAccount ba : mAccountBook.getmBankAccounts()) {
            System.out.printf("%s %n",
                    ba.getName());
        }
        System.out.printf("%n");
    }
}
