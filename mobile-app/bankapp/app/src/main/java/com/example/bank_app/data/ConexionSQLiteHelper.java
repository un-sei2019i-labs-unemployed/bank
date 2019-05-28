package com.example.bank_app.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {


    public static final String DB_NAME = "bank-app";
    public static final int DB_VERSION = 1;

    // Tabla usuario
    public static final String TABLE_USER = "Usuario";
    // Tabla usuario - Columnas
    public static final String CEDULA = "cedula";
    public static final String NOMBRE = "nombre";
    public static final String PASSWORD = "password";

    public static final String SQL_TABLE_USER = " CREATE TABLE " + TABLE_USER
            + " ( "
            + CEDULA + " INTEGER PRIMARY KEY NOT NULL,  "
            + NOMBRE + " TEXT NOT NULL, "
            + PASSWORD + " INTEGER NOT NULL "
            + " )";

    public ConexionSQLiteHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_TABLE_USER);
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USER+ " VALUES (1023954713, 'Diego Ruiz',210105 )");
        sqLiteDatabase.execSQL("INSERT INTO " + TABLE_USER+ " VALUES (1023954712, 'Nobza',123456 )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL(SQL_TABLE_USER);
    }

    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();
        //create content values to insert
        ContentValues values = new ContentValues();
        // Put in @values
        values.put(CEDULA, user.getCedula());
        values.put(NOMBRE, user.getPassword());
        values.put(PASSWORD,user.getPassword());
        // insert row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public User Loggin(User user) throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " +  TABLE_USER
                        + " WHERE " + CEDULA + " = '" + user.getCedula() + "'", null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {

            //if cursor has value then in user database there is user associated with this given username
            User user1 = new User(cursor.getInt(cursor.getColumnIndex(CEDULA)),cursor.getString(cursor.getColumnIndex(NOMBRE)),cursor.getInt(cursor.getColumnIndex(PASSWORD)));

            cursor.close();
            db.close();
            //Match both passwords check they are same or not
            if (user.getPassword() == user1.getPassword()){
                return user1;
            }
        }

        return null;
    }

    public boolean doesExist(String ID, String Username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " +  TABLE_USER
                        + " WHERE " + NOMBRE + " = '" + Username
                        + "' OR " + CEDULA + " = " + ID, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given id or username so return true
            return true;
        }
        cursor.close();
        db.close();
        //if id and username does not exist return false
        return false;
    }


}
