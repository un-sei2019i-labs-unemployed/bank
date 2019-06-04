package com.example.bank_app.dataAccess.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Transaction {

    @Id
    private int id_transaction;
    @Transient
    private Date date;
    @Transient
    private int begin;
    @Transient
    private int destination;
    @Transient
    private int amount;
    @Transient
    private String state;

    public Transaction() {}

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

    @Generated(hash = 2018882728)
    public Transaction(int id_transaction) {
        this.id_transaction = id_transaction;
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
