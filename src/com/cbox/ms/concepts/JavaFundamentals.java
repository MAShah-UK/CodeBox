package com.cbox.ms.concepts;

import java.util.ArrayList;
import java.util.List;

// Abstraction:
interface Transaction { // Can't be initialised directly. Must be subclassed.
    void deposit(double amount); // public by default.
    void withdraw(double amount);
}

// Inheritance:
abstract class BankAccount implements Transaction { // Can't be initialised directly. Must be subclassed.
    // Encapsulation:
    private final int id; // Must be assigned during declaration or in constructor.
    private static int idCount = 1; // Static field / class variable. Instantiated once per class.
    private final String name;
    private double balance;
    private List<Double> transactions = new ArrayList<>();

    @Override // Override annotation marks overridden method.
    public void deposit(double amount) {
        if (amount > 0) {
            transactions.add(amount);
            balance += amount;
            System.out.println(amount + " was deposited to " + name + "'s account.");
        } else {
            System.out.println("Invalid amount deposited to " + name + "'s account.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (balance - amount > 0) {
            transactions.add(-amount);
            balance -= amount;
            System.out.println(amount + " was withdrawn from " + name + "'s account.");
        } else {
            System.out.println("Invalid amount withdrawn from " + name + "'s account.");
        }
    }

    public BankAccount(String name) { // Automatically called when class is instantiated.
        this.name = name;
        id = idCount;
        idCount++;
    }

    // Getters:

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }
}

// Classes can only extend one class, but can implement as many as needed.
class CurrentAccount extends BankAccount {

    private double overdraftLimit;
    private double currOverdraft;

    @Override
    public void deposit(double amount) {
        // Deal with overdraft logic.
    }

    @Override
    public void withdraw(double amount) {
        // Deal with overdraft logic.
    }

    // Super class doesn't have default constructor so neither can subclass.
    public CurrentAccount(String name, double overdraftLimit) {
        // Must be the first statement to ensure that superclass is initialised before subclass.
        super(name);
        this.overdraftLimit = overdraftLimit;
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;

    @Override
    public void deposit(double amount) {
        setInterestRate();
        double amountWithInterest = amount*(1+interestRate);
        super.deposit(amountWithInterest); // Call super class implementation.
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount);
        setInterestRate();
    }

    public SavingsAccount(String name) {
        super(name);
    }

    public double getInterestRate() {
        return interestRate;
    }

    private void setInterestRate() {
        double oldInterestRate = interestRate;

        if (getBalance() < 2000) {
            interestRate = 0.001; // 0.1%
        } else if (getBalance() < 25000) {
            interestRate = 0.005;
        } else {
            interestRate = 0.0065;
        }

        // Since floating point math is inaccurate.
        if (Math.abs(oldInterestRate-interestRate) > 0.00001) {
            System.out.println(getName() + "'s new interest rate is: " + interestRate + ".");
        }
    }
}

public class JavaFundamentals {
    // Only required to name the file JavaFundamentals.
}
