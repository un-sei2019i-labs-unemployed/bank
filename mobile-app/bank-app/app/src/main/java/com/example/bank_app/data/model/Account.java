package com.example.bank_app.data.model;

import java.util.Date;

public class Account {

    private int no_accoun;
    private int balance;
    private Date data_create;
    private int owner;

    public Account(int no_accoun, int balance, Date data_create, int owner) {
        this.no_accoun = no_accoun;
        this.balance = balance;
        this.data_create = data_create;
        this.owner = owner;
    }

    public int getNo_accoun() {
        return no_accoun;
    }

    public void setNo_accoun(int no_accoun) {
        this.no_accoun = no_accoun;
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
