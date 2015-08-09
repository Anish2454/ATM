package com.company;

public class Main {

    public static void main(String[] args) {
        AccountBook accountBook = new AccountBook();
        accountBook.importFrom("accounts.txt");
        ATM machine = new ATM(accountBook);
        machine.run();
        accountBook.exportTo("accounts.txt");

    }


}
