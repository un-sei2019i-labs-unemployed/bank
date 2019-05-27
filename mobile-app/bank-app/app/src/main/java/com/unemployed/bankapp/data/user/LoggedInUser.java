package com.unemployed.bankapp.data.user;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.unemployed.bankapp.data.ConexionSQLiteHelper;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private int userId;
    private int password;

    private user us;
    private ConexionSQLiteHelper con;

    private Cursor row;

    public LoggedInUser(int userId, int password) {
        this.userId = userId;
        this.password = password;
        this.us = search_psid();
    }

    public int getUserId() {
        return userId;
    }

    public int getPassword() {
        return password;
    }

    public user getUs() {
        return us;
    }

    private user search_psid(){
        SQLiteDatabase db = con.getWritableDatabase();
        row = db.rawQuery("Select * from Usuario where cedula='"+getUserId(),null);
        if(row.moveToFirst()==true){
            user u = new user(row.getInt(0),row.getString(1),row.getInt(2));
            return u;
        }
        return null;
    }
}
