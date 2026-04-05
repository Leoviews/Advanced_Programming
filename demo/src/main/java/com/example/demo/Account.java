package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double balance;
    private String accountType;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    public Account() {
    }

    public Account(String name, double balance, String accountType, String accountNumber) {
        this.name = name;
        this.balance = balance;
        this.accountType = accountType;
        this.accountNumber = accountNumber;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}