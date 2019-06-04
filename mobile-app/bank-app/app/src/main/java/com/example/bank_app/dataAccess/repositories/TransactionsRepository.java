package com.example.bank_app.dataAccess.repositories;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.bank_app.dataAccess.databases.Database;

import com.example.bank_app.dataAccess.databases.QueryUtilities;
import com.example.bank_app.dataAccess.models.Transaction;

import java.io.IOException;

public class TransactionsRepository {
    protected static final String TAG = "UsersAdapter";

    private Transaction transaction;
    private SQLiteDatabase mDb;
    private Database con;
    public String error="";

    public TransactionsRepository(Context context) {
        this.con = new Database(context);
    }

    public Transaction getUser() {
        return transaction;
    }

    public void setUser(Transaction transaction) {
        this.transaction = transaction;
    }

    public TransactionsRepository createTable() throws SQLException {
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

    public TransactionsRepository open()throws  SQLException{
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

    public void close()
    {
        con.close();
    }

    public boolean insertTransaction(int source, int target, int ammount, String status){
        try{
            QueryUtilities.insertTransaction(mDb, source, target, ammount, status);
            return true;
        }catch(SQLiteException e){
            error=e.getMessage();
            return false;
        }
    }
    public boolean updateTransaction(String status, int ID){
        try{
            // SQL Statement
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
    public boolean readTransaction(int personal_id){
        try{
            // SQL Statement
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }

    public boolean deleteTransaction(int personal_id){
        try{
            // SQL Statement
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
}
