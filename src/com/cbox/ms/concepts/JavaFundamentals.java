package com.cbox.ms.concepts;

import java.util.ArrayList;
import java.util.List;

// Abstraction: Provides the interface to the user while hiding the implementation.
// The user knows what the class does instead of how it does it.
// This is achieved through abstract classes and interfaces.
// Abstract classes:
// - Can contain abstract methods and/or implemented methods.
// - Behaves like a normal class (constructors, public method implementation, inheritance, etc).
// - Classes can only subclass one class.
// Interfaces:
// - Fields are public static final by default.
// - Methods are public by default.
// - static, default, private methods must be implemented.
// - Classes can subclass many interfaces.
// Both:
// - Can't be instantiated without being subclassed.
// - Methods must be implemented in the first non-abstract subclass.

interface Transaction { // Can't be initialised directly. Must be subclassed.
    void deposit(double amount); // Interface methods are public by default.
    void withdraw(double amount);
}

// Inheritance: Allows once class to build on another class/interface.
// This is achieved through extends and implements keywords.
// - Represents 'is-a' relationship.
// - The simpler class is called a superclass, the new class is called a subclass.
// - All classes subclass Object.
// - 'this' is used to call current class methods.
// - 'super' is used to call superclass methods.
abstract class BankAccount implements Transaction { // Can't be initialised directly. Must be subclassed.

    // Encapsulation: Protects inner workings of class by data hiding.
    // This is achieved through access modifiers (public, private, default, private) ,
    // getters, and setters.
    // - Can rename fields without affecting dependant classes.
    // - Can introduce logic before getting/setting a field (validation, logging, etc).
    // - Can limit access to field/method/class/etc to just that class / package / subclasses.

    private final int id;
    private static int idCount = 1;
    private final String name;
    private double balance;
    private List<Double> transactions = new ArrayList<>();

    // Final keyword: Prevents further modification.
    // - Final fields must be initialised during declaration, OR in the constructor.
    // - Final methods can't be overridden.
    // - Final classes can't be subclassed.

    // Static keyword: Allows accessing fields/methods through class rather than instance.
    // - Static fields are instantiated once per class. Changing it in one class changes
    // it for the others too.
    // - Static methods can only use static fields or other static methods within the
    // same class. Local variables and their methods can be non-static.

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
    public JavaFundamentals() {
        CurrentAccount JohnDoe = new CurrentAccount("John Doe", 1000);
        JohnDoe.deposit(5000);

        SavingsAccount JaneDoe = new SavingsAccount("Jane Doe");
        JaneDoe.deposit(10000);
        JaneDoe.withdraw(2000);

        // Polymorphism:
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(JohnDoe);
        accounts.add(JaneDoe);

        for (BankAccount account : accounts) {
            account.deposit(50);
        }
    }
}
