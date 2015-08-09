package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANISH on 8/4/2015.
 */
public class AccountBook {
    private List<BankAccount> mBankAccounts;

    public AccountBook() {
        mBankAccounts = new ArrayList<>();
    }

    public int getSize() {
        return mBankAccounts.size();
    }

    public void add(BankAccount ba) {
        mBankAccounts.add(ba);
    }

    public List<BankAccount> getmBankAccounts() {
        return mBankAccounts;
    }

    public void exportTo(String fileName) {
        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                PrintWriter writer = new PrintWriter(fos);
        ) {
            for (BankAccount ba : mBankAccounts) {
                writer.printf("%s|%s|%s%n",
                        ba.getName(),
                        ba.getBalance(),
                        ba.getPin());

            }
        } catch (IOException ioe) {
            System.out.printf("Problem saving %s %n", fileName);
            ioe.printStackTrace();
        }
    }

    public void importFrom(String fileName) {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|");
                add(new BankAccount(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2])));
            }
        } catch (IOException ioe) {
            System.out.printf("Problems loading %s %n", fileName);
            ioe.printStackTrace();
        }
    }


}
