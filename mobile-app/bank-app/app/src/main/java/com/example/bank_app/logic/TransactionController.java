package com.example.bank_app.logic;

import android.content.Context;

import com.example.bank_app.dataAccess.models.Account;
import com.example.bank_app.dataAccess.models.Transaction;
import com.example.bank_app.dataAccess.repositories.*;

public class TransactionController {
    private TransactionsRepository provTransaction;
    private AccountsRepository provAccount;

    public TransactionController(Context context) {
        this.provTransaction = new TransactionsRepository(context);
        this.provAccount = new AccountsRepository(context);
    }

    public Transaction getTransaction(){ return provTransaction.getUser(); }

    public Account getAccount(){ return provAccount.getAccount(); }

    private Transaction checkTransaction(int personal_id){
        provTransaction.createTable();
        provTransaction.open();
        if (provTransaction.readTransaction(personal_id)){
            provTransaction.close();
            return  provTransaction.getUser();
        }
        return null;
    }

    public String isVerified(int origin,String accountID, String amount, int currentBalance){
        if(!accountID.isEmpty()){
            if(!amount.isEmpty() && amount.length()>=1){
                int id = Integer.parseInt(accountID);
                int am = Integer.parseInt(amount);
                lookForAccount();
                int target=provAccount.getAccountOwnerById(id);
                lookForTransaction();
                // Handle
                if(provAccount.getAccountExistingId(id)!=-1) {
                    if (am <= currentBalance && am>0) {
                        if(provTransaction.insertTransaction(origin,target,am,"completa"))
                            return "Succesful transaction!";
                        else
                            return "fatal failure "+provTransaction.error;
                    }else if(am<=0) {
                        return "Ammount must be greater than 0";
                    }else {
                        return "You don't have enough funds for this trasaction!";
                    }
                }else
                    return "target account doesn't exists";
            }else{
                return "There isn't a valid ammount";
            }
        }else{
            return "Enter a valid Account ID";
        }
    }

    private void lookForAccount(){
        provAccount.createTable();
        provAccount.open();
    }

    private void lookForTransaction(){
        provTransaction.createTable();
        provTransaction.open();
    }
}
