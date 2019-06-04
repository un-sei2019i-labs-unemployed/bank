package com.example.bank_app.dataAccess.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import java.util.Date;

import com.example.bank_app.dataAccess.databases.Database;
import com.example.bank_app.dataAccess.models.Account;
import com.example.bank_app.dataAccess.models.User;

import java.io.IOException;

public class AccountsRepository {
    protected static final String TAG = "AcountsAdapter";

    Account account;
    private SQLiteDatabase mDb;
    private Database con;

    public AccountsRepository(Context context) {
        this.con = new Database(context);
    }

    public Account getAccount() { return account; }

    public void setAccount(Account account) { this.account = account; }

    public AccountsRepository createTable() throws SQLException {
        try
        {
            con.createDataBase();
        }
        catch (IOException mIOException)        {
            Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
            throw new Error("UnableToCreateDatabase");
        }
        return this;
    }

    public AccountsRepository open()throws  SQLException{
        try
        {
            con.openDataBase();
            con.close();
            mDb = con.getReadableDatabase();
        }
        catch (SQLException mSQLException)
        {
            Log.e(TAG, "open >>"+ mSQLException.toString());
            throw mSQLException;
        }
        return this;
    }

    public void close() { con.close(); }

    public boolean insertAccount(){
        return false;
    }

    public boolean getAccountById(int acountOwner){
        try{
            String select = "select * from Cuenta where propietario ="+acountOwner;
            Cursor query = mDb.rawQuery(select,null);
            if(query.moveToFirst()){
                int _id = query.getInt(0);
                int balance = query.getInt(1);
                String date = query.getString(2);
                int owner = query.getInt(3);
                Account fakeAccount = new Account(_id,balance,date,owner);
                setAccount(fakeAccount);
                return true;
            }
        }catch(SQLiteException e){
            return false;
        }
        return false;
    }

    public int getAccountExistingId(int acountID){
        try{
            String select = "select _id from Cuenta where _id ="+acountID;
            Cursor query = mDb.rawQuery(select,null);
            if(query.moveToFirst()){
                int _id = query.getInt(0);
                return _id;
            }
        }catch(SQLiteException e){
            return -1;
        }
        return -1;
    }

    public int getAccountOwnerById(int acountID){
        try{
            String select = "select propietario from Cuenta where _id ="+acountID;
            Cursor query = mDb.rawQuery(select,null);
            if(query.moveToFirst()){
                int _id = query.getInt(0);
                return _id;
            }
        }catch(SQLiteException e){
            return -1;
        }
        return -1;
    }

    public int getAccountNumber(){
        try{
            String select = "select * from NoCuentas";
            Cursor query = mDb.rawQuery(select,null);
            if(query.moveToFirst()){
                int number = query.getInt(0);
                return number;
            }
        }catch(SQLiteException e){
            return 0;
        }
        return 0;
    }

    public boolean updateAccount(int newID, int owner){
        try{
            String update = "UPDATE Cuenta set _id="+newID+" where propietario="+owner;
            mDb.execSQL(update);
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }

    public boolean recharge(int newBalance, int owner){
        try{
            String update = "UPDATE Cuenta set saldo="+newBalance+" where propietario="+owner;
            mDb.execSQL(update);
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }

    public boolean deleteAccount(){
        return false;
    }

}
