package com.example.bank_app.dataAccess.models;

import java.util.Date;

public class Account {

    private int no_account;
    private int balance;
    private Date data_create;
    private int owner;

    public Account(int no_account, int balance, Date data_create, int owner) {
        this.no_account = no_account;
        this.balance = balance;
        this.data_create = data_create;
        this.owner = owner;
    }

    public Account(int balance, Date data_create, int owner) {
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

    public Date getData_create() {
        return data_create;
    }

    public void setData_create(Date data_create) {
        this.data_create = data_create;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
