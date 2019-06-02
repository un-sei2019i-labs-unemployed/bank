package com.example.bank_app.dataAccess.repositories;

import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.example.bank_app.dataAccess.databases.Database;
import com.example.bank_app.dataAccess.models.User;

import java.io.IOException;


public class UsersRepository {

    protected static final String TAG = "UsersAdapter";

    private User user;
    private SQLiteDatabase mDb;
    private Database con;

    public UsersRepository(Context context) {
        this.con = new Database(context);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UsersRepository createTable() throws SQLException{
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

    public UsersRepository open()throws  SQLException{
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

    public boolean insertUser(int id, String name, int pass){
        try{
            // SQL Statement
            String insert = "insert into Usuario values("+id+",'"+name+"',"+pass+")";
            mDb.execSQL(insert);
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
    public boolean updateUser(){
        try{
            // SQL Statement
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }
    public boolean readUser(int personal_id){
        try{
            String select = "select * from Usuario where _id ="+personal_id;
            Cursor query = mDb.rawQuery(select,null);
            if(query.moveToFirst()){
                int _id = query.getInt(0);
                String name = query.getString(1);
                int pass = query.getInt(2);
                User fakeUser = new User(_id,name,pass);
                setUser(fakeUser);
                return true;
            }
        }catch(SQLiteException e){
            return false;
        }
        return false;
    }

    public boolean deleteUser(int personal_id){
        try{
            // SQL Statement
            return true;
        }catch(SQLiteException e){
            return false;
        }
    }

    public User[] readUsers(){
        int n=1;
        User[] user = new User[n];
        try{
            // SQL Statement
            for (int i=0; i<n;i++) user[i] = new User(0, null, 0);
        }catch(SQLiteException e){
            user = null;
        }
        return user;
    }

}
