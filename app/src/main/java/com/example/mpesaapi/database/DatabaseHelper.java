package com.example.mpesaapi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import androidx.annotation.Nullable;

import java.sql.PreparedStatement;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String database = "api.sqlite";

    //stk push table
    public static final String table = "stkpush";
    public static final String id = "id";
    public static final String password = "password";
    public static final String date = "date";
    public static final String checkOutId = "checkOutId";


    public DatabaseHelper(@Nullable Context context) {
        super(context, database, null, 1);
        Log.d("DATABASE: ","database created");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + table + "(id integer primary key autoincrement,password text,date varchar(50),checkOutId text)");
        Log.d("DATABASE: ","table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
    }

    public void insertSTKPush(String password,String date,String checkOutId){
        SQLiteDatabase database = getWritableDatabase();
        String sql ="INSERT INTO " + table + "(password,date,checkOutId)VALUES(?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,password);
        statement.bindString(2,date);
        statement.bindString(3,checkOutId);
        statement.executeInsert();
        statement.close();
        database.close();
    }




}
