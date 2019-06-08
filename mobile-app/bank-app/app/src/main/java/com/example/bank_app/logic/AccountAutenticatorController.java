package com.example.bank_app.logic;

import android.content.Context;

import com.example.bank_app.dataAccess.models.*;
import com.example.bank_app.dataAccess.repositories.AccountsRepository;

public class AccountAutenticatorController {
    private AccountsRepository provAccount;

    public AccountAutenticatorController(Context context) {
        this.provAccount = new AccountsRepository(context);
    }

    public Account getAccount(){ return provAccount.getAccount(); }

    public Account viewAccount(int owner){
        provAccount.createTable();
        provAccount.open();
        if (provAccount.getAccountById(owner)){
            provAccount.close();
            return  provAccount.getAccount();
        }
        return null;
    }

    public boolean refill(int owner){
        provAccount.createTable();
        provAccount.open();
        if (provAccount.getAccountById(owner)){
            return  provAccount.recharge(500,owner);
        }
        return false;
    }


    public int accountExistance(int account_id){
        int currentID=provAccount.getAccountExistingId(account_id);
        provAccount.createTable();
        provAccount.open();
        if (provAccount.getAccountExistingId(account_id)!=-1){
            provAccount.close();
        }
        return currentID;
    }
}
