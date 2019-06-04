package com.example.bank_app.dataAccess.databases;


import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class QueryUtilities {
    //nombres de las tablas
    protected static final String tableUser="Usuario";
    protected static final String tableAdmin="Administrador";
    protected static final String tableAccount="Cuenta";
    protected static final String tableTransaction="Transaccion";

    //nombres de las views
    protected static final String accountCounter="NoCuentas";

    //nombres de los campos
    protected static final String universalID="_id";
    protected static final String universalPassword="password";
    protected static final String userName="nombre";
    protected static final String accountBalance="saldo";
    protected static final String accountDate="fecha_creacion";
    protected static final String Owner="propietario";
    protected static final String transactionDate="fecha";
    protected static final String transactionSource="origen";
    protected static final String transactionTarget="destino";
    protected static final String transactionBalance="cantidad";
    protected static final String transactionStatus="estado";

    //campos de las views
    protected static final String number="numero";

    //operaciones
    protected static final String insertStatement="insert into";

    public static void insertTransaction(SQLiteDatabase mDb,int source, int target, int ammount, String status){
        String insert = insertStatement+" "+tableTransaction+"("+transactionSource+","+transactionTarget+","+transactionBalance+","+transactionStatus+") values("+source+","+target+","+ammount+", '"+status+"'"+")";
        mDb.execSQL(insert);
    }

    public static void insertUser(SQLiteDatabase mDb,int id, String name, int pass){
        String insert = insertStatement+" "+tableUser+" values("+id+",'"+name+"',"+pass+")";
        mDb.execSQL(insert);
    }

    public static Cursor userQuery(SQLiteDatabase mDb,int personal_id){
        String select = "select * from "+tableUser+" where "+universalID+" ="+personal_id;
        return mDb.rawQuery(select,null);
    }

    public static Cursor accountQueryByID(SQLiteDatabase mDb,int acountOwner){
        String select = "select * from "+tableAccount+" where "+Owner+" ="+acountOwner;
        return mDb.rawQuery(select,null);
    }

    public static Cursor accountQueryByExistingID(SQLiteDatabase mDb,int acountID){
        String select = "select "+universalID+" from "+tableAccount+" where "+universalID+" ="+acountID;
        return mDb.rawQuery(select,null);
    }
    public static Cursor accountQueryByOwner(SQLiteDatabase mDb,int acountID){
        String select = "select "+Owner+" from "+tableAccount+" where "+universalID+" ="+acountID;
        return mDb.rawQuery(select,null);
    }

    public static Cursor accountNumber(SQLiteDatabase mDb){
        String select = "select * from "+accountCounter;
        return mDb.rawQuery(select,null);
    }

    public static void updateAccount(SQLiteDatabase mDb,int newID, int owner){
        String update = "UPDATE "+tableAccount+" set "+universalID+" ="+newID+" where "+Owner+" ="+owner;
        mDb.execSQL(update);
    }

    public static void updateAccountBalance(SQLiteDatabase mDb,int newBalance, int owner){
        String update = "UPDATE "+tableAccount+" set "+accountBalance+" ="+newBalance+" where "+Owner+" ="+owner;
        mDb.execSQL(update);
    }
}
