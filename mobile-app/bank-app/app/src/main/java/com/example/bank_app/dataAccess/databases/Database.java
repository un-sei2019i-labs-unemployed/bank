package com.example.bank_app.dataAccess.databases;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Database extends SQLiteOpenHelper {

    // /data/data/com.example.bank_app/databases/
    private static String DB_PATH = "";
    private static String DB_NAME = "bank-app.db";

    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public Database(Context context) {
        super(context, DB_NAME, null, 1);
        this.myContext = context;
        DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if(dbExist){
        }else{
            this.getReadableDatabase();
            this.close();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            // Database don't exist
        }
        if(checkDB != null){ checkDB.close(); }
        return checkDB != null ? true : false;
    }

    private void copyDataBase() throws IOException{
        InputStream myInput = myContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public boolean openDataBase() throws SQLException {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        return myDataBase != null;
    }

    @Override
    public synchronized void close() {
        if(myDataBase != null){ myDataBase.close(); }
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion>oldVersion)
            try{
                copyDataBase();
            }catch(IOException e){
                throw new Error(e.toString());
            }
    }


}
