package com.example.bank_app.dataAccess.models;

public class Account {

    private int no_account;
    private int balance;
    private String data_create;
    private int owner;

    public Account(int no_account, int balance, String data_create, int owner) {
        this.no_account = no_account;
        this.balance = balance;
        this.data_create = data_create;
        this.owner = owner;
    }

    public Account(int balance, String data_create, int owner) {
        this.balance = balance;
        this.data_create = data_create;
        this.owner = owner;
    }

    public int getNo_accoun() {
        return no_account;
    }

    public void setNo_accoun(int no_account) {
        this.no_account = no_account;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getData_create() {
        return data_create;
    }

    public void setData_create(String data_create) {
        this.data_create = data_create;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
