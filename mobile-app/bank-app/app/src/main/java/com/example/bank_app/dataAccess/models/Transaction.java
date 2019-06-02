package com.example.bank_app.dataAccess.models;

import java.util.Date;

public class Transaction {

    private int id_transaction;
    private Date date;
    private int begin;
    private int destination;
    private int amount;
    private String state;

    public Transaction(int id_transaction, Date date, int begin, int destination, int amount, String state) {
        this.id_transaction = id_transaction;
        this.date = date;
        this.begin = begin;
        this.destination = destination;
        this.amount = amount;
    }

    public Transaction(Date date, int begin, int destination, int amount, String state) {
        this.date = date;
        this.begin = begin;
        this.destination = destination;
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId_transaction() {
        return id_transaction;
    }

    public void setId_transaction(int id_transaction) {
        this.id_transaction = id_transaction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getDestination() {
        return destination;
    }

    public void setDestination(int destination) {
        this.destination = destination;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
