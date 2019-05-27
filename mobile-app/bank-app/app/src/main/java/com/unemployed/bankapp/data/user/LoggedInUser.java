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
        search_psid(userId);
    }

    public int getUserId() {
        return userId;
    }

    public int getPassword() {
        return password;
    }

    private void search_psid(int psid){

        SQLiteDatabase db = con.getWritableDatabase();
        row = db.rawQuery("Select * from Usuario where cedula='"+psid,null);

        if(row.moveToFirst()==true){
            us.setCedula(row.getInt(0));
            us.setNombre(row.getString(1));
            us.setPassword(row.getInt(2));
        }
    }
}
